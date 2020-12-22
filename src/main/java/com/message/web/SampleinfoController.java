package com.message.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import com.message.Vo.SampleInfoVo;
import com.message.common.*;
import com.message.pojo.*;
import com.message.service.SampleInfoService;

import com.message.util.Excel;
import com.message.util.ExcelRead;
import com.message.util.UpLoad;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class SampleinfoController {
    @Autowired
    private SampleInfoService sampleInfoService;

    //自定义分页
    /*@RequestMapping(value = "/findAll")
    public String findAll(Model model, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        PageUtil allSampleinfoPage =sampleInfoService.finAllSampleinfoPage(pageNum,pageSize);
        model.addAttribute("pageUtil",allSampleinfoPage);
        return "IM";

    }*/
    /*//分页插件
    @RequestMapping(value = "/findAll")
    public String findAll(Model model, @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        PageInfo<SampleInfo> allSampleInfoPageinfo =sampleInfoService.findAllSampleinfoPageInfo(pageNum,pageSize);
        model.addAttribute("pageInfo",allSampleInfoPageinfo);
        return "IM1";
    }*/

    @ResponseBody
    @RequestMapping(value = "/findAllAjax")
    public PageInfo<SampleInfo> findAll (@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                        SampleInfoVo sampleInfoVo,String wuranluv,String year,String month,String day){
        //分割污染率范围
        if (wuranluv !=null && !"".equals(wuranluv)){
            String [] split = wuranluv.split("-");
            sampleInfoVo.setBetween(split[0]);
            sampleInfoVo.setEnd(split[1]);

        }
        //拼接年月日时间
        String time = "";
        if (year !=null && !"".equals(year)){
            time = year;
            if (month !=null && !"".equals(month)){
                if (month.length()>1){
                        time +="-"+month;
                }else {
                    time +="-0"+month;
                }
                if (day !=null && !"".equals(day)){
                    if (day.length()>1){
                        time +="-"+day;
                    }else {
                        time +="-0"+day;
                    }
                }
            }
        }
        sampleInfoVo.setTime(time);
        PageInfo<SampleInfo> allSampleInfoPageinfo =sampleInfoService.findAllSampleInfoLike(sampleInfoVo,pageNum,pageSize);
        return allSampleInfoPageinfo;
    }


    /**
     *
     * @returnn 查询所有毒素信息
     */
    @RequestMapping("/findAllSampleToxinInfo")
    @ResponseBody
    public List<SampleToxinInfo> findAllSampleToxinInfo() {
        return sampleInfoService.findAllSampleToxinInfo();
    }

    /**
     *
     * @return 查询所有的农作物种类
     */
    @ResponseBody
        @RequestMapping("/findAllCropSpecies")
    public List<CropSpecies> findCropSpecies(){
        List<CropSpecies> list = sampleInfoService.findAllCropSpecies();
        return list;
    }

    @RequestMapping("/findCropCategory")
    @ResponseBody
    public List<CropCategory> findCropCategory(){
        List<CropCategory> list = sampleInfoService.findCropCategory();
        return list;
    }
    //前后端分离   后台处理数据，前端跳转页面
    @RequestMapping("/findAll1")
    @ResponseBody
    public JSONResult<PageUtil> findAll1(Model model, SampleInfoDTO sampleInfoDTO, String wuranluv, String year, String month, String day,
                                        @RequestParam(value = "pageNum",defaultValue = "1" ,required = false) Integer pageNum){
        if (sampleInfoDTO != null){
            if (wuranluv != null && !"".equals(wuranluv)){

                String[] str = wuranluv.split("-");
                sampleInfoDTO.setBegin(str[0]);
                sampleInfoDTO.setEnd(str[1]);

            }

            String time = "";
            if (day != null && !"".equals(day)){
                time += year;
                if (month.length() >1){
                    time +=  "-" + month;
                }else {
                    time +=  "-0" + month;
                }


                if (day.length() >1){
                    time +=  "-" + day;
                }else {
                    time +=  "-0" + day;
                }
            }else if (month != null && !"".equals(month)){
                time += year;
                if (month.length() >1){
                    time +=  "-" + month;
                }else {
                    time +=  "-0" + month;
                }
            }else if (year != null && !"".equals(year)){
                time = year;
            }
            sampleInfoDTO.setTime(time);

        }


        JSONResult<PageUtil> jsonResult = null;
        try {

            PageUtil all = sampleInfoService.findAll(sampleInfoDTO, pageNum, 5);
            jsonResult = new JSONResult<>(Result.SUCCESS,all);

        }catch (Exception e){

            jsonResult = new JSONResult<>(Result.ERROR,null);
        }

        return  jsonResult;

    }



    /**
     *
     * @return 查询所有的的省份
     */
    @RequestMapping("/findAddressProvince")
    @ResponseBody
    public List<AddressProvince> findAddressProvince(){
        return sampleInfoService.findAddressProvince();
    }

    /**
     *
     * @param code   省编码
     * @return
     */
    @RequestMapping("/findAddressCity")
    @ResponseBody
    public List<AddressCity> findAddressCity(String code){
        return sampleInfoService.findAddressCityByPrivinceCode(code);
    }

    /**
     *
     * @param code
     * @return
     */
    @RequestMapping("/findAddressTown")
    @ResponseBody
    public List<AddressTown> findAddressTown(String code){
        return sampleInfoService.findAddressTownByCityCode(code);
    }

    //模糊查询
    @RequestMapping("/findAll")
    public String findAllLike(Model model, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
                              SampleInfoVo sampleInfoVo,String wuranluv,String year,String month,String day){
        //分割污染率范围
        if (wuranluv !=null && !"".equals(wuranluv)){

            String[] split= wuranluv.split("-");
            sampleInfoVo.setBetween(split[0]);
            sampleInfoVo.setEnd(split[1]);
        }
        //拼接年月日时间
             String time ="";
                 if (year !=null && !"".equals(year)){
                      time = year;
                   if (month !=null && !"".equals(month)){
                     if (month.length()>1){
                       time +="-"+month;
                     }else {
                           time +="-0"+month;
                     }
                if (day !=null && !"".equals(day)){
                   if (day.length()>1){
                     time +="-"+day;
                 }else {
                      time +="-0"+day;
                     }

                }
            }
        }
        sampleInfoVo.setTime(time);

        PageInfo<SampleInfo> allSampleInfoLike = sampleInfoService.findAllSampleInfoLike(sampleInfoVo ,pageNum,pageSize);
        model.addAttribute("pageInfo",allSampleInfoLike);
        model.addAttribute("sampleInfoVo",sampleInfoVo);
        model.addAttribute("wuranluv",wuranluv);
        return "IM1";
    }

    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, String fileName, HttpServletRequest request) throws IOException {
        if (fileName != null && !"".equals(fileName)){
            if (!fileName.endsWith(".xls")&& !fileName.endsWith(".xlsx")){

                fileName = fileName + ".xls";
            }
        }else {

            fileName = "导出文件.xls";
        }

        String sheetName = "first";
        String[] henders = {"样品id","省","市"};
        List<SampleInfo> sampleInfos= new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setSampleId("样品"+i);
            sampleInfo.setProvince("省"+i);
            sampleInfo.setCity("市"+i);
            sampleInfos.add(sampleInfo);
        }
        ExcelWriteExport excelWrite = new ExcelWriteExport();
        Workbook write = excelWrite.write(fileName, sheetName, henders, sampleInfos);

        //转码
        fileName = URLEncoder.encode(fileName,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition","attachment;filename="+ fileName);
        //1.设置文件ContentType类型，会自动判断文件下载类型
        response.setContentType("multipart/form-data");
        //创建输出流
        OutputStream out =response.getOutputStream();

        write.write(out);
        out.flush();
        out.close();

    }
    @RequestMapping("/readerExcel")
    @ResponseBody
    public boolean readerExcel(@RequestParam("fileFirst")MultipartFile fileFirst)  {

        try {


        String fileName = fileFirst.getOriginalFilename(); //获取文件名称
        InputStream inputstream = fileFirst.getInputStream();

        ReaderExcel readerExcel = new ReaderExcel();
        List<List<String>> reder = readerExcel.reader(fileName,inputstream);
        List<CropSpecies> cropSpecies = sampleInfoService.findCropSpecies();

        List<SampleToxinInfo> sampleToxinInfos = sampleInfoService.findAllSampleToxinInfo();

        List<String> henders = reder.get(1);

        List<SampleInfo> sampleInfos = new ArrayList<>();
        for (int i = 2; i <reder.size() ; i++) {

            List<String> result = reder.get(i);
            SampleInfo sampleInfo = new SampleInfo();
            //样品信息
            sampleInfo.setSampleId(result.get(0));
            //品种信息
            String cropSpeciesName = result.get(1);
            for (CropSpecies crop : cropSpecies){
                if (crop.getCropSpecies().equals(cropSpeciesName)){
                        sampleInfo.setBreed(crop.getId());
                        break;
                }
            }
        //是否取样
            String flag =result.get(2);
            if ("是".equals(flag)){
                sampleInfo.setFlag(1);
            }else {
                sampleInfo.setFlag(2);
            }
          //加工类型
            sampleInfo.setCropCategoryId((int)Double.parseDouble(result.get(3)));
            //省
            String provinceCode = sampleInfoService.getAddressProvinceByName(result.get(4));
            sampleInfo.setProvince(provinceCode);
            //市
            String cityCode = sampleInfoService.getAddressCityByName(result.get(5),provinceCode);
            sampleInfo.setCity(cityCode);
            //县
            String countyName = result.get(6);
            String townName = "";
            if (countyName !=null && !"".equals(cityCode)){
                if (countyName.length() ==2){
                    if (countyName.endsWith("县") || countyName.endsWith("区")){
                            townName = countyName.substring(0,1)+"  "+countyName.substring(1);
                    }else{
                            townName = countyName;
                    }
                }else{
                    townName = countyName;
                }
            }
            String countyCode = sampleInfoService.getAddressTownByName(townName,cityCode);
            sampleInfo.setCounty(countyCode);

            //乡
            sampleInfo.setTownship(result.get(7));
            //村
            sampleInfo.setVillage(result.get(8));
            //户
            sampleInfo.setHousehold(result.get(9));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            //收货时间
            sampleInfo.setHarvestTime(simpleDateFormat.parse(result.get(10)));
            //取样时间
            sampleInfo.setSamplingTime(simpleDateFormat.parse(result.get(11)));
            //取样人
            sampleInfo.setSamplingPeople(result.get(12));
            //气候特征
            sampleInfo.setSeasonal(result.get(13));
            //环境描述
            sampleInfo.setDescription(result.get(14));
            //真菌污染率
            sampleInfo.setPollutionRate(Float.parseFloat(result.get(15)));

            //默认添加到对象中
            sampleInfo.setIsdel(0);
            sampleInfo.setCreateTime(new Date());

            //17<= <29
            //毒素信息
            int index = 17;
            int end = 29;
            if (result.size() <29){
                    end = result.size();
            }

            //
            List<SampleToxin> sampleToxins = new ArrayList<>();
            for (int j = index; j <end ; j++) {
                String count = result.get(j);
                if (count !=null  && !"".equals(count)){
                    SampleToxin sampleToxin = new SampleToxin();
                    //根据毒素名称查询毒素Id
                    String toxinName = henders.get(j);
                    A:
                    for (int k = 0; k <sampleToxinInfos.size() ; k++) {
                        if (townName.equals(sampleToxinInfos.get(k).getToxinType())){
                            sampleToxin.setToxinId(sampleToxinInfos.get(k).getId());
                            sampleToxin.setToxinCount(Float.parseFloat(count));
                            break A;
                        }
                        
                    }

                    sampleToxins.add(sampleToxin);
                }
            }
                sampleInfo.setSampleToxins(sampleToxins);

            sampleInfos.add(sampleInfo);
        }
        int i = sampleInfoService.saveBeachSampleInfo(sampleInfos);
        System.out.println("失败********************"+i + "条");
            return true;
        }catch (Exception e){
            e.printStackTrace();

            return false;
        }

    }


    @RequestMapping("insert")
    @ResponseBody
    public String insertSampleInfo(@RequestParam("pojo")String pojo,@RequestParam(value = "flieList",required = false)MultipartFile[] flieList){
    String result="主数据增加失败";
    SampleInfoInsertDto sampleInfoInsertDto =  JSONObject.parseObject(pojo,SampleInfoInsertDto.class);
    SampleInfo sampleInfo = new SampleInfo();
    sampleInfo.setSampleId(sampleInfoInsertDto.getSampleId());
    int resultS=sampleInfoService.insertSampleInfo(sampleInfo);
    sampleInfo.getId();//直接拿到id
    SampleToxin[] listSampleToxin =sampleInfoInsertDto.getListSampleToxin();
        for (int i = 0; i <listSampleToxin.length ; i++) {
            listSampleToxin[i].setSampleInfoId(sampleInfo.getId());
        }
        int resyltT=0;
        if (listSampleToxin.length>0){
            resyltT=sampleInfoService.insertSampleToxin(listSampleToxin);
        }
        BacterialStrainInfo[] listBacterialStrainInfo=sampleInfoInsertDto.getListBacterialStrainInfo();
        for (int i = 0; i <listBacterialStrainInfo.length ; i++) {
            listBacterialStrainInfo[i].setSampleInfoId(sampleInfo.getId());
            listBacterialStrainInfo[i].setWordAddr(UpLoad.shanchu(flieList[(i*3)]));
            listBacterialStrainInfo[i].setTxtAddr(UpLoad.shanchu(flieList[(i*3+1)]));
            listBacterialStrainInfo[i].setPictureAddr(UpLoad.shanchu(flieList[(i*3+2)]));
        }
        int resylB=sampleInfoService.addBacterialStrainInfoByList(listBacterialStrainInfo);
        if (resultS>0){
            result="主数据增加成功";
            if (listSampleToxin.length>0){
                result+=String.format(";从数据毒素信息一共%d条，增加成功%d条，失败%d条",listSampleToxin.length,resyltT,listSampleToxin.length-resyltT);
            }
            if (listBacterialStrainInfo.length>0){
                result+=String.format(";从数据铲毒菌株信息一共%d条，增加成功%d条，失败%d条",listSampleToxin.length,resylB,listSampleToxin.length-resylB);
            }
        }
        return JSON.toJSONString(result);
    }


    @RequestMapping("/toLead")
    @ResponseBody
    public String toLead(@RequestParam("flie")MultipartFile[]files){
        int maxSize=0;
        int maxSuccess=0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<SampleToxinInfo> sampleToxins=sampleInfoService.findAllSampleToxinInfo();
        for (MultipartFile file : files) {
            List<List<String>> list= ExcelRead.readExcel(file);
            for (int i = 0; i <list.size() ; i++) {
                List<String> strings=list.get(i);
                SampleInfoVo sampleInfo=new SampleInfoVo();
                sampleInfo.setSampleId(strings.get(0));
                sampleInfo.setProvince(strings.get(1));
                sampleInfo.setCity(strings.get(2));
                sampleInfo.setCounty(strings.get(3));
                sampleInfo.setCropSpecies(strings.get(4));
                try {
                    sampleInfo.setHarvestTime(sdf.parse(strings.get(5)));
                    sampleInfo.setSamplingTime(sdf.parse(strings.get(6)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sampleInfo.setPollutionRate(Float.parseFloat(strings.get(7)));
                maxSize++;
                int result=sampleInfoService.insertByExcel(sampleInfo);
                maxSuccess+=result;
                if (result>0){
                    if (result>0){
                        String[] stringsa=strings.get(8).split(",");
                        List<SampleToxin> list1= new ArrayList<>();
                        for (int i1 = 0; i1 <stringsa.length ; i1++) {
                            SampleToxin sampleToxin= new SampleToxin();
                            sampleToxin.setId(0);
                            sampleToxin.setToxinCount(30F);
                            sampleToxin.setSampleInfoId(Integer.parseInt(sampleInfo.getSampleId()));
                            for (SampleToxinInfo toxin :sampleToxins) {
                                if (toxin.getToxinType().equals(stringsa[i1])){
                                    sampleToxin.setToxinId(toxin.getId());
                                }
                            }
                            list1.add(sampleToxin);
                        }
                            int resyltT=0;
                        if (list1.size()>0) {
                            resyltT=sampleInfoService.insertSampleToxinList(list1);
                        }
                    }
                }
            }

        }
        return JSON.toJSONString(String.format("数据一共%d条，增加成功%d条，失败%d条",maxSize,maxSuccess,maxSize-maxSuccess));
    }
    public String findSampleInfoVOById(@RequestParam("id")Integer id){
        com.message.Vo.SampleInfoVo sampleInfoVo=sampleInfoService.findSampleInfoVoById(id);
        sampleInfoVo.setListBacterialStrainInfo(sampleInfoService.findBacterialStrainInfoByMasterId(id));
        return JSON.toJSONString(sampleInfoVo);
    }
}
