package com.example.character_store.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.example.character_store.dto.simpsons.SimpsonsPageDto;

@Service
public class SimpsonsApiService {
	
    private final RestClient restClient;

    public SimpsonsApiService() {
        this.restClient = RestClient.builder()
            .baseUrl("https://thesimpsonsapi.com/api")
            .build();
    }

    public SimpsonsPageDto obtenerPagina(int pagina) {
        return restClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/characters")
                .queryParam("page", pagina)
                .build())
            .retrieve()
            .body(SimpsonsPageDto.class);
    }


}
