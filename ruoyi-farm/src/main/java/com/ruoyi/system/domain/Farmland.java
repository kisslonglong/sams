package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 农田信息管理对象 farmland
 * 
 * @author aw
 * @date 2024-07-02
 */
public class Farmland extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 图片 */
    @Excel(name = "图片")
    private String imgUrl;

    /** 面积/亩 */
    @Excel(name = "面积/亩")
    private Long area;

    /** 位置 */
    @Excel(name = "位置")
    private String adress;

    /** 经度 */
    @Excel(name = "经度")
    private double longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private double latitude;

    /** 土壤类型 */
    @Excel(name = "土壤类型")
    private String soilType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setArea(Long area) 
    {
        this.area = area;
    }

    public Long getArea() 
    {
        return area;
    }
    public void setAdress(String adress) 
    {
        this.adress = adress;
    }

    public String getAdress() 
    {
        return adress;
    }
    public void setLongitude(Long longitude) 
    {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setSoilType(String soilType)
    {
        this.soilType = soilType;
    }

    public String getSoilType() 
    {
        return soilType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("imgUrl", getImgUrl())
            .append("area", getArea())
            .append("adress", getAdress())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("soilType", getSoilType())
            .toString();
    }
}
