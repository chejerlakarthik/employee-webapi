package com.karthik.rest.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StringUtilTest {
	
	public String input;
	public boolean expected;
	
	public StringUtilTest(String input, boolean expected) {
		this.input = input;
		this.expected = expected;
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] inputs = { { "", true }, { null, true }, { "JUnit", false } };
		return Arrays.asList(inputs);
	}
	
	@Test
	public void testStringUtilNullOrBlankFunction() {
		assertEquals(expected, StringUtil.isNullOrBlank(input));
	}

}
