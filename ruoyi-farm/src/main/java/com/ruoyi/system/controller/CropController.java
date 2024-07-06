package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Crop;
import com.ruoyi.system.service.ICropService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作物管理Controller
 * 
 * @author aw
 * @date 2024-07-03
 */
@RestController
@RequestMapping("/system/crop")
public class CropController extends BaseController
{
    @Autowired
    private ICropService cropService;

    /**
     * 查询作物管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:crop:list')")
    @GetMapping("/list")
    public TableDataInfo list(Crop crop)
    {
        startPage();
        List<Crop> list = cropService.selectCropList(crop);
        return getDataTable(list);
    }

    /**
     * 导出作物管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:crop:export')")
    @Log(title = "作物管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Crop crop)
    {
        List<Crop> list = cropService.selectCropList(crop);
        ExcelUtil<Crop> util = new ExcelUtil<Crop>(Crop.class);
        util.exportExcel(response, list, "作物管理数据");
    }

    /**
     * 获取作物管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:crop:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cropService.selectCropById(id));
    }

    /**
     * 新增作物管理
     */
    @PreAuthorize("@ss.hasPermi('system:crop:add')")
    @Log(title = "作物管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Crop crop)
    {
        return toAjax(cropService.insertCrop(crop));
    }

    /**
     * 修改作物管理
     */
    @PreAuthorize("@ss.hasPermi('system:crop:edit')")
    @Log(title = "作物管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Crop crop)
    {
        return toAjax(cropService.updateCrop(crop));
    }

    /**
     * 删除作物管理
     */
    @PreAuthorize("@ss.hasPermi('system:crop:remove')")
    @Log(title = "作物管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cropService.deleteCropByIds(ids));
    }
}
