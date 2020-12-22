package com.message.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BacterialStrainInfo implements Serializable {

    /**
     * 产毒菌株id
     */
    private Integer id;

    private Integer sampleInfoId;

    /**
     * 样品id
     */
    private String sampleNum;

    /**
     * 菌株原始编号
     */
    private String originalNum;

    /**
     * 文献信息地址
     */
    private String wordAddr;

    /**
     * 文本地址
     */
    private String txtAddr;

    /**
     * 图片地址
     */
    private String pictureAddr;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 0： 显示  1 ；删除
     */
    private Integer isdel;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSampleInfoId() {
        return sampleInfoId;
    }

    public void setSampleInfoId(Integer sampleInfoId) {
        this.sampleInfoId = sampleInfoId;
    }

    public String getSampleNum() {
        return sampleNum;
    }

    public void setSampleNum(String sampleNum) {
        this.sampleNum = sampleNum;
    }

    public String getOriginalNum() {
        return originalNum;
    }

    public void setOriginalNum(String originalNum) {
        this.originalNum = originalNum;
    }

    public String getWordAddr() {
        return wordAddr;
    }

    public void setWordAddr(String wordAddr) {
        this.wordAddr = wordAddr;
    }

    public String getTxtAddr() {
        return txtAddr;
    }

    public void setTxtAddr(String txtAddr) {
        this.txtAddr = txtAddr;
    }

    public String getPictureAddr() {
        return pictureAddr;
    }

    public void setPictureAddr(String pictureAddr) {
        this.pictureAddr = pictureAddr;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BacterialStrainInfo that = (BacterialStrainInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(sampleInfoId, that.sampleInfoId) &&
                Objects.equals(sampleNum, that.sampleNum) &&
                Objects.equals(originalNum, that.originalNum) &&
                Objects.equals(wordAddr, that.wordAddr) &&
                Objects.equals(txtAddr, that.txtAddr) &&
                Objects.equals(pictureAddr, that.pictureAddr) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(isdel, that.isdel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sampleInfoId, sampleNum, originalNum, wordAddr, txtAddr, pictureAddr, createTime, isdel);
    }

    @Override
    public String toString() {
        return "BacterialStrainInfo{" +
                "id=" + id +
                ", sampleInfoId=" + sampleInfoId +
                ", sampleNum='" + sampleNum + '\'' +
                ", originalNum='" + originalNum + '\'' +
                ", wordAddr='" + wordAddr + '\'' +
                ", txtAddr='" + txtAddr + '\'' +
                ", pictureAddr='" + pictureAddr + '\'' +
                ", createTime=" + createTime +
                ", isdel=" + isdel +
                '}';
    }
}
