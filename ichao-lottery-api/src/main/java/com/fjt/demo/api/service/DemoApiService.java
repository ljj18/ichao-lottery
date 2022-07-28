/**
 * 文件名称:          			DemoApiService.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.fjt.demo.api.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import com.fjt.common.base.dto.ResponseData;
import com.fjt.demo.api.dto.DemoDto;

import feign.RequestLine;

/**
 * Fegin 接口
 * 
 * Version 1.0.0
 * 
 * @author FPM0393
 * 
 * Date 2021-05-17 16:29
 * 
 */
@FeignClient(name = "${spring.application.name}")
@ConditionalOnClass(name = {"org.springframework.cloud.client.loadbalancer.LoadBalancedRetryFactory"})
public interface DemoApiService {
    
    /**
     * 提供接口服务
     * @param demoDto
     * @return
     */
    @RequestLine("POST /demo/path")
    ResponseData<DemoDto> demoMethod(@RequestBody DemoDto demoDto);
}
