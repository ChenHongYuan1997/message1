package com.message.dao;

import com.message.Vo.SampleInfoVo;
import com.message.pojo.SampleInfo;
import com.message.pojo.SampleInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SampleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SampleInfo record);

    int insertSelective(SampleInfo record);

   SampleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SampleInfo record);

    int updateByPrimaryKey(SampleInfo record);

    List<SampleInfo> selectAllSamleinfo();

    List<SampleInfo> selectAllSamleinfopage(@Param("startRow")Integer startRow,@Param("pageSize")Integer pagesize);

    int selectAllSamleInfoPagCount(SampleInfoDTO sampleInfoDTO);


    List<SampleInfo> selectAllSamleinfo(SampleInfoDTO sampleInfoDTO, Integer startRow, Integer paSize);

    List<SampleInfoVo> selectAllSampleExcel(@Param("sampleInfoDTO" )SampleInfoDTO sampleInfoDTO);

    List<SampleInfoVo> selectAllSampleExcelByList(@Param("ids") Integer[] ids);

    int insertByExcel(SampleInfoVo sampleInfoVo);

    com.message.Vo.SampleInfoVo selectSampleInfoVo(Integer id);
}