package utils;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.ReadingMaterial;
import model.Room;
import service.ReadingMaterialService;

public class DataExport {
	
	public static boolean exportAll() {
		boolean result = false;
		
		ArrayList<ReadingMaterial> rmList = ReadingMaterialService.getDataForExport();
		ReadingMaterial rm = null;
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet rm_sheet = workbook.createSheet(ReadingMaterial.TABLE_RM);
		
		int rowNum = 0;
		Row row = null;
		Cell cell = null;
		System.out.println("Creating excel");
		
		// create headers
		row = rm_sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue(ReadingMaterial.COL_RMID);
		cell = row.createCell(1);
		cell.setCellValue("rm_status");
		
		for(int i = 0; i < rmList.size(); i ++) {
			row = rm_sheet.createRow(rowNum++);
			rm = rmList.get(i);
			
			cell = row.createCell(0);
			cell.setCellValue((String) rm.getRMID_Location());
			
			cell = row.createCell(1);
			cell.setCellValue((String) rm.getStatus().toString());
			
		}
		
		XSSFSheet mr_sheet = workbook.createSheet(Room.TABLE_NAME);
		
 		
		return result;
	}

}
