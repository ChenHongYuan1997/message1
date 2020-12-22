package com.message.pojo;

public class SampleInfoDTO {
    private String sampleId;

    private  String toxinType;

    private String cropSpecies;

    private String province;

    private String city;

    private  String county;

    private String begin;

    private String end;

    private String time;

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getToxinType() {
        return toxinType;
    }

    public void setToxinType(String toxinType) {
        this.toxinType = toxinType;
    }

    public String getCropSpecies() {
        return cropSpecies;
    }

    public void setCropSpecies(String cropSpecies) {
        this.cropSpecies = cropSpecies;
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

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
