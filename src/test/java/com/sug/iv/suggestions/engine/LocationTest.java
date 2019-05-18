package com.sug.iv.suggestions.engine;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LocationTest {

	@Test
	public void test() {
		Location r = new Location("name", 1f, 2f, 3f);
		assertNotNull(r);
		PojoTestUtils.validateAccessors(Location.class);
	}
}
