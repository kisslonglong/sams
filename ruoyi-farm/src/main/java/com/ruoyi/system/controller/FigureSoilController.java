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
import com.ruoyi.system.domain.FigureSoil;
import com.ruoyi.system.service.IFigureSoilService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 土壤管理Controller
 * 
 * @author aw
 * @date 2024-07-02
 */
@RestController
@RequestMapping("/system/soil")
public class FigureSoilController extends BaseController
{
    @Autowired
    private IFigureSoilService figureSoilService;

    /**
     * 查询土壤管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:soil:list')")
    @GetMapping("/list")
    public TableDataInfo list(FigureSoil figureSoil)
    {
        startPage();
        List<FigureSoil> list = figureSoilService.selectFigureSoilList(figureSoil);
        return getDataTable(list);
    }

    /**
     * 导出土壤管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:soil:export')")
    @Log(title = "土壤管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FigureSoil figureSoil)
    {
        List<FigureSoil> list = figureSoilService.selectFigureSoilList(figureSoil);
        ExcelUtil<FigureSoil> util = new ExcelUtil<FigureSoil>(FigureSoil.class);
        util.exportExcel(response, list, "土壤管理数据");
    }

    /**
     * 获取土壤管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:soil:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(figureSoilService.selectFigureSoilById(id));
    }

    /**
     * 新增土壤管理
     */
    @PreAuthorize("@ss.hasPermi('system:soil:add')")
    @Log(title = "土壤管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FigureSoil figureSoil)
    {
        return toAjax(figureSoilService.insertFigureSoil(figureSoil));
    }

    /**
     * 修改土壤管理
     */
    @PreAuthorize("@ss.hasPermi('system:soil:edit')")
    @Log(title = "土壤管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FigureSoil figureSoil)
    {
        return toAjax(figureSoilService.updateFigureSoil(figureSoil));
    }

    /**
     * 删除土壤管理
     */
    @PreAuthorize("@ss.hasPermi('system:soil:remove')")
    @Log(title = "土壤管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(figureSoilService.deleteFigureSoilByIds(ids));
    }
}
