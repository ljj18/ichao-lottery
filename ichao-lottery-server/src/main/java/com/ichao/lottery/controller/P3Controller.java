/**
 * 文件名称:          			ContractFieldController.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fjt.common.base.dto.ResponseData;
import com.ichao.lottery.dto.condition.AnalyzeConditionDto;
import com.ichao.lottery.dto.condition.ShrinkConditionDto;
import com.ichao.lottery.dto.condition.SimulateConditionDto;
import com.ichao.lottery.service.P3Service;

/**
 * 
 * 
 * Version 1.0.0
 * 
 * @author FPM0393
 * 
 * Date 2021-04-22 14:53
 * 
 */
@RestController
@RequestMapping(value = "/lottery/p3")
@ResponseBody
public class P3Controller {

    @Autowired
    private P3Service p3Service;

    @PostMapping(value = "/number/refresh") 
    public ResponseData<? extends Object> refreshNumber() {
        return ResponseData.success(p3Service.refreshNumber());
    }
    
    @PostMapping(value = "/number/shrink") 
    public ResponseData<? extends Object> shrink(@RequestBody ShrinkConditionDto reqDto) {
        return ResponseData.success(p3Service.shrink(reqDto));
    }
    
    @PostMapping(value = "/number/analyze") 
    public ResponseData<? extends Object> analyzeHistoryData(@RequestBody AnalyzeConditionDto reqDto) {
        return ResponseData.success(p3Service.analyzeHistoryData(reqDto));
    }
    
    @PostMapping(value = "/number/simulate") 
    public ResponseData<? extends Object> simulate(@RequestBody SimulateConditionDto reqDto) {
        return ResponseData.success(p3Service.simulate(reqDto));
    }
}
