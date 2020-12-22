package com.message.dao;

import com.message.pojo.AddressCity;

import java.util.List;

public interface AddressCityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AddressCity record);

    int insertSelective(AddressCity record);

    AddressCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AddressCity record);

    int updateByPrimaryKey(AddressCity record);

    /**
     *
     * @param code 获得省
     * @return  根基省编码查询市
     */
    List<AddressCity> selectAddressCityByPrivinceCode(String code);

    String selectAddressCityByName(String name,String provinceCode);
}