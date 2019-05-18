
package com.sug.iv.suggestions.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sug.iv.suggestions.bean.SuggestionService;
import com.sug.iv.suggestions.service.ISuggestionService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SuggestionController.class, secure = false)
public class SuggestionControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ISuggestionService iSuggestionService;

	@Test
	public void testGetSuggestions() throws Exception {
		SuggestionService suggestionService = new SuggestionService();
		Mockito.when(iSuggestionService.getSuggestions(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn(suggestionService.getSuggestions("Zu", "43.70011", "-79.4163"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/suggestions?q=Zu&latitude=43.70011&longitude=-79.4163").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), 200);
		// System.out.println(" >>>>>>>>>>>>>>>>>>>" +
		// result.getResponse().getContentAsString());
		String expected = "{\"suggestions\":[{\"name\":\"Zuni Pueblo\",\"latitude\":35.07253,\"longitude\":-108.85064,\"score\":0.51938075}],\"error\":null}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetSuggestionsInvalid() throws Exception {
		SuggestionService suggestionService = new SuggestionService();
		Mockito.when(iSuggestionService.getSuggestions(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn(suggestionService.getSuggestions("Hogwards", "43.70011", "-79.4163"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/suggestions?q=Hogwards&latitude=43.70011&longitude=-79.4163").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), 200);
		// System.out.println(" >>>>>>>>>>>>>>>>>>>" +
		// result.getResponse().getContentAsString());
		String expected = "{\"suggestions\":[],\"error\":null}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetSuggestionsError() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/suggestions?q=Hogwards&latitude=43.70011&longitude=-79.4163").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), 200);
		// System.out.println(" >>>>>>>>>>>>>>>>>>>" +
		// result.getResponse().getContentAsString());
		assertThat(result.getResponse().getContentAsString().contains("error"));
	}
	// .thenThrow(new RuntimeException("INternal Server Error"));

}
