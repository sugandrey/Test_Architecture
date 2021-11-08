package com.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="registrationData")
	public static Object[][] getDataTestCase(Method m) {
		ExcelReader excelReader = new ExcelReader(Constants.SUITE_XL_PATH);
		System.out.println(m.getName());
		String testCase = m.getName();
		return DataUtil.getData(testCase, excelReader);
	}

}
