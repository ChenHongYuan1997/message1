package com.message.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelRead {

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    public static List<List<String>> readExcel(MultipartFile multipartFile){
        List<List<String>> listList=new ArrayList<>();
        //获得文件名
        String fileName = multipartFile.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = multipartFile.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(xls)){
                //2003 xls
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith(xlsx)){
                //2007 及2007以上 xlsx
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
        }
        Sheet sheet=workbook.getSheetAt(0);
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            List<String> list=new ArrayList<>();
            Row row=sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j <row.getLastCellNum() ; j++) {
                Cell cell=row.getCell(j);
                cell.setCellType(CellType.STRING);
                list.add(cell.getStringCellValue());
            }
            listList.add(list);
        }
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listList;
    }
}
