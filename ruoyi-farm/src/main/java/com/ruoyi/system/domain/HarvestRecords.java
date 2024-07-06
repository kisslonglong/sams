package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 农场信息对象 harvest_records
 * 
 * @author aw
 * @date 2024-07-03
 */
public class HarvestRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 作物名称 */
    @Excel(name = "作物名称")
    private String cropName;

    /** 产量/公斤 */
    @Excel(name = "产量/公斤")
    private Long yield;

    /** 收获日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收获日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date harvestDate;

    /** 所属农田 */
    @Excel(name = "所属农田")
    private Long ownedFarmlandId;



    private Farmland farmland;


    public Farmland getFarmland() {
        return farmland;
    }

    public void setFarmland(Farmland farmland) {
        this.farmland = farmland;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCropName(String cropName) 
    {
        this.cropName = cropName;
    }

    public String getCropName() 
    {
        return cropName;
    }
    public void setYield(Long yield) 
    {
        this.yield = yield;
    }

    public Long getYield() 
    {
        return yield;
    }
    public void setHarvestDate(Date harvestDate) 
    {
        this.harvestDate = harvestDate;
    }

    public Date getHarvestDate() 
    {
        return harvestDate;
    }
    public void setOwnedFarmlandId(Long ownedFarmlandId) 
    {
        this.ownedFarmlandId = ownedFarmlandId;
    }

    public Long getOwnedFarmlandId() 
    {
        return ownedFarmlandId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cropName", getCropName())
            .append("yield", getYield())
            .append("harvestDate", getHarvestDate())
            .append("ownedFarmlandId", getOwnedFarmlandId())
            .toString();
    }
}
