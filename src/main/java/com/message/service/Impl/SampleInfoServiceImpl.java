package com.message.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.message.Vo.SampleInfoVo;
import com.message.common.PageUtil;
import com.message.dao.*;
import com.message.pojo.*;
import com.message.service.SampleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class SampleInfoServiceImpl implements SampleInfoService {

    @Autowired
    private SampleInfoMapper sampleInfoMapper;

    @Autowired
    private SampleToxinInfoMapper sampleToxinInfoMapper;


    @Autowired
    private AddressProvinceMapper addressProvinceMapper;

    @Autowired
    private AddressCityMapper addressCityMapper;

    @Autowired
    private AddressTownMapper addressTownMapper;

    @Autowired
    private CropCategoryMapper cropCategoryMapper;

    @Autowired
    private CropSpeciesMapper cropSpeciesMapper;

    @Autowired
    private BacterialStrainInfoDAO bacterialStrainInfoDAO;


    @Override
    public List<SampleInfo> finAllSampleinfo() {
        return sampleInfoMapper.selectAllSamleinfo();
    }

    @Override
    public PageUtil finAllSampleinfoPage(Integer pageNum, Integer pageSize) {
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageNum(pageNum);
        pageUtil.setPageSize(pageSize);

        //查询分页总条数
        List<SampleInfo> sampleInfos =sampleInfoMapper.selectAllSamleinfopage(pageUtil.getStartRow(),pageSize);
        pageUtil.setList(sampleInfos);

        //总条数
        SampleInfoDTO sampleInfoDTO = new SampleInfoDTO();
        int i=sampleInfoMapper.selectAllSamleInfoPagCount(sampleInfoDTO);
        return pageUtil;
    }

    @Override
    public PageInfo<SampleInfo> findAllSampleinfoPageInfo(Integer pageNum, Integer pageSize) {
        //启动分页插件
        PageHelper.startPage(pageNum,pageSize);
         //查询所有数据
        List<SampleInfo> sampleInfos = sampleInfoMapper.selectAllSamleinfo();
        //将数据封装到分页插件中
        PageInfo<SampleInfo> pageInfo = new PageInfo<>(sampleInfos);
        return pageInfo;
    }

    @Override
    public List<SampleToxinInfo> findAllSampleToxinInfo() {
        return sampleToxinInfoMapper.selectAllSampleToxinInfo();
    }

    @Override
    public List<CropSpecies> findCropSpecies() {
        return cropSpeciesMapper.selectCropSpecies();
    }

    @Override
    public List<AddressProvince> findAddressProvince() {
        return addressProvinceMapper.selectAddressProvince();
    }

    @Override
    public List<AddressCity> findAddressCityByPrivinceCode(String code) {
        return addressCityMapper.selectAddressCityByPrivinceCode(code);
    }

    @Override
    public List<AddressTown> findAddressTownByCityCode(String code) {
        return addressTownMapper.seletAdeeressTownByCityCode(code);
    }


    @Override
    public String getAddressProvinceByName(String name) {
        return addressProvinceMapper.selectAddressProvinceByName(name);
    }

    @Override
    public String getAddressCityByName(String name, String provinceCode) {
        return addressCityMapper.selectAddressCityByName(name,provinceCode);
    }

    @Override
    public String getAddressTownByName(String name, String cityCode) {
        return addressTownMapper.selectAddressTownByName(name,cityCode);
    }
    @Transactional
    @Override
    public int saveBeachSampleInfo(List<SampleInfo> sampleInfos) {
        int i=0;
        for (SampleInfo sampleInfo : sampleInfos){
            int insert = sampleInfoMapper.insert(sampleInfo);
            if (insert >0){
                Integer id= sampleInfo.getId();
                int i1 =  sampleToxinInfoMapper.insertBeach(sampleInfo.getSampleToxins(),id);
                if (i1 <1){
                    i ++;
                }
            }else {
                    i ++;
            }
        }
        return i;
    }

    @Override
    public List<CropCategory> findCropCategory() {
        return cropCategoryMapper.selectCropCategory();
    }

    @Override
    public List<CropSpecies> findAllCropSpecies() {
        return cropSpeciesMapper.selectCropSpecies();
    }

    @Override
    public List<BacterialStrainInfo> findBacterialStrainInfoByMasterId(Integer var1) {
        return bacterialStrainInfoDAO.findBacterialStrainInfoByMasterId(var1);
    }

    @Override
    public SampleInfoVo findSampleInfoVoById(Integer var1) {
        return sampleInfoMapper.selectSampleInfoVo(var1);
    }

    @Override
    public int insertSampleInfo(SampleInfo sampleInfo) {
        return sampleInfoMapper.insert(sampleInfo);
    }

    @Override
    public int insertSampleToxinList(List<SampleToxin> list) {
        return sampleToxinInfoMapper.insertBylist(list);
    }


    @Override
    public int insertSampleToxin(SampleToxin[] sampInfo) {
        return sampleToxinInfoMapper.insertBylist(Arrays.asList(sampInfo));
    }

    @Override
    public int addBacterialStrainInfoByList(BacterialStrainInfo[] BacterialStrainInfo) {
        return bacterialStrainInfoDAO.addBacterialStrainInfoByList(BacterialStrainInfo);
    }

    @Override
    public int insertByExcel(SampleInfoVo sampleInfoVo) {
        return sampleInfoMapper.insertByExcel(sampleInfoVo);
    }

    @Override
    public PageUtil findAll(SampleInfoDTO sampleInfoDTO, Integer pageNum, Integer pageSize) {
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageNum(pageNum);
        pageUtil.setPageSize(pageSize);
        List<SampleInfo> sampleInfos = sampleInfoMapper.selectAllSamleinfo(sampleInfoDTO,pageUtil.getStartRow(),pageSize);
        int i = sampleInfoMapper.selectAllSamleInfoPagCount(sampleInfoDTO);
        pageUtil.setList(sampleInfos);
        pageUtil.setTotal(i);
        return pageUtil;
    }

    @Override
    public PageInfo<SampleInfo> findAllSampleInfoLike(SampleInfoVo sampleInfoVo, Integer pageNum, Integer pageSize) {
        return null;
    }

}

