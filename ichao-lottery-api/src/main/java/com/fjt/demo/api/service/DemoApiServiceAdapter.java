/**
 * 文件名称:          			DemoApiService.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.fjt.demo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fjt.common.base.dto.ResponseData;
import com.fjt.demo.api.dto.DemoDto;

/**
 * Feign API 适合器，应用调用此类提供方法
 * 
 * Version 1.0.0
 * 
 * @author FPM0393
 * 
 * Date 2021-05-17 16:29
 * 
 */
@Service
public class DemoApiServiceAdapter {
    
    @Autowired
    private DemoApiService apiService;
    
    /**
     * 提供接口服务
     * @param demoDto
     * @return
     */
    DemoDto demoMethod(@RequestBody DemoDto demoDto) {
        ResponseData<DemoDto> resp = apiService.demoMethod(demoDto);
        return resp.getData();
    }
}
