package com.sug.iv.suggestions.engine;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuggestionsResponseTest {

	@Test
	public void test() {
		SuggestionsResponseTest r = new SuggestionsResponseTest();
		assertNotNull(r);
		PojoTestUtils.validateAccessors(SuggestionsResponseTest.class);
	}

}
