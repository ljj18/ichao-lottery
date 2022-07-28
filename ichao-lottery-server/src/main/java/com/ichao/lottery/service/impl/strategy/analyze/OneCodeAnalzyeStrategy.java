/**
 * 文件名称:          			AloneNumberStrategy.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.service.impl.strategy.analyze;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.annotation.JSONField;
import com.fjt.common.base.util.FjtDateUtils;
import com.ichao.lottery.dto.P3Dto;
import com.ichao.lottery.dto.analyze.OneCodeAnalyzeResult;
import com.ichao.lottery.dto.result.AnalyzeResultDto;
import com.ichao.lottery.service.IAnalyzeStrategy;

import lombok.Data;

/**
 * 独码分析策略
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-20 20:19
 * 
 */
@Service
public class OneCodeAnalzyeStrategy implements IAnalyzeStrategy {

    
    /**
     * 最大间隔独码期数
     */
    private OneCodeAnalyze leak;
    
    /**
     * 连续下独码期数
     */
    private OneCodeAnalyze continuous;
    
    @Override
    public void doAnalyze(P3Dto pre, P3Dto curr, AnalyzeResultDto respDto) {
        if (pre == null) {
            return;
        }
        if (leak == null) {
            leak = new OneCodeAnalyze();
            continuous = new OneCodeAnalyze();
        }
        OneCodeAnalyzeResult oneDto = respDto.getOneCode();
        
        // 
        boolean isOneRepeatCode = curr.isOneRepeatCode(pre);
        if (isOneRepeatCode) {
            oneDto.setCountRepeatCount(oneDto.getCountRepeatCount() + 1);
        }
        // 遗漏
        execAnalyze(pre, curr, leak, isOneRepeatCode);
        // 连续
        execAnalyze(pre, curr, continuous, !isOneRepeatCode);
    }
    
    
    /**
     * 
     * @param pre
     * @param curr
     * @param one
     * @param isRepeat
     */
    private void execAnalyze(P3Dto pre, P3Dto curr, OneCodeAnalyze one, boolean isRepeat) {
     // 重复两个号
        if (isRepeat) {
            // 遗漏
            if (one.getTempCount() >= one.getCount()) {
                //
                one.setCount(one.getTempCount());
                // 结束信息
                one.setEndTime(pre.getDrawTime());
                one.setEndNo(pre.getDrawNo());
                // 开始信息
                if (one.getStartP3Dto() != null) {
                    one.setStartTime(one.getStartP3Dto().getDrawTime());
                    one.setStartNo(one.getStartP3Dto().getDrawNo());
                }
            }
            one.setTempCount(0);
            one.setStartP3Dto(null);
            
        } else {
            one.setTempCount(one.getTempCount() + 1);
            if (one.getStartP3Dto() == null) {
                one.setStartP3Dto(curr);
            }
        }
    }
    
    /**
     * 
     *
     */
    @Override
    public void finish(AnalyzeResultDto respDto) {
        OneCodeAnalyzeResult one = respDto.getOneCode();
        one.setLeak(leak.toString());
        one.setContinuous(continuous.toString());
        leak = null;
        continuous = null;
    }
    
    @Data
    class OneCodeAnalyze {
        // 期数
        private int count = 0;
        // 开始日期
        @JSONField(format = "yyyy-MM-dd")
        private Date startTime;
        // 开始期号
        private String startNo;
        // 结束日期
        @JSONField(format = "yyyy-MM-dd")
        private Date endTime;
        // 结束期号
        private String endNo;
        //
        @JSONField(deserialize = false, serialize = false)
        private int tempCount;
        @JSONField(deserialize = false, serialize = false)
        private P3Dto startP3Dto;
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("count=").append(count).append(", ");
            sb.append("期号：").append(startNo).append(" - ").append(endNo).append(", ");
            sb.append("日期：").append(FjtDateUtils.formatDate(FjtDateUtils.PATTERN_ISO_ON_DATE, startTime)).append(" - ")
              .append(FjtDateUtils.formatDate(FjtDateUtils.PATTERN_ISO_ON_DATE, endTime));
            return sb.toString();
        }
    }
}
