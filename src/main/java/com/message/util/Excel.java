package com.message.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class Excel<T> {

    /**
     * 反射实现excel导出
     * @param sheetname
     * @param filename
     * @param heads
     * @param response
     * @param dataset
     * @param pattern
     * @return
     */
    public String Excel(String sheetname, String filename, String[] heads, HttpServletResponse response, Collection<T> dataset, String pattern) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        OutputStream outputStream = null;
        HSSFSheet sheet = workbook.createSheet(sheetname);
        Row rowone = sheet.createRow(0);
        for (int i = 0; i < heads.length; i++) {
            rowone.createCell(i).setCellValue(heads[i]);
        }
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            Row row = sheet.createRow(index);
            T t = it.next();
            //获取到所有的属性
            Field[] fields = t.getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                Cell cell = row.createCell(i);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                Class tCls = t.getClass();
                Method getMethod = null;
                try {
                    getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    String textValue = "";
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value.toString();
                    }
                    cell.setCellValue(textValue);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
        try {
            response.setHeader("Content-disposition", "attachment;filename="+filename);//附件形式下载，文件名叫duty.xls
            outputStream = response.getOutputStream();  //写到客户端
            workbook.write(outputStream);
            /*            wb.write(fileOut);*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}

