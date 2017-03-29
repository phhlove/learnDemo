package com.phh.learnDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 *
 * @Description: 
 * @author phh
 * @date 2016年9月22日
 *
 */
public class ExcelReader {
	
	public static List<Map<String, String>> read(File excelFile, List<String> headers) throws Exception {
		String fileName = excelFile.getName();
		String suffix = getAfterLastDot(fileName);
		if("xls".equalsIgnoreCase(suffix)) {
			// 03
			return read03Excel(excelFile, headers);
		} else if("xlsx".equalsIgnoreCase(suffix)) {
			// 07
			return read07Excel(excelFile, headers);
		} 
		throw new Exception("invalid content type.");
	}
	
	/**
	 * @param excelFile
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	private static List<Map<String, String>> read03Excel(File excelFile, List<String> headers) throws Exception {
		try {
			int headerIndex = 0;
			
			List<Map<String, String>> excelContent = new ArrayList<Map<String,String>>();
			List<String> excelHeaders = new ArrayList<String>();
			
			List<String> tempHeaders = new ArrayList<String>();
			tempHeaders.addAll(headers);
			
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(excelFile));
			int numberOfSheets = workbook.getNumberOfSheets();
			for(int i=0; i<numberOfSheets ; i++) {
				HSSFSheet sheet = workbook.getSheetAt(i);
				HSSFRow headerRow = sheet.getRow(sheet.getFirstRowNum());
				if(headerRow != null) {
					headerIndex = headerRow.getFirstCellNum()+1;
					for(int j=headerRow.getFirstCellNum()+1; j<=headerRow.getLastCellNum(); j++) {
						HSSFCell cell = headerRow.getCell(j);
						if(cell == null) {
							continue;
						}
						String excelHeader = getCellValueAsString(cell).trim();
						if( tempHeaders.contains(excelHeader)) {
							tempHeaders.remove(excelHeader);
							excelHeaders.add(excelHeader);
						} else {
							// 出现多余的Header信息，不合法
							throw new Exception("invalid excel header, not enough information.");
						}
					}
				}
				if(tempHeaders.size() > 0) {
					// 说明没有足够的header信息，不合法
					throw new Exception("invalid excel header, redundant information.");
				}
				
				// 标题是合法的，解析其内容，内容从第二行开始
				for(int j=sheet.getFirstRowNum() + 1; j<=sheet.getLastRowNum(); j++) {
					Map<String, String> values = new LinkedHashMap<String, String>();
					HSSFRow row = sheet.getRow(j);
					if(row != null) {
						for(int k = headerIndex; k<headerIndex + excelHeaders.size(); k++) {
							Cell cell = row.getCell(k);
							String value = getCellValueAsString(cell);
							values.put(excelHeaders.get(k-1), value);
						}
						excelContent.add(values);
					}
				}
			}
			return excelContent;
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * @param excelFile
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	private static List<Map<String, String>> read07Excel(File excelFile, List<String> headers) throws Exception {
		try {
			
			int headerIndex = 0;
			
			List<Map<String, String>> excelContent = new ArrayList<Map<String,String>>();
			List<String> excelHeaders = new ArrayList<String>();
			
			List<String> tempHeaders = new ArrayList<String>();
			tempHeaders.addAll(headers);
			
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(excelFile));
			int numberOfSheets = workbook.getNumberOfSheets();
			for(int i=0; i<numberOfSheets ; i++) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				XSSFRow headerRow = sheet.getRow(sheet.getFirstRowNum());
				if(headerRow != null) {
					headerIndex = headerRow.getFirstCellNum()+1;
					for(int j=headerRow.getFirstCellNum()+1; j<=headerRow.getLastCellNum(); j++) {
						XSSFCell cell = headerRow.getCell(j);
						if(cell == null) {
							continue;
						}
						String excelHeader = getCellValueAsString(cell);
						excelHeader = StringUtils.trim(excelHeader);
						if(tempHeaders.contains(excelHeader)) {
							tempHeaders.remove(excelHeader);
							excelHeaders.add(excelHeader);
						} else {
							// 出现多余的Header信息，不合法
							throw new Exception("invalid excel header, not enough information.");
						}
					}
				}
				if(tempHeaders.size() > 0) {
					// 说明没有足够的header信息，不合法
					throw new Exception("invalid excel header, redundant information.");
				}
				// 标题是合法的，解析其内容，内容从第二行开始
				for(int j=sheet.getFirstRowNum() + 1; j<=sheet.getLastRowNum(); j++) {
					Map<String, String> values = new LinkedHashMap<String, String>();
					XSSFRow row = sheet.getRow(j);
					if(row != null) {
						for(int k = headerIndex; k<headerIndex + excelHeaders.size(); k++) {
							Cell cell = row.getCell(k);
							String value = getCellValueAsString(cell);
							values.put(excelHeaders.get(k-1), value);
						}
						excelContent.add(values);
					}
				}
			}
			return excelContent;
			
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}
	}
	
//	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	/**
	 * @param cell
	 * @return
	 */
	private static String getCellValueAsString(Cell cell) {
		if(cell == null) {
			return null;
		}
		switch(cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_NUMERIC:
//			if ("@".equals(cell.getCellStyle().getDataFormatString()) || 
//					"General".equals(cell.getCellStyle().getDataFormatString())) {
//				BigDecimal b = new BigDecimal(cell.getNumericCellValue());
//				return subZeroAndDot(b.toString());
//			} else {
//				return sdf.format(HSSFDateUtil.getJavaDate(cell
//						.getNumericCellValue()));
//			}
			BigDecimal b = new BigDecimal(cell.getNumericCellValue());
			return subZeroAndDot(b.toString());
		case Cell.CELL_TYPE_BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case Cell.CELL_TYPE_BLANK:
			return "";
		default:
			return cell.toString();
		}
	}
	
	/**
	 * ȥ������0�Լ�.
	 * 
	 * @param s
	 * @return
	 */
	private static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// ȥ�������0
			s = s.replaceAll("[.]$", "");// �����һλ��.��ȥ��
		}
		return s;
	}
	
	
	/**
	 * 获取字符串最后一个点之后的字符串，例：输入 a.jsp，返回 jsp
	 * 
	 * @param input
	 * 
	 * @return 如果输入为空则返回空，如果输入没有点，则返回本身，否则返回最后一个点后面的字符串
	 */
	public static String getAfterLastDot(String input) {
		if (input == null || input.trim().length() < 1) {
			return "";
		}
		int i = input.lastIndexOf(".");
		return i < 0 ? "" : input.substring(i + 1);
	}
}
