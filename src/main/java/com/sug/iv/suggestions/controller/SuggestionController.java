package com.sug.iv.suggestions.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sug.iv.suggestions.engine.SuggestionsResponse;
import com.sug.iv.suggestions.service.ISuggestionService;

@RestController
public class SuggestionController {

	private final static Logger LOG = LoggerFactory.getLogger(SuggestionController.class);
	@Autowired
	private ISuggestionService suggestionService;

	@RequestMapping(method = RequestMethod.GET, value = "suggestions")
	public ResponseEntity<Object> getSuggestions(@RequestParam(name="q", required=false) String q, @RequestParam(value = "latitude", required=false) String latitude,
			@RequestParam(value = "longitude", required=false) String longitude) {
		LOG.info("/suggestions");
		SuggestionsResponse resp;
		try {
			resp = suggestionService.getSuggestions(q, latitude, longitude);
			return new ResponseEntity<Object>(resp, HttpStatus.OK);
		} catch (Exception ex) {
			resp = new SuggestionsResponse(ex.getLocalizedMessage());
			return new ResponseEntity<Object>("error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
