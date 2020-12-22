package com.message.common;

import com.message.pojo.SampleInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelWrite2 <T>{
    public void write(String path, String fileName, String sheetName,
                      String [] henders, List<SampleInfo> result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        //创建工作薄
        Workbook workbook = null;
        OutputStream outputStream = null;
        if (fileName !=null &&!"".equals(fileName)){
            if (fileName.endsWith(".xls")){
                workbook = new HSSFWorkbook();
                outputStream = new FileOutputStream(path+"/"+fileName);
            }else if (fileName.endsWith(".xlsx")){
                workbook = new XSSFWorkbook();
                outputStream = new FileOutputStream(path+"/"+fileName);
            }else {
                workbook = new HSSFWorkbook();
                outputStream = new FileOutputStream(path+"/"+fileName+".xls");
            }
        }else {
            workbook = new HSSFWorkbook();
            outputStream = new FileOutputStream(path+"/导出数据.xls");
        }
        //创建sheet表格
        Sheet sheet =workbook.createSheet(sheetName);

        //创建第一行表头信息
        Row row = sheet.createRow(0);
        for (int i=0; i<henders.length; i++){
            Cell cell =row.createCell(i);
            cell.setCellValue(henders[i]);
        }
        //创建其他信息
        for (int i=0; i< result.size() ;i++){
            Row row1 = sheet.createRow(i+1);
            T t = (T) result.get(i);
            Class<?> aClass = t.getClass();
            Field [] declaredFields =aClass.getDeclaredFields();
            int index = 0;
            for (int j=0; j< declaredFields.length ; j++){
                Cell cell =row1.createCell(index);
                String name =declaredFields[j].getName();
                String getMethod ="get"+name.substring(0,1).toUpperCase()+name.substring(1);
                Method method =aClass.getMethod(getMethod,null);
                Object invoke =method.invoke(t,null);
                String value = "";
                if (invoke !=null){
                    value = invoke.toString();
                    cell.setCellValue(value);
                    index ++;

                }

            }
        }
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static void main(String[] args) throws NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException {
        String path = "F://";
        String fileName = "导出2.xlsx";
        String sheetName = "first";
        String [] henders = {"样品Id","省","市"};
        List<SampleInfo> sampleInfos = new ArrayList<>();
        for (int i=0 ; i<10; i++){
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setSampleId("样品"+i);
            sampleInfo.setProvince("省"+i);
            sampleInfo.setCity("市"+i);
            sampleInfos.add(sampleInfo);
        }
        ExcelWrite2<SampleInfo> excelWrite= new ExcelWrite2();
        excelWrite.write(path,fileName,sheetName,henders,sampleInfos);
    }
}
