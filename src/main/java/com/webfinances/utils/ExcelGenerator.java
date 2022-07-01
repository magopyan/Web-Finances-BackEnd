package com.webfinances.utils;

import com.webfinances.transaction.Transaction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelGenerator {
    private List<Transaction> transactionList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Transactions");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Amount", style);
        createCell(row, 1, "Date", style);
        createCell(row, 2, "Subcategory", style);
        createCell(row, 3, "Account", style);
        createCell(row, 4, "Note", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }
        else if (value instanceof LocalDate) {
            cell.setCellValue((LocalDate) value);
        }
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void write() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Transaction record : transactionList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, record.getAmount(), style);
            createCell(row, columnCount++, record.getDate().toString(), style);
            createCell(row, columnCount++, record.getSubcategory().getName(), style);
            createCell(row, columnCount++, record.getAccount().getName(), style);
            createCell(row, columnCount++, record.getNote(), style);
        }
    }

    private void writeSummary() {
        int rowCount = 1;

        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeight(16);
        headerStyle.setFont(headerFont);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        Map<String, Double> incomeVsExpenses = new HashMap<>();
        incomeVsExpenses.put("Income", 0.0);
        incomeVsExpenses.put("Expenses", 0.0);
        Map<String, Double> incomeCategories = new HashMap<>();
        Map<String, Double> expenseCategories = new HashMap<>();

        for (Transaction record : transactionList) {
            String category = record.getSubcategory().getCategory().getName();
            Integer coeff = record.getSubcategory().getCategory().getCategoryType().getCoefficient();

            if(coeff.compareTo(Integer.valueOf(-1)) == 0) {
                incomeVsExpenses.put("Expenses", record.getAmount()+incomeVsExpenses.get("Expenses"));
                if(expenseCategories.containsKey(category))
                    expenseCategories.put(category, expenseCategories.get(category)+record.getAmount());
                else
                    expenseCategories.put(category, record.getAmount());
            }
            if(record.getSubcategory().getCategory().getCategoryType().getCoefficient().compareTo(Integer.valueOf(1)) == 0) {
                incomeVsExpenses.put("Income", record.getAmount() + incomeVsExpenses.get("Income"));
                if(incomeCategories.containsKey(category))
                    incomeCategories.put(category, incomeCategories.get(category)+record.getAmount());
                else
                    incomeCategories.put(category, record.getAmount());
            }

            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getAmount(), style);
            createCell(row, columnCount++, record.getDate().toString(), style);
            createCell(row, columnCount++, record.getSubcategory().getName(), style);
            createCell(row, columnCount++, record.getAccount().getName(), style);
            createCell(row, columnCount++, record.getNote(), style);
        }

        sheet.createRow(rowCount++);
        sheet.createRow(rowCount++);
        sheet.createRow(rowCount++);
        sheet.createRow(rowCount++);
        Row row = sheet.createRow(rowCount++);
        createCell(row, 0, "SUMMARY", headerStyle);
        sheet.createRow(rowCount++);

        row = sheet.createRow(rowCount++);
        createCell(row, 0, "Income vs Expenses", headerStyle);
        row = sheet.createRow(rowCount++);
        createCell(row, 0, "Income", style);
        createCell(row, 1, incomeVsExpenses.get("Income"), style);
        row = sheet.createRow(rowCount++);
        createCell(row, 0, "Expenses", style);
        createCell(row, 1, incomeVsExpenses.get("Expenses"), style);

        sheet.createRow(rowCount++);
        row = sheet.createRow(rowCount++);
        createCell(row, 0, "Income by Categories", headerStyle);
        for(Map.Entry<String, Double> entry: incomeCategories.entrySet()) {
            row = sheet.createRow(rowCount++);
            createCell(row, 0, entry.getKey(), style);
            createCell(row, 1, entry.getValue(), style);
        }

        sheet.createRow(rowCount++);
        row = sheet.createRow(rowCount++);
        createCell(row, 0, "Expenses by Categories", headerStyle);
        for(Map.Entry<String, Double> entry: expenseCategories.entrySet()) {
            row = sheet.createRow(rowCount++);
            createCell(row, 0, entry.getKey(), style);
            createCell(row, 1, entry.getValue(), style);
        }
    }

    public void generate(HttpServletResponse response) throws IOException {
        writeHeader();
        writeSummary();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}

