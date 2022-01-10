package com.fts.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

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
		else if (column.equals("Files"))
			columnno = 4;
		else
			columnno = 5;

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
		String rf = "";
		int columnno = 0;
		
		if (column.equals("ginc_FileName"))
			columnno = 0;
		else if (column.equals("ginc_FileType"))
			columnno = 1;
		else if (column.equals("gatra_FileName"))
			columnno = 2;
		else if (column.equals("gatra_FileType"))
			columnno = 3;
		else if (column.equals("bmc_FileName"))
			columnno = 4;
		else if (column.equals("bmc_FileType"))
			columnno = 5;
		else
			columnno = 6;
		
		Row row = ((XSSFSheet) sheet).getRow(rowno);
		
		if (row.getCell(columnno) == null)
			return "";
		else
		{ 
			rf = row.getCell(columnno).getStringCellValue() + "";
			return rf;
		}
		
	}
	
	public int numberOfUsers() {
		
		int users = sheet.getLastRowNum()+1;
		return users;
		
	}
	
	public int rowCount(String column) {
		
		int rc = 0;
		int columnno = 0;
		
		if (column.equals("ginc_FileName"))
			columnno = 0;
		else if (column.equals("ginc_FileType"))
			columnno = 1;
		else if (column.equals("gatra_FileName"))
			columnno = 2;
		else if (column.equals("gatra_FileType"))
			columnno = 3;
		else if (column.equals("bmc_FileName"))
			columnno = 4;
		else if (column.equals("bmc_FileType"))
			columnno = 5;
		else
			columnno = 6;
		
		for (Row row : sheet) {
			if (row.getCell(columnno) != null)
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
