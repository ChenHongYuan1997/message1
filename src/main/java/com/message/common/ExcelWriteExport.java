package com.message.common;

import com.message.pojo.SampleInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class ExcelWriteExport {
    public Workbook write(String fileName, String sheetName,
                          String [] henders, List<SampleInfo> sampleInfos){
    //创建工作薄
        Workbook workbook = null;
        if (fileName != null && !"".equals(fileName)){
            if (fileName.endsWith(".xls")){
                workbook = new HSSFWorkbook();
            }else if (fileName.endsWith(".xlsx")){
                workbook = new XSSFWorkbook();
            }else {
                workbook = new HSSFWorkbook();
            }
        }else {
            workbook = new HSSFWorkbook();
        }

        //创建sheet表格
        Sheet sheet = workbook.createSheet(sheetName);

        //创建第一行表头信息
        Row row = sheet.createRow(0);
        for (int i = 0; i <henders.length ; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(henders[i]);
        }
        //创建其他行信息
        for (int i = 0; i < sampleInfos.size() ; i++) {

            Row row1 = sheet.createRow(i + 1);
            Cell cell = row1.createCell(0);
            cell.setCellValue(sampleInfos.get(i).getSampleId());
            Cell cell1 = row1.createCell(1);
            cell1.setCellValue(sampleInfos.get(i).getProvince());
            Cell cell2 = row1.createCell(2);
            cell2.setCellValue(sampleInfos.get(i).getCity());
        }

        return workbook;
    }
}


