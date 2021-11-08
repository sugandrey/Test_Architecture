package com.utilities;

import com.webpageresources.HomeAccountPage;
import com.propertiesreceiver.GetAllPropertiesClass;

public class AddDataToExcel {

    private static final ExcelReader excelReader = new ExcelReader(Constants.SUITE_XL_PATH);
    private static String fileName = "webSetting.properties";

    public static int getNumberStartTitleRow(String testCaseName, String sheetName) {

        if(DataUtil.isTestRunnable(testCaseName, excelReader)) {

            int rows = excelReader.getRowCount(sheetName);
            int testCaseRowNumber = 0;
            for (testCaseRowNumber = 0; testCaseRowNumber < rows; testCaseRowNumber++) {
                String name = excelReader.getCellData(sheetName, 0, testCaseRowNumber);
                if(name.equalsIgnoreCase(testCaseName)) {
                    break;

                }
            }
            System.out.println("The row starts data since " + testCaseRowNumber);
            return testCaseRowNumber;
        }
        return -1;
    }
    public static int getNumberNewColumn(String testCaseName, String sheetName) {

        int colsNum = 0;
        int columnsStartNumber = getNumberStartTitleRow(testCaseName, sheetName);
        while (!excelReader.getCellData(sheetName, colsNum, columnsStartNumber).equals("")) {
            colsNum++;
        }
        System.out.println("Total number test columns = " + colsNum);
        return colsNum + 1;
    }
    public static void addColumnToSheet(String testCaseName, String sheetName,String colName) {

        excelReader.addColumn(testCaseName, sheetName, colName);

    }

    public static void getRowNumberForNewEntry(int rowNumber, HomeAccountPage homeAccountPage, String testCaseName) {
//        int rowsNum = 0;
//        while (!excelReader.getCellData(Constants.DATA_SHEET, 0, rowNumber + rowsNum).equals("")) {
//            rowsNum++;
//        }

        for (int row = rowNumber; row < rowNumber + 1; row++) {
            if (!homeAccountPage.getTitleHomeAccountPage()
                    .contains(GetAllPropertiesClass.getPropertyFileValue("HomeAccountPage", fileName))) {
                String result = "failed";
                excelReader.setCellData(testCaseName, Constants.DATA_SHEET, "Results", row, result);
                System.out.println("In row number " + row + " the result is " + result);
                //Assert.fail(data.get("Note"));
            }
            else {
                String result = "Passed";
                excelReader.setCellData(testCaseName, Constants.DATA_SHEET, "Results", row, result);
                System.out.println("In row number " + row + " the result is " + result);
            }
        }
    }


}
