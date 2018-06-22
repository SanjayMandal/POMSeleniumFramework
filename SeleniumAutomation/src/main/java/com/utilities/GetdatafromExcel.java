package com.utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class GetdatafromExcel {
	private static String s = "Sheet1";
	private static String s1 = "./src/main/resources/Data/MasterData.xlsx";
	private static Map<String, String> map = new HashMap<>();
	static String str;

	public static String getData(String testcasename, String columnname) {
		FileInputStream fis;
		int k = 0;
		
		try {
			fis = new FileInputStream(s1);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet(s);
			int rows = ws.getPhysicalNumberOfRows();
			System.out.println("Number of rows"+ rows);
			for (int i = 0; i < rows; i++) {
				int cols = ws.getRow(i).getPhysicalNumberOfCells();
				System.out.println("Number of cols"+ cols);
				for (int j = 0; j < cols; j++) {
					System.out.println("col cell value"+ ws.getRow(i).getCell(j).toString());
					if (ws.getRow(i).getCell(j).toString().equalsIgnoreCase(columnname)) {
						//k = j;
						str =ws.getRow(i+1).getCell(j).toString();
						break;
					}
					//map.put(ws.getRow(i).getCell(0).toString(), ws.getRow(i).getCell(k).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return map.get(testcasename);
		return str;
	}
}