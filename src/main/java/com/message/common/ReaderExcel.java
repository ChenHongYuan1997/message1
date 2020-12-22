package com.message.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.management.relation.Role;

public class ReaderExcel {
    public List<List<String>> reader(String fileName, InputStream inputStream) throws IOException {
        //创建工作薄

        Workbook workbook = null;
        if (fileName != null && !"".equals(fileName)){
            if (fileName.endsWith(".xls")){
                workbook = new HSSFWorkbook(inputStream);
            }else if (fileName.endsWith(".xlsx")){
                workbook = new XSSFWorkbook(inputStream);
            }else{
                return null;
            }
        }else {
            return null;
        }
        //获取sheet
        Sheet sheetAt =workbook.getSheetAt(0);

        //获取行列数据
        List<List<String>> lists = new ArrayList<>();
        for (int i=0; i< sheetAt.getPhysicalNumberOfRows();i ++){
            List<String> list = new ArrayList<>();
            Row row = sheetAt.getRow(i);
            for (int j=0; j <row.getLastCellNum();j ++){
                Cell cell =row.getCell(j);
                String value ="";
                if ( cell !=null){
                    value =cell.toString();
                }
                    list.add(value);
            }
                    lists.add(list);
        }
        return lists;
    }
}
