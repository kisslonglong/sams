package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作物管理对象 crop
 * 
 * @author aw
 * @date 2024-07-03
 */
public class Crop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 作物类型 */
    @Excel(name = "作物类型")
    private String cropType;

    /** 生长周期 */
    @Excel(name = "生长周期")
    private String livespan;

    /** 产量 */
    @Excel(name = "产量")
    private String yield;

    /** 作物图片 */
    @Excel(name = "作物图片")
    private String imageUrl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCropType(String cropType) 
    {
        this.cropType = cropType;
    }

    public String getCropType() 
    {
        return cropType;
    }
    public void setLivespan(String livespan) 
    {
        this.livespan = livespan;
    }

    public String getLivespan() 
    {
        return livespan;
    }
    public void setYield(String yield) 
    {
        this.yield = yield;
    }

    public String getYield() 
    {
        return yield;
    }
    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cropType", getCropType())
            .append("livespan", getLivespan())
            .append("yield", getYield())
            .append("imageUrl", getImageUrl())
            .toString();
    }
}
