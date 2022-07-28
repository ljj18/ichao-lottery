/**
 * 文件名称:          			AnalyzeResultDto.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.ichao.lottery.dto.analyze.BaiAnalyzeResult;
import com.ichao.lottery.dto.analyze.BigSmallAnalyzeResult;
import com.ichao.lottery.dto.analyze.ComplexAnalyzeResult;
import com.ichao.lottery.dto.analyze.GeAnalyzeResult;
import com.ichao.lottery.dto.analyze.GroupAnalyzeResult;
import com.ichao.lottery.dto.analyze.MultiCodeAnalyzeResult;
import com.ichao.lottery.dto.analyze.OddEvenAnalyzeResult;
import com.ichao.lottery.dto.analyze.OneCodeAnalyzeResult;
import com.ichao.lottery.dto.analyze.RoadAnalyzeResult;
import com.ichao.lottery.dto.analyze.SequenceAnalyzeResult;
import com.ichao.lottery.dto.analyze.ShiAnalyzeResult;
import com.ichao.lottery.dto.analyze.SpanAnalyzeResult;
import com.ichao.lottery.dto.analyze.TotalAnalyzeResult;
import com.ichao.lottery.dto.analyze.TwoCodeAnalyzeResult;

import lombok.Data;

/**
 * 历史数据分析结果
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-21 15:00
 * 
 */
@Data
public class AnalyzeResultDto {

    /**
     * 
     */
    @JSONField(name="总数", ordinal = 0)
    private int count;
    
    /**
     * 
     */
    @JSONField(name = "百位", ordinal = 10)
    private BaiAnalyzeResult bai = new BaiAnalyzeResult();
    
    /**
     * 
     */
    @JSONField(name = "十位", ordinal = 20)
    private ShiAnalyzeResult shi = new ShiAnalyzeResult();
    /**
     * 
     */
    @JSONField(name = "个位", ordinal = 30)
    private GeAnalyzeResult ge = new GeAnalyzeResult();
    
    /**
     * 
     */
    @JSONField(name = "独码", ordinal = 40)
    private OneCodeAnalyzeResult oneCode = new OneCodeAnalyzeResult();
    
    /**
     * 
     */
    @JSONField(name = "2码", ordinal = 42)
    private TwoCodeAnalyzeResult twoCode = new TwoCodeAnalyzeResult();
    
    /**
     * 
     */
    @JSONField(name = "多码", ordinal = 44)
    private MultiCodeAnalyzeResult multiCode = new MultiCodeAnalyzeResult();

    /**
     * 
     */
    @JSONField(name = "和值", ordinal = 50)
    private TotalAnalyzeResult total = new TotalAnalyzeResult();
    
    /**
     * 
     */
    @JSONField(name = "跨度", ordinal = 60)
    private SpanAnalyzeResult span = new SpanAnalyzeResult();
    
    /**
     * 
     */
    @JSONField(name = "组选", ordinal = 70)
    private GroupAnalyzeResult group = new GroupAnalyzeResult();
    /**
     * 
     */
    @JSONField(name = "顺子", ordinal = 80)
    private SequenceAnalyzeResult sequence = new SequenceAnalyzeResult();
    /**
     * 012路
     */
    @JSONField(name = "012路", ordinal = 90)
    private RoadAnalyzeResult road = new RoadAnalyzeResult();
    /**
     * 奇偶
     */
    @JSONField(name = "奇偶", ordinal = 100)
    private OddEvenAnalyzeResult oddEven = new OddEvenAnalyzeResult();
    
    /**
     * 奇偶
     */
    @JSONField(name = "大小", ordinal = 110)
    private BigSmallAnalyzeResult bigSmall = new BigSmallAnalyzeResult();
    /**
     * 
     */
    @JSONField(name = "综合分析", ordinal = 120)
    private ComplexAnalyzeResult complex = new ComplexAnalyzeResult();
}
