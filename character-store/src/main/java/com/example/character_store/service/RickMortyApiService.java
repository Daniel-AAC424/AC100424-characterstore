package com.example.character_store.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClient;
import com.example.character_store.dto.rickmorty.RickMortyResponseDto;

@Service
public class RickMortyApiService {
	
    private static final int MAX_INTENTOS = 5;
	
    private final RestClient restClient;

    public RickMortyApiService() {
        this.restClient = RestClient.builder()
            .baseUrl("https://rickandmortyapi.com")
            .build();
    }

    public RickMortyResponseDto obtenerPagina(int pagina) {
        for (int intento = 1; intento <= MAX_INTENTOS; intento++) {
            try {
                return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                        .path("/api/character")
                        .queryParam("page", pagina)
                        .build())
                    .retrieve()
                    .body(RickMortyResponseDto.class);
            } catch (HttpStatusCodeException e) {
                int codigo = e.getStatusCode().value();
                boolean reintentable = codigo == 429 || codigo >= 500;
                if (!reintentable || intento == MAX_INTENTOS) {
                    throw e;
                }
                long espera = 2000L * intento;
                System.out.println("HTTP " + codigo + " en página " + pagina
                    + ". Reintento " + intento + " en " + espera + " ms");
                try {
                    Thread.sleep(espera);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw e;
                }
            }
        }
        return null;
    }



}
