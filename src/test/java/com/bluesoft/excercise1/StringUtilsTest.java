package com.bluesoft.excercise1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StringUtilsTest {

	//@Disabled("Not implemented yet")
	@Test
	public void shouldRevertTheInput() {
		String result = StringUtils.reverse("12345");

		assertEquals(result, "54321");

	}

	@Test
	public void shouldRevertTheInputNull() {
		String result = StringUtils.reverse(null);

		assertNull(result);
	}
}
