/**
 * 文件名称:          			IAnalyzeStrategy.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.service;

import com.ichao.lottery.dto.P3Dto;
import com.ichao.lottery.dto.result.AnalyzeResultDto;

/**
 * 分析策略接口
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-21 19:41
 * 
 */
public interface IAnalyzeStrategy {

    /**
     * 执行分析
     * @param pre       前一期
     * @param curr      当前期
     * @param respDto   分析结果Dto   
     * @return
     */
    void doAnalyze(P3Dto pre, P3Dto curr, AnalyzeResultDto respDto);
    
    /**
     * 分析已完成，生成分析结果
     * 
     * @param respDto   分析结果响应数据
     */
    void finish(AnalyzeResultDto respDto);
}
