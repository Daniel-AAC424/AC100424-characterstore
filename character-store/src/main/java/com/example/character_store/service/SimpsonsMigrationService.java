package com.example.character_store.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.character_store.dto.simpsons.*;
import com.example.character_store.model.PersonajeSimpsons;
import com.example.character_store.repository.ProductoRepository;

@Service
public class SimpsonsMigrationService {
	
    private static final String UNIVERSO = "SIMPSONS";
    private static final String CDN = "https://cdn.thesimpsonsapi.com/500";

    private final SimpsonsApiService apiService;
    private final ProductoRepository repository;

    public SimpsonsMigrationService(
            SimpsonsApiService apiService,
            ProductoRepository repository) {
        this.apiService = apiService;
        this.repository = repository;
    }

    @Transactional
    public int migrarTodos() {
        int insertados = 0;
        int pagina = 1;
        int totalPaginas;

        do {
            SimpsonsPageDto respuesta = apiService.obtenerPagina(pagina);
            if (respuesta == null || respuesta.getResults() == null) {
                break;
            }

            totalPaginas = respuesta.getPages() == null
                    ? pagina
                    : respuesta.getPages();

            for (SimpsonsCharacterDto dto : respuesta.getResults()) {
                if (dto.getId() == null) continue;

                boolean existe = repository
                    .existsByUniversoAndApiId(UNIVERSO, dto.getId());

                if (!existe) {
                    repository.save(convertir(dto));
                    insertados++;
                }
            }

            System.out.println("Simpsons página " + pagina
                    + " de " + totalPaginas + " procesada");
            pagina++;

        } while (pagina <= totalPaginas);

        return insertados;
    }

    private PersonajeSimpsons convertir(SimpsonsCharacterDto dto) {
        PersonajeSimpsons p = new PersonajeSimpsons();
        p.setApiId(dto.getId());
        p.setNombre(dto.getName());
        p.setUniverso(UNIVERSO);
        p.setEdad(dto.getAge());
        p.setGenero(dto.getGender());
        p.setOcupacion(dto.getOccupation());
        p.setEstado(dto.getStatus());

        if (dto.getPortraitPath() != null) {
            p.setImagen(CDN + dto.getPortraitPath());
        }

        p.setPrecio(precioDidactico(dto.getId()));
        p.setStock(5 + (dto.getId() % 21));
        return p;
    }

    private BigDecimal precioDidactico(int apiId) {
        double valor = 19.99 + ((apiId % 15) * 2.00);
        return BigDecimal.valueOf(valor);
    }


}
