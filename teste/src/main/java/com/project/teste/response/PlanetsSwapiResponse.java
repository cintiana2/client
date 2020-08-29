package com.project.teste.response;

import java.util.List;

public class PlanetsSwapiResponse {
	private Integer count;
	private String next; 
	private List<PlanetResponse> results;
	
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
	public List<PlanetResponse> getResults() {
		return results;
	}
	public void setResults(List<PlanetResponse> results) {
		this.results = results;
	}
}
