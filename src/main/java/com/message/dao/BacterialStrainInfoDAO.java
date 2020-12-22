package com.message.dao;

import com.message.pojo.BacterialStrainInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BacterialStrainInfoDAO继承基类
 */
@Repository
public interface BacterialStrainInfoDAO  extends MyBatisBaseDao<BacterialStrainInfo,Integer>{
    int addBacterialStrainInfoByList(@Param("BacterialStrainInfo")BacterialStrainInfo[] BacterialStrainInfo);

    List<BacterialStrainInfo> findBacterialStrainInfoByMasterId(Integer id);
}
