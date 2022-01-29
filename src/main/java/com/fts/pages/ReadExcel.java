package com.fts.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	File file; 
	FileInputStream inputStream;
	Workbook wb;
	Sheet sheet;
	public String readData(int rowno, String column) {
		
		String rs = "";
		int columnno = 2;

		if (column.equals("UserName"))
			columnno = 1;
		else if (column.equals("Password"))
			columnno = 2;
		else if (column.equals("Env"))
			columnno = 0;
		else if (column.equals("User"))
			columnno = 3;
		else if (column.equals("Location"))
			columnno = 4;
		else if (column.equals("Files"))
			columnno = 5;
		else
			columnno = 6;

		Row row = ((XSSFSheet) sheet).getRow(rowno);

		if (row.getCell(columnno) == null)
			return "";
		else 
		{ 
			rs = row.getCell(columnno).getStringCellValue() + "";
			return rs;
		}
	}
	
	
	public String readFile(int rowno, String column) {
		
		Row row = ((XSSFSheet) sheet).getRow(0);
		int colCount = row.getLastCellNum();
		String rf = "";
		int columnno = 0;
		
		for  (int j=0; j<colCount; j++) {
			if (column.equals(row.getCell(j).getStringCellValue()))
			{
				columnno = j;
				break;
			}
		}
		
		Row r = ((XSSFSheet) sheet).getRow(rowno);
		
		if (r.getCell(columnno) == null)
			return "";
		else
		{ 
			rf = r.getCell(columnno).getStringCellValue() + "";
			return rf;
		}
	}
	
	
	public int numberOfUsers() {
		
		int users = sheet.getLastRowNum()+1;
		return users;
	}
	
	
	public int rowCount(String column) {
		
		Row row = ((XSSFSheet) sheet).getRow(0);
		int colCount = row.getLastCellNum();
		int rc = 0;
		int columnno = 0;
		for  (int j=0; j<colCount; j++) {
			if (column.equals(row.getCell(j).getStringCellValue()))
			{
				columnno = j;
				break;
			}
		}
		
		for (Row r : sheet) {
			if (r.getCell(columnno) != null)
				rc += 1;
		}                     
		return rc;
	}
	
	
	public void initialize(String filePath, String fileName, String sheetName) throws IOException {
	
		file = new File(filePath + "\\" + fileName);
		inputStream = new FileInputStream(file);
		wb = null;
		wb = new XSSFWorkbook(inputStream);
		sheet = (Sheet) wb.getSheet(sheetName);
	}

}
