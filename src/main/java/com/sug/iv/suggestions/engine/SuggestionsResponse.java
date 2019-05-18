package com.sug.iv.suggestions.engine;

import java.util.List;

public class SuggestionsResponse {
	List<Location> suggestions;
	
	String error;

	public SuggestionsResponse(String error) {
		super();
		this.error = error;
	}

	public List<Location> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Location> suggestions) {
		this.suggestions = suggestions;
	}

	public SuggestionsResponse(List<Location> suggestions) {
		super();
		this.suggestions = suggestions;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
