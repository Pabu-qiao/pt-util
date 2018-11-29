package com.ptutil;


import org.junit.Assert;
import org.junit.Test;

public class CamelUtilsTest {

	@Test
	public void testUnderscoreName() {
		String camelCaseName="woDeMingZi";
		String underscoreName = CamelUtils.underscoreName(camelCaseName);
		Assert.assertEquals("驼峰转下划线失败","wo_de_ming_zi",underscoreName);
	}

	@Test
	public void testCamelCaseName() {
		String underscoreName="wo_de_ming_zi";
		String camelCaseName = CamelUtils.camelCaseName(underscoreName);
		Assert.assertEquals("下划线转驼峰失败","woDeMingZi",camelCaseName);
	}

}
