/**
 * 文件名称:          			CountAnalyzeData.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto.analyze;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 和值分析结果
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-26 11:33
 * 
 */
@Data
public class TotalAnalyzeResult {

    /**
     * 向大
     */
    @JSONField(name = "向大次数", ordinal = 10)
    private int toBig;
    /**
     * 向小
     */
    @JSONField(name = "向小次数", ordinal = 20)
    private int toSmall;
    /**
     * 重复
     */
    @JSONField(name = "重复次数", ordinal = 30)
    private int repeat;
    /**
     * 和值统计
     */
    @JSONField(name = "和值统计", ordinal = 40)
    private Map<Integer, Integer> totalCount = new HashMap<>();
}
