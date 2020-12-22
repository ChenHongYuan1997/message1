package com.message.common;

import com.message.pojo.SampleInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelWrite1 {
    public void write(String path, String fileName, String sheetName,
                      String [] henders, List<List<String>> result) throws IOException {
        //创建工作薄
        Workbook workbook = null;
        OutputStream outputStream = null;
        if (fileName !=null && !"".equals(fileName)){
            if (fileName.endsWith(".xls")){
                workbook = new HSSFWorkbook();
                outputStream =  new FileOutputStream(path+"/"+fileName);
            }else if (fileName.endsWith(".xlsx")){
                workbook = new XSSFWorkbook();
                outputStream = new FileOutputStream(path+"/"+fileName);
            }else {
                workbook = new HSSFWorkbook();
                outputStream = new FileOutputStream(path+"/导出数据.xls");
            }
            //创建sheet表格
            Sheet sheet =workbook.createSheet(sheetName);

            //创建第一行表头信息
            Row row = sheet.createRow(0);
            for (int i=0; i<henders.length; i ++){
                Cell cell =row.createCell(i);
                cell.setCellValue(henders[i]);
            }
            //创建其他行为
            for (int i=0; i <result.size() ;i++){
                List<String> strings= result.get(i);
                Row row1 =sheet.createRow(i+1);
            for (int j=0; j<strings.size(); j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(strings.get(j));
                }
            }
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        }

    }

    public static void main(String[] args) throws Exception{
        String path = "F://";
        String fileName = "导出1.xlsx";
        String sheetName = "first";
        String [] henders = {"样品Id","省","市"};
        List<SampleInfo> sampleInfos = new ArrayList<>();
        for (int i=0; i <10; i ++){
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setSampleId("样品"+i);
            sampleInfo.setProvince("省"+i);
            sampleInfo.setCity("市"+i);
            sampleInfos.add(sampleInfo);
        }
        List<List<String>> lists =new ArrayList<>();
        for (int i=0; i <sampleInfos.size(); i ++){
            List<String> list = new ArrayList<>();
            list.add(sampleInfos.get(i).getSampleId());
            list.add(sampleInfos.get(i).getProvince());
            list.add(sampleInfos.get(i).getCity());
            lists.add(list);
        }
        ExcelWrite excelWrite = new ExcelWrite();
        excelWrite.write(path,fileName,sheetName,henders,sampleInfos);
    }
}

