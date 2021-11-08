package com.utilities;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;

import java.util.Hashtable;

public class DataUtil {

//	private static boolean isSuiteRunnable(String suiteName) {
//
//        ExcelReader excel = new ExcelReader(Constants.SUITE_XL_PATH);
//		int rows = excel.getRowCount(Constants.SUITE_SHEET);
//		for (int rowNum = 2; rowNum <= rows; rowNum++) {
//
//			String data = excel.getCellData(Constants.SUITE_SHEET, Constants.SUITE_NAME_COLUMN, rowNum);
//			if (data.equalsIgnoreCase(suiteName)) {
//				String runMode = excel.getCellData(Constants.SUITE_SHEET, Constants.RUN_MODE_COLUMN, rowNum);
//				if (runMode.equalsIgnoreCase(Constants.RUN_MODE_YES))
//					return true;
//				else
//					return false;
//			}
//		}
//		return false;
//	}

    public static boolean isTestRunnable(String testCaseName, ExcelReader excel) {
        int rows = excel.getRowCount(Constants.TEST_CASE_SHEET);
        for (int rowNum = 2; rowNum <= rows; rowNum++) {

            String data = excel.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASE_COLUMN, rowNum);
            if (data.equalsIgnoreCase(testCaseName)) {
                String runMode = excel.getCellData(Constants.TEST_CASE_SHEET, Constants.RUN_MODE_COLUMN, rowNum);
                if (runMode.equalsIgnoreCase(Constants.RUN_MODE_YES))
                    return true;
                else
                    return false;
            }
        }
        return false;

    }

    @DataProvider
    public static Object[][] getData(String testcase, ExcelReader excel) {

        int rows = excel.getRowCount(Constants.DATA_SHEET);
        System.out.println("Total rows count = " + rows);

        //String testName = "OpenAccountTest";
        // find the test case start row

        int testCaseRowsNumber = 1;

        for (testCaseRowsNumber = 1; testCaseRowsNumber <= rows; testCaseRowsNumber++) {

            String testCaseName = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowsNumber);

            if (testCaseName.equalsIgnoreCase(testcase)) {

                break;

            }

        }
        System.out.println("Test case starts since row number " + testCaseRowsNumber);

        // verifying how many rows in the test case

        int testCaseStartRow = testCaseRowsNumber + 2;

        int rowsNum = 0;
        while (!excel.getCellData(Constants.DATA_SHEET, 0, testCaseStartRow + rowsNum).equals("")) {
            rowsNum++;
        }
        System.out.println("Total number test rows = " + rowsNum);

        // verifying how many columns in the test case

        int colsNum = 0;
        int columnsStartNumber = testCaseRowsNumber + 1;
        while (!excel.getCellData(Constants.DATA_SHEET, colsNum, columnsStartNumber).equals("")) {
            colsNum++;
        }
        System.out.println("Total number test columns = " + colsNum);

        // printing data

        Object[][] data = new Object[rowsNum][1];

        int j = 0;
        for (int i = testCaseStartRow; i < testCaseStartRow + rowsNum; i++) {

            Hashtable<String, String> table = new Hashtable<String, String>();

            for (int k = 0; k < colsNum; k++) {

//                System.out.println(excel.getCellData(Constants.DATA_SHEET, k, i));
//                data[i-testCaseStartRow][k] = excel.getCellData(Constants.DATA_SHEET, k, i);
                String testData = excel.getCellData(Constants.DATA_SHEET, k, i);
                String colName = excel.getCellData(Constants.DATA_SHEET, k, columnsStartNumber);
                table.put(colName, testData);

            }
            data[j][0] = table;

            System.out.println("The " + j + " value of array is " + data[j][0]);
            j++;
        }
        return data;

    }

    //	public static void checkExecution(String testSuiteName, String testCaseName, String runMode, ExcelReader excel) {
    public static void checkExecution(String testCaseName, String runMode, ExcelReader excel) {
//		if(!isSuiteRunnable(testSuiteName)) {
//			throw new SkipException("Skipping the test " + testCaseName + " as runMode of testSuite " + testSuiteName + " is NO");
//		}
        if (!isTestRunnable(testCaseName, excel)) {
            throw new SkipException("Skipping the test " + testCaseName + " as runMode of testCase " + testCaseName + " is NO");
        }
        if (runMode.equalsIgnoreCase(Constants.RUN_MODE_NO)) {
            throw new SkipException("Skipping the data " + " as runMode of data " + testCaseName + " is NO");
        }
    }

}
