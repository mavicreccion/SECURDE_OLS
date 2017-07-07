package utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.ReadingMaterial;
import model.Room;
import service.ReadingMaterialService;
import service.RoomService;

public class DataExport {
	
	public static boolean exportAll(String filename) {
		boolean result = false;

		XSSFWorkbook workbook = new XSSFWorkbook();
		int rowNum = 0;
		Row row = null;
		Cell cell = null;
		
		////////////////////////////// READING MATERIAL //////////////////////////////
		
		ArrayList<ReadingMaterial> rmList = ReadingMaterialService.getDataForExport();
		ReadingMaterial rm = null;
		
		XSSFSheet rm_sheet = workbook.createSheet(ReadingMaterial.TABLE_RM);
		rm_sheet.autoSizeColumn(0);
		rm_sheet.autoSizeColumn(1);
		System.out.println("Creating sheet");
		
		// create headers
		row = rm_sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("READING MATERIAL");
		cell = row.createCell(1);
		cell.setCellValue("STATUS");
		
		// data
		for(int i = 0; i < rmList.size(); i ++) {
			row = rm_sheet.createRow(rowNum++);
			rm = rmList.get(i);
			
			cell = row.createCell(0);
			cell.setCellValue((String) rm.getRMID_Location());
			
			cell = row.createCell(1);
			cell.setCellValue((String) rm.getStatus().toString());
			
		}
		
		////////////////////////////// MEETING ROOM //////////////////////////////
		
		XSSFSheet mr_sheet = workbook.createSheet(Room.TABLE_NAME);
		mr_sheet.autoSizeColumn(0);
		
		ArrayList<Room> mrList = RoomService.getDataForExport();
		ArrayList<Room> all_rooms = RoomService.getALLRooms();
		
		int[] timeSlots = Utils.getTimeSlots();
		
		
		rowNum = 0;
		
		// create headers
		row = mr_sheet.createRow(rowNum++);
		
		// room header
		cell = row.createCell(0);
		cell.setCellValue("ROOM");
		
		// time header
		for(int i = 1; i < timeSlots.length; i ++) {
			cell = row.createCell(i);
			cell.setCellValue(timeSlots[i-1] + " - " + timeSlots[i]);
			
			mr_sheet.autoSizeColumn(i);
		}
		
		int ctr = 0;
		int col = 0;
		
		// data
		for(int i = 0; i < all_rooms.size(); i ++) {
			row = mr_sheet.createRow(i+1);
			cell = row.createCell(col++);
			cell.setCellValue(mrList.get(ctr).getMr_name());
			
			for(int j = 1; j < timeSlots.length; j ++) {
				cell = row.createCell(col++);
				cell.setCellValue(mrList.get(ctr++).getRoomStatus() + "");
			}
			
			col = 0;
 		}
		
		try {
            FileOutputStream outputStream = new FileOutputStream(filename);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		System.out.println("done");
 		
		return result;
	}

}
