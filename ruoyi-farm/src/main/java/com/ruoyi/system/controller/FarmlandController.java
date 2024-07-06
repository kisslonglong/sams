package com.ruoyi.system.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.service.IFigureSoilService;
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
import com.ruoyi.system.domain.Farmland;
import com.ruoyi.system.service.IFarmlandService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 农田信息管理Controller
 *
 * @author aw
 * @date 2024-07-02
 */
@RestController
@RequestMapping("/system/farmland")
public class FarmlandController extends BaseController
{
    @Autowired
    private IFarmlandService farmlandService;
    @Autowired
    private IFigureSoilService figureSoilService;


    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws IOException {
        ExcelUtil<Farmland> util = new ExcelUtil<Farmland>(Farmland.class);
        List<Farmland> list = util.importExcel(file.getInputStream());
        AtomicLong count = new AtomicLong();
        list.forEach(farmland -> {

                    farmlandService.insertFarmland(farmland);
                    count.getAndIncrement();
                }
        );
        return AjaxResult.success("导入成功", count.get());
    }

    @PostMapping("/template")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<Farmland> util = new ExcelUtil<Farmland>(Farmland.class);
        util.importTemplateExcel(response, "农田信息管理数据");
    }

    @GetMapping("/mapInfo")
    public AjaxResult mapInfo(Farmland farmland){
        return AjaxResult.success(farmlandService.selectFarmlandList(farmland));
    }


    @GetMapping("/all")
    public AjaxResult all(){
        return AjaxResult.success(figureSoilService.selectFigureSoilList(null));
    }

    /**
     * 查询农田信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:farmland:list')")
    @GetMapping("/list")
    public TableDataInfo list(Farmland farmland)
    {
        startPage();
        List<Farmland> list = farmlandService.selectFarmlandList(farmland);
        return getDataTable(list);
    }

    /**
     * 导出农田信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:farmland:export')")
    @Log(title = "农田信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Farmland farmland)
    {
        List<Farmland> list = farmlandService.selectFarmlandList(farmland);
        ExcelUtil<Farmland> util = new ExcelUtil<Farmland>(Farmland.class);
        util.exportExcel(response, list, "农田信息管理数据");
    }

    /**
     * 获取农田信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:farmland:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(farmlandService.selectFarmlandById(id));
    }

    /**
     * 新增农田信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:farmland:add')")
    @Log(title = "农田信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Farmland farmland)
    {
        return toAjax(farmlandService.insertFarmland(farmland));
    }

    /**
     * 修改农田信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:farmland:edit')")
    @Log(title = "农田信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Farmland farmland)
    {
        return toAjax(farmlandService.updateFarmland(farmland));
    }

    /**
     * 删除农田信息管理
     */
    @PreAuthorize("@ss.hasPermi('system:farmland:remove')")
    @Log(title = "农田信息管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(farmlandService.deleteFarmlandByIds(ids));
    }
}
