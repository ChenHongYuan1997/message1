package com.message.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class UpLoad {

    private static final String PATH="F:\\cd\\upload";

    public static String shanchu(MultipartFile file){
        String PathName=PATH+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+file.getOriginalFilename();
        try {
            file.transferTo(new File(PathName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return PathName;
    }

}
