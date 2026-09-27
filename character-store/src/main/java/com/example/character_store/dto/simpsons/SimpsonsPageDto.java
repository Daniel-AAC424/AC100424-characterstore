package com.example.character_store.dto.simpsons;

import java.util.List;

public class SimpsonsPageDto {

    private Integer count;
    private String next;
    private String prev;
    private Integer pages;
    private List<SimpsonsCharacterDto> results;
    
    
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrev() {
		return prev;
	}
	public void setPrev(String prev) {
		this.prev = prev;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	public List<SimpsonsCharacterDto> getResults() {
		return results;
	}
	public void setResults(List<SimpsonsCharacterDto> results) {
		this.results = results;
	}

    // Getters y setters.
    
    
    
}
