package com.sug.iv.suggestions.service;

import com.sug.iv.suggestions.engine.SuggestionsResponse;

public interface ISuggestionService {

	public SuggestionsResponse getSuggestions(String q, String latitude, String longitude);

}
