package com.message.pojo;

import java.util.Date;
import java.util.List;

public class SampleInfo {
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

    private Date harvestTime;

    private Date samplingTime;

    private String samplingPeople;

    private Float pollutionRate;

    private Date createTime;

    private Integer isdel;

    private Date inputTime;

    private Integer flag;

    private Integer enterpeople;

    private String varieties;

    private AddressProvince addressProvince;

    private AddressCity addressCity;

    private AddressTown addressTown;

    private CropSpecies cropSpecies;

    private SampleToxinInfo sampleToxinInfo;
    private List<SampleToxin> sampleToxins;

    private String seasonal;

    private String description;

    public List<SampleToxin> getSampleToxins() {
        return sampleToxins;
    }

    public void setSampleToxins(List<SampleToxin> sampleToxins) {
        this.sampleToxins = sampleToxins;
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

    public AddressProvince getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(AddressProvince addressProvince) {
        this.addressProvince = addressProvince;
    }

    public AddressCity getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(AddressCity addressCity) {
        this.addressCity = addressCity;
    }

    public AddressTown getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(AddressTown addressTown) {
        this.addressTown = addressTown;
    }

    public CropSpecies getCropSpecies() {
        return cropSpecies;
    }

    public void setCropSpecies(CropSpecies cropSpecies) {
        this.cropSpecies = cropSpecies;
    }

    public SampleToxinInfo getSampleToxinInfo() {
        return sampleToxinInfo;
    }

    public void setSampleToxinInfo(SampleToxinInfo sampleToxinInfo) {
        this.sampleToxinInfo = sampleToxinInfo;
    }

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
        this.sampleId = sampleId == null ? null : sampleId.trim();
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
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getTownship() {
        return township;
    }

    public void setTownship(String township) {
        this.township = township == null ? null : township.trim();
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village == null ? null : village.trim();
    }

    public String getHousehold() {
        return household;
    }

    public void setHousehold(String household) {
        this.household = household == null ? null : household.trim();
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
        this.samplingPeople = samplingPeople == null ? null : samplingPeople.trim();
    }

    public Float getPollutionRate() {
        return pollutionRate;
    }

    public void setPollutionRate(Float pollutionRate) {
        this.pollutionRate = pollutionRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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
        this.varieties = varieties == null ? null : varieties.trim();
    }
}