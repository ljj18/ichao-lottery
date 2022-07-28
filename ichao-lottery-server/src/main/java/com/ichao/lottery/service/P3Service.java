package com.ichao.lottery.service;

import java.util.List;

import com.ichao.lottery.dto.condition.AnalyzeConditionDto;
import com.ichao.lottery.dto.condition.ShrinkConditionDto;
import com.ichao.lottery.dto.condition.SimulateConditionDto;
import com.ichao.lottery.dto.result.AnalyzeResultDto;
import com.ichao.lottery.dto.result.RefreshResultDto;
import com.ichao.lottery.dto.result.ShrinkResultDto;
import com.ichao.lottery.dto.result.SimulateResultDto;

/**
 * 排三数据
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-05-14 10:13
 * 
 */
public interface P3Service {

    /**
     * 刷新开奖数据
     * @param id
     * @return
     */
    RefreshResultDto refreshNumber();
    
    /**
     * 缩水
     * @return
     */
    List<ShrinkResultDto> shrink(ShrinkConditionDto reqDto);
    
    /**
     * 分析历史数据
     * @return
     */
    AnalyzeResultDto analyzeHistoryData(AnalyzeConditionDto reqDto);
    
    /**
     * 模拟
     */
    SimulateResultDto simulate(SimulateConditionDto reqDto);
}
