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
import com.ichao.lottery.dict.SequenceType;
import com.ichao.lottery.dto.P3Dto;
import com.ichao.lottery.dto.analyze.SequenceAnalyzeResult;
import com.ichao.lottery.dto.result.AnalyzeResultDto;
import com.ichao.lottery.service.IAnalyzeStrategy;

import lombok.Data;

/**
 * 顺子分析策略
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-20 20:19
 * 
 */
@Service
public class SequenceAnalzyeStrategy implements IAnalyzeStrategy {
    
    /**
     * 全顺
     */
    private SequenceAnalyze full;
    
    /**
     * 半顺
     */
    private SequenceAnalyze half;
    /**
     * 杂顺
     */
    private SequenceAnalyze mixed;
    /**
     * 豹子
     */
    private SequenceAnalyze same;

    @Override
    public void doAnalyze(P3Dto pre, P3Dto curr, AnalyzeResultDto respDto) {
        if (full == null) {
            full = new SequenceAnalyze(SequenceType.FULL);
            half = new SequenceAnalyze(SequenceType.HALF);
            mixed = new SequenceAnalyze(SequenceType.MIXED);
            same = new SequenceAnalyze(SequenceType.SAME);
        }
        // 全顺
        execAnalyze(pre, curr, full, SequenceType.FULL);
        // 半顺
        execAnalyze(pre, curr, half, SequenceType.HALF);
        // 杂顺
        execAnalyze(pre, curr, mixed, SequenceType.MIXED);
        // 豹子
        execAnalyze(pre, curr, same, SequenceType.SAME);
    }

    /**
     * 
     * @param pre
     * @param curr
     * @param sa
     * @param type
     */
    private void execAnalyze(P3Dto pre, P3Dto curr, SequenceAnalyze sa, SequenceType type) {
        if (curr.getSequenceType() != type) {
            sa.setTempMaxLeak(sa.getTempMaxLeak() + 1);
            if (sa.getStartP3Dto() == null) {
                sa.setStartP3Dto(curr);
            }
        } else {
            sa.setConut(sa.getConut() + 1);
            if (sa.getTempMaxLeak() >= sa.getMaxLeak()) {
                sa.setMaxLeak(Math.max(sa.getMaxLeak(), sa.getTempMaxLeak()));
                if (pre != null) {
                    sa.setEndTime(pre.getDrawTime());
                    sa.setEndNo(pre.getDrawNo());
                }
                if (sa.getStartP3Dto() != null) {
                    sa.setStartTime(sa.getStartP3Dto().getDrawTime());
                    sa.setStartNo(sa.getStartP3Dto().getDrawNo());
                }
            }
            sa.setTempMaxLeak(0);
            sa.setStartP3Dto(null);
        }
    }
    
    /**
     * 
     *
     */
    @Override
    public void finish(AnalyzeResultDto respDto) {
        SequenceAnalyzeResult sa = respDto.getSequence();
        sa.setFull(full.toString());
        sa.setHalf(half.toString());
        sa.setMixed(mixed.toString());
        sa.setSame(same.toString());
        full = null;
        half = null;
        mixed = null;
        same = null;
    }
    
    @Data
    class SequenceAnalyze {
        // 顺子类型
        private SequenceType seqType;
        //
        private int conut;
        // 最大遗漏期数
        private int maxLeak = 0;
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
        private int tempMaxLeak;
        @JSONField(deserialize = false, serialize = false)
        private P3Dto startP3Dto;
        
        public SequenceAnalyze(SequenceType seqType){
            this.seqType = seqType;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("count=").append(conut).append(",  ");
            sb.append("maxLeak=").append(maxLeak).append(",  ");
            sb.append("期号：").append(startNo).append(" - ").append(endNo).append(", ");
            sb.append("日期：").append(FjtDateUtils.formatDate(FjtDateUtils.PATTERN_ISO_ON_DATE, startTime)).append(" - ")
              .append(FjtDateUtils.formatDate(FjtDateUtils.PATTERN_ISO_ON_DATE, endTime));
            return sb.toString();
        }
    }

}
