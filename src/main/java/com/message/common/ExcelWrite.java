package com.message.common;

import com.message.pojo.SampleInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelWrite {
    //导出 OutPutStream   文件 WorkBook   HssfWorkBook (2003)   XSSFWorkBook (2007)

    //文件名称  fileName  path    sheetName    String [] handers   List<>

    public void write (String path, String fileName, String sheetName,
                       String [] handers, List<SampleInfo> sampleInfos) throws IOException {
        //创建工作薄
        Workbook workbook = null;
        OutputStream outputStream = null;
        if (fileName !=null && !"".equals(fileName)){
            if (fileName.endsWith(".xls")){
                workbook = new HSSFWorkbook();
                outputStream = new FileOutputStream(path+"/"+fileName);
            }else if (fileName.endsWith(".xlsx")){
                workbook = new HSSFWorkbook();
                outputStream = new FileOutputStream(path+"/"+fileName);
            }else{
                workbook = new HSSFWorkbook();
                outputStream = new FileOutputStream(path+"/"+fileName);
            }
        }else {
            workbook = new HSSFWorkbook();
            outputStream = new FileOutputStream(path+"/导出数据.xls");

        }
        //创建sheetname 表格
        Sheet sheet = workbook.createSheet(sheetName);

        //创建第一行表头信息
        Row row = sheet.createRow(0);
        for (int i=0; i<handers.length; i ++){
            Cell cell =row.createCell(i);
            cell.setCellValue(handers[i]);
        }
        //创建其他行信息
        for (int i=0; i<sampleInfos.size(); i ++){
            Row row1 = sheet.createRow(i+1);
            Cell cell =row1.createCell(0);
            cell.setCellValue(sampleInfos.get(i).getSampleId());
            Cell cell1 = row1.createCell(1);
            cell1.setCellValue(sampleInfos.get(i).getProvince());
            Cell cell2 = row1.createCell(2);
            cell2.setCellValue(sampleInfos.get(i).getCity());
        }
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static void main(String[] args) throws IOException {
        String  path = "F://";
        String fileName = "导出.xlsx";
        String sheetName = "first";
        String [] henders = {"样品Id","省","市"};
        List<SampleInfo> sampleInfos = new ArrayList<>();
            for (int i=0; i<10; i ++){
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setSampleId("样品"+i);
            sampleInfo.setProvince("省"+i);
            sampleInfo.setCity("市"+i);
            sampleInfos.add(sampleInfo);
            }
            ExcelWrite excelWrite = new ExcelWrite();
            excelWrite.write(path,fileName,sheetName,henders,sampleInfos);

    }
}
