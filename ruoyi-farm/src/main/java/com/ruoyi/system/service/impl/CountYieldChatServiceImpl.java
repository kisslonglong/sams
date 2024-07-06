package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.CropData;
import com.ruoyi.system.mapper.CropDataMapper;
import com.ruoyi.system.service.CountYieldChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountYieldChatServiceImpl implements CountYieldChatService {

    @Autowired
    private CropDataMapper cropDataMapper;
    @Override
    public Map<String, Object> countYieldChat() {
        List<CropData> list = cropDataMapper.selectCropDataList();
        Map<String, Object> map = new HashMap<>();
        map.put("year",list.stream().map(CropData::getYear).collect(Collectors.toList()));
        map.put("wheat",list.stream().map(CropData::getWheat).collect(Collectors.toList()));
        map.put("cotton",list.stream().map(CropData::getCotton).collect(Collectors.toList()));
        map.put("corn",list.stream().map(CropData::getCorn).collect(Collectors.toList()));
        return map;
    }
}
