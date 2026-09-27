package com.example.character_store.dto.rickmorty;

import java.util.List;

public class RickMortyResponseDto {
	
    private RickMortyInfoDto info;
    private List<RickMortyCharacterDto> results;

    public RickMortyInfoDto getInfo() {
        return info;
    }

    public void setInfo(RickMortyInfoDto info) {
        this.info = info;
    }

    public List<RickMortyCharacterDto> getResults() {
        return results;
    }

    public void setResults(List<RickMortyCharacterDto> results) {
        this.results = results;
    }

}
