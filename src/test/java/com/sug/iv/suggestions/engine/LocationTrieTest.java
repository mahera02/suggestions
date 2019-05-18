package com.sug.iv.suggestions.engine;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTrieTest {

	@Test
	public void test() {
		LocationTrie r = new LocationTrie();
		assertNotNull(r);
		PojoTestUtils.validateAccessors(LocationTrie.class);
	}

}
