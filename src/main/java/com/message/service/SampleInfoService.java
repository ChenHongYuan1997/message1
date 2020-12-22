package com.message.service;

import com.github.pagehelper.PageInfo;
import com.message.Vo.SampleInfoVo;
import com.message.common.PageUtil;
import com.message.pojo.*;

import java.util.List;

public interface SampleInfoService {
    List<SampleInfo> finAllSampleinfo();

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return 自定义分页
     */
    PageUtil finAllSampleinfoPage(Integer pageNum, Integer pageSize);

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return 分页插件
     */
    PageInfo<SampleInfo> findAllSampleinfoPageInfo(Integer pageNum, Integer pageSize);

    /**
     *
     * @return 全部毒素信息
     */
    List<SampleToxinInfo> findAllSampleToxinInfo();

    /**
     *
     * @return 查询所有农作物信息
     */
    List<CropSpecies> findCropSpecies();


    /**
     *
     * @return 查询所有的省
     */
    List<AddressProvince> findAddressProvince();

    /**
     *
     * @param code  获得省编码
     * @return 根据省编码查询市信息
     */
    List<AddressCity> findAddressCityByPrivinceCode(String code);

    /**
     *
     * @param code 获得市编码
     * @return 根据市编码查询县信息
     */
    List<AddressTown> findAddressTownByCityCode(String code);

    String getAddressProvinceByName(String name);

    String getAddressCityByName(String name,String provinceCode);

    String getAddressTownByName(String name,String cityCode);


    int saveBeachSampleInfo(List<SampleInfo> sampleInfos);

    List<CropCategory> findCropCategory();

    List<CropSpecies> findAllCropSpecies();

    List<BacterialStrainInfo> findBacterialStrainInfoByMasterId(Integer var1);

    //添加SampleInfo信息，并返回主键Id
    int insertSampleInfo(SampleInfo sampleInfo);

    //添加SampleToxin集合信息
    int insertSampleToxinList(List<SampleToxin> list);

    int insertSampleToxin(SampleToxin[] listSampleToxin);

    int insertByExcel(SampleInfoVo sampleInfoVo);

    com.message.Vo.SampleInfoVo findSampleInfoVoById(Integer var1);

    int addBacterialStrainInfoByList(BacterialStrainInfo[] BacterialStrainInfo);

    PageUtil findAll(SampleInfoDTO sampleInfoDTO, Integer pageNum, Integer pageSize);

    PageInfo<SampleInfo> findAllSampleInfoLike(SampleInfoVo sampleInfoVo, Integer pageNum, Integer pageSize);
}
