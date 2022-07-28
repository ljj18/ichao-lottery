/**
 * 文件名称:          			AloneNumberStrategy.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.service.impl.strategy.analyze;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.annotation.JSONField;
import com.fjt.common.base.util.FjtDateUtils;
import com.ichao.lottery.dto.P3Dto;
import com.ichao.lottery.dto.analyze.MultiCodeAnalyzeResult;
import com.ichao.lottery.dto.result.AnalyzeResultDto;
import com.ichao.lottery.service.IAnalyzeStrategy;

import lombok.Data;

/**
 * 二码分析策略
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-20 20:19
 * 
 */
@Service
public class MultiCodeAnalzyeStrategy implements IAnalyzeStrategy {

    
    /**
     * 连续不中N码
     */
    private MultiCodeAnalyze leak;
    
    /**
     * 连续中N码
     */
    private MultiCodeAnalyze continuous;
    
    /**
     * 
     */
    private Map<Integer, NumberCount> numberCountMap;
    /**
     * 
     */
    private NumberCount[] numberCountArray;
    
    @Override
    public void doAnalyze(P3Dto pre, P3Dto curr, AnalyzeResultDto respDto) {
        if (pre == null) {
            return;
        }
        if (numberCountMap == null) {
            leak = new MultiCodeAnalyze();
            continuous = new MultiCodeAnalyze();
            numberCountMap = new HashMap<>();
            numberCountArray = new NumberCount[10];
            for (int i = 0; i <= 9; i++) {
                NumberCount nc = new NumberCount(i);
                numberCountMap.put(i, nc);
                numberCountArray[i] = nc;
            }
        }
        int[] rd = pre.removeDuplicateAndSort();
        for (int j = 0; j <= 9; j++) {
            NumberCount nc = numberCountMap.get(j);
            boolean isMatch = false;
            for (int i = 0; i < rd.length; i++) {
                if (rd[i] == nc.number) {
                    isMatch = true;
                    nc.reset();
                    break;
                }
            }
            if (!isMatch) {
                nc.add();
            }
        }
        MultiCodeAnalyzeResult multiDto = respDto.getMultiCode();
        Arrays.sort(numberCountArray, new Comparator<NumberCount>() {
            @Override
            public int compare(NumberCount one, NumberCount two) {
                return one.getCount() > two.getCount() ? -1 : (one.getCount() == two.getCount() ? 0 : 1);
            }
        });
        int matchCount = 0;
        rd = curr.removeDuplicateAndSort();
        for (int i = 0; i < rd.length; i++) {
            for (int j = 1; j <= 9; j++) {
                if (rd[i] == numberCountArray[j].number) {
                    matchCount++;
                    break;
                }
            }
        }
        // 
        if (matchCount == rd.length) {
            multiDto.setCountRepeatCount(multiDto.getCountRepeatCount() + 1);
        }
        // 遗漏
        execAnalyze(pre, curr, leak, matchCount == rd.length);
        // 连续
        execAnalyze(pre, curr, continuous, matchCount < rd.length);
    }
    
    
    /**
     * 
     * @param pre
     * @param curr
     * @param sa
     * @param type
     */
    private void execAnalyze(P3Dto pre, P3Dto curr, MultiCodeAnalyze tc, boolean isRepeat) {
     // 重复两个号
        if (isRepeat) {
            // 遗漏
            if (tc.getTempCount() >= tc.getCount()) {
                //
                tc.setCount(tc.getTempCount());
                // 结束信息
                tc.setEndTime(pre.getDrawTime());
                tc.setEndNo(pre.getDrawNo());
                // 开始信息
                if (tc.getStartP3Dto() != null) {
                    tc.setStartTime(tc.getStartP3Dto().getDrawTime());
                    tc.setStartNo(tc.getStartP3Dto().getDrawNo());
                }
            }
            tc.setTempCount(0);
            tc.setStartP3Dto(null);
            
        } else {
            tc.setTempCount(tc.getTempCount() + 1);
            if (tc.getStartP3Dto() == null) {
                tc.setStartP3Dto(curr);
            }
        }
    }
    
    /**
     * 
     *
     */
    @Override
    public void finish(AnalyzeResultDto respDto) {
        MultiCodeAnalyzeResult multi = respDto.getMultiCode();
        multi.setLeak(leak.toString());
        multi.setContinuous(continuous.toString());
        for (NumberCount nc: numberCountArray) {
            multi.getNumberCountList().add(nc.getNumber() + ": " + nc.getCount());
            nc.reset();
        }
        leak.reset();
        continuous.reset();
    }
    
    @Data
    class MultiCodeAnalyze {
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
        
        public void reset() {
            count = 0;
            tempCount = 0;
            startTime = null;
            startNo = null;
            endTime = null;
            endNo = null;
            startP3Dto = null;
        }
        
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
    
    /**
     * 
     * @author FPM0393
     *
     */
    @Data
    class NumberCount {
        /**
         * 
         */
        private int number;
        /**
         * 
         */
        private int count;
        
        public NumberCount(int number){
            this.number = number;
        }
        
        
        public void add() {
            count++;
        }
        
        public void reset() {
            count = 0;
        }
    }
}
