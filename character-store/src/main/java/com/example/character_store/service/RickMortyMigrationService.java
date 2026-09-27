package com.example.character_store.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.character_store.dto.rickmorty.RickMortyCharacterDto;
import com.example.character_store.dto.rickmorty.RickMortyResponseDto;
import com.example.character_store.model.PersonajeRickMorty;
import com.example.character_store.repository.ProductoRepository;

@Service
public class RickMortyMigrationService {

    private static final String UNIVERSO = "RICK_MORTY";

    private static final long PAUSA_ENTRE_PAGINAS_MS = 1500L;

    private final RickMortyApiService apiService;
    private final ProductoRepository repository;
    private final TransactionTemplate tx;

    public RickMortyMigrationService(
            RickMortyApiService apiService,
            ProductoRepository repository,
            PlatformTransactionManager txManager) {
        this.apiService = apiService;
        this.repository = repository;
        this.tx = new TransactionTemplate(txManager);
    }

    public int migrarTodos() {
        int insertados = 0;
        int pagina = 1;
        int totalPaginas;

        do {
            RickMortyResponseDto respuesta = apiService.obtenerPagina(pagina);

            if (respuesta == null
                    || respuesta.getInfo() == null
                    || respuesta.getResults() == null) {
                break;
            }

            totalPaginas = respuesta.getInfo().getPages() == null
                    ? pagina
                    : respuesta.getInfo().getPages();

            final int paginaActual = pagina;
            final int totalPaginasActual = totalPaginas;

            insertados += tx.execute(estado -> guardarPagina(
                    respuesta.getResults(), paginaActual, totalPaginasActual));

            pagina++;

            if (pagina <= totalPaginas) {
                try {
                    Thread.sleep(PAUSA_ENTRE_PAGINAS_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

        } while (pagina <= totalPaginas);

        return insertados;
    }

    private int guardarPagina(List<RickMortyCharacterDto> resultados,
                              int pagina, int totalPaginas) {
        int nuevos = 0;

        for (RickMortyCharacterDto dto : resultados) {
            if (dto.getId() == null) continue;
            if (dto.getName() == null || dto.getName().isBlank()) continue;

            if (repository.existsByUniversoAndApiId(UNIVERSO, dto.getId())) {
                continue;
            }

            repository.save(convertir(dto));
            nuevos++;
        }

        System.out.println("Rick and Morty página " + pagina + " de " + totalPaginas
                + " procesada, nuevos: " + nuevos);
        return nuevos;
    }

    private PersonajeRickMorty convertir(RickMortyCharacterDto dto) {
        PersonajeRickMorty p = new PersonajeRickMorty();
        p.setApiId(dto.getId());
        p.setNombre(dto.getName());
        p.setUniverso(UNIVERSO);
        p.setGenero(limpiar(dto.getGender()));
        p.setEstado(limpiar(dto.getStatus()));
        p.setEspecie(limpiar(dto.getSpecies()));
        p.setTipo(limpiar(dto.getType()));
        p.setOrigen(limpiar(
                dto.getOrigin() == null ? null : dto.getOrigin().getName()));
        p.setLocalizacion(limpiar(
                dto.getLocation() == null ? null : dto.getLocation().getName()));

        if (dto.getImage() != null && !dto.getImage().isBlank()) {
            p.setImagen(dto.getImage());
        }

        p.setPrecio(precioDidactico(dto.getId()));
        p.setStock(5 + (dto.getId() % 21));
        return p;
    }

    private String limpiar(String valor) {
        if (valor == null) return null;
        String v = valor.trim();
        if (v.isEmpty()) return null;
        if (v.equalsIgnoreCase("unknown")) return null;
        if (v.equalsIgnoreCase("n/a")) return null;
        return v;
    }

    private BigDecimal precioDidactico(int apiId) {
        return BigDecimal.valueOf(24.99 + ((apiId % 17) * 1.75))
                .setScale(2, RoundingMode.HALF_UP);
    }
}