package com.message.Vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.message.pojo.BacterialStrainInfo;
import com.message.pojo.SampleToxin;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class SampleInfoVo {
    private Integer id;
    private String sampleId;
    private Integer cropCategoryId;
    private Integer breed;
    private String province;
    private String city;
    private String county;
    private String township;
    private String village;
    private String household;
    @JSONField(
            format = "yyyy-MM-dd"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    private Date harvestTime;
    @JSONField(
            format = "yyyy-MM-dd"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd"
    )
    private Date samplingTime;
    private String samplingPeople;
    private Float pollutionRate;
    private Integer enterpeople;
    private String varieties;
    private String seasonal;
    private String description;
    private List<SampleToxin> listSampleToxin;
    private List<BacterialStrainInfo> listBacterialStrainInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public Integer getCropCategoryId() {
        return cropCategoryId;
    }

    public void setCropCategoryId(Integer cropCategoryId) {
        this.cropCategoryId = cropCategoryId;
    }

    public Integer getBreed() {
        return breed;
    }

    public void setBreed(Integer breed) {
        this.breed = breed;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getHousehold() {
        return household;
    }

    public void setHousehold(String household) {
        this.household = household;
    }

    public Date getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(Date harvestTime) {
        this.harvestTime = harvestTime;
    }

    public Date getSamplingTime() {
        return samplingTime;
    }

    public void setSamplingTime(Date samplingTime) {
        this.samplingTime = samplingTime;
    }

    public String getSamplingPeople() {
        return samplingPeople;
    }

    public void setSamplingPeople(String samplingPeople) {
        this.samplingPeople = samplingPeople;
    }

    public Float getPollutionRate() {
        return pollutionRate;
    }

    public void setPollutionRate(Float pollutionRate) {
        this.pollutionRate = pollutionRate;
    }

    public Integer getEnterpeople() {
        return enterpeople;
    }

    public void setEnterpeople(Integer enterpeople) {
        this.enterpeople = enterpeople;
    }

    public String getVarieties() {
        return varieties;
    }

    public void setVarieties(String varieties) {
        this.varieties = varieties;
    }

    public String getSeasonal() {
        return seasonal;
    }

    public void setSeasonal(String seasonal) {
        this.seasonal = seasonal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SampleToxin> getListSampleToxin() {
        return listSampleToxin;
    }

    public void setListSampleToxin(List<SampleToxin> listSampleToxin) {
        this.listSampleToxin = listSampleToxin;
    }

    public List<BacterialStrainInfo> getListBacterialStrainInfo() {
        return listBacterialStrainInfo;
    }

    public void setListBacterialStrainInfo(List<BacterialStrainInfo> listBacterialStrainInfo) {
        this.listBacterialStrainInfo = listBacterialStrainInfo;
    }

    public void setCropSpecies(String s) {
    }

    public void setTime(String time) {
    }

    public void setBetween(String s) {
    }

    public void setEnd(String s) {
    }
}
