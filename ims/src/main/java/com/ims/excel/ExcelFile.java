package com.ims.excel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.ims.dto.ShowCandidateDTO;
import com.ims.entity.Candidate;
import com.ims.properties.ExcelProperties;

@Service
public class ExcelFile {

	public boolean downloadCandidateExcel(HttpServletResponse response, List<Candidate> candidateList)
			throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=HireCandidate.xlsx");

		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet(ExcelProperties.HIRE_CANDIDATE);

		sheet.setDefaultColumnWidth(30);

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		CellStyle style1 = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName(ExcelProperties.FONT_NAME);

		font.setBold(true);

		style.setFont(font);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);

		style1.setBorderBottom(CellStyle.BORDER_THIN);
		style1.setBorderTop(CellStyle.BORDER_THIN);
		style1.setBorderRight(CellStyle.BORDER_THIN);
		style1.setBorderLeft(CellStyle.BORDER_THIN);

		// create header row
		Row header = sheet.createRow(2);
		header.createCell(0).setCellValue(ExcelProperties.CANDIDATE_NAME);
		header.getCell(0).setCellStyle(style);
		header.createCell(1).setCellValue(ExcelProperties.CANDIDATE_ADDRESS);
		header.getCell(1).setCellStyle(style);
		header.createCell(2).setCellValue(ExcelProperties.CANDIDATE_CONTACT_NUMBER);
		header.getCell(2).setCellStyle(style);
		header.createCell(3).setCellValue(ExcelProperties.CANDIDATE_QUALIFICATION);
		header.getCell(3).setCellStyle(style);
		header.createCell(4).setCellValue(ExcelProperties.CANDIDATE_DESIGNATION);
		header.getCell(4).setCellStyle(style);
		header.createCell(5).setCellValue(ExcelProperties.CANDIDATE_DEPARTMENT);
		header.getCell(5).setCellStyle(style);
		header.createCell(6).setCellValue(ExcelProperties.CANDIDATE_DOB);
		header.getCell(6).setCellStyle(style);
		header.createCell(7).setCellValue(ExcelProperties.CANDIDATE_EMAILID);
		header.getCell(7).setCellStyle(style);
		header.createCell(8).setCellValue(ExcelProperties.CANDIDATE_EXPERIENCE);
		header.getCell(8).setCellStyle(style);
		header.createCell(9).setCellValue(ExcelProperties.CANDIDATE_GENDER);
		header.getCell(9).setCellStyle(style);
		header.createCell(10).setCellValue(ExcelProperties.CANDIDATE_STATUS);
		header.getCell(10).setCellStyle(style);

		int rowCount = 3;

		for (Candidate candidateObject : candidateList) {
			Row userRow = sheet.createRow(rowCount++);
			userRow.createCell(0).setCellValue(candidateObject.getCandidateName());
			userRow.getCell(0).setCellStyle(style1);
			userRow.createCell(1).setCellValue(candidateObject.getAddress());
			userRow.getCell(1).setCellStyle(style1);
			userRow.createCell(2).setCellValue(candidateObject.getContactNumber());
			userRow.getCell(2).setCellStyle(style1);
			userRow.createCell(3).setCellValue(candidateObject.getQualification());
			userRow.getCell(3).setCellStyle(style1);
			userRow.createCell(4).setCellValue(candidateObject.getCandidateDesignation());
			userRow.getCell(4).setCellStyle(style1);
			userRow.createCell(5).setCellValue(candidateObject.getCandidateDepartment());
			userRow.getCell(5).setCellStyle(style1);
			userRow.createCell(6).setCellValue(candidateObject.getDateOfBirth().toString());
			userRow.getCell(6).setCellStyle(style1);
			userRow.createCell(7).setCellValue(candidateObject.getEmailId());
			userRow.getCell(7).setCellStyle(style1);
			userRow.createCell(8).setCellValue(candidateObject.getExperience());
			userRow.getCell(8).setCellStyle(style1);
			userRow.createCell(9).setCellValue(candidateObject.getGender());
			userRow.getCell(9).setCellStyle(style1);
			userRow.createCell(10).setCellValue(candidateObject.getInterviewResult());
			userRow.getCell(10).setCellStyle(style1);
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);

		return true;

	}
	

}
