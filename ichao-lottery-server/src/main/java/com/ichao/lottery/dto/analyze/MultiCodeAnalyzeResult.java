/**
 * 文件名称:          			SequenceAnalyze.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto.analyze;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 多码分析数据
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-21 19:34
 * 
 */
@Data
public class MultiCodeAnalyzeResult {

    /**
     * 
     */
    @JSONField(name = "多码匹配总数")
    private int countRepeatCount;
    /**
     * 
     */
    @JSONField(name = "最大遗漏")
    private String leak;
    
    /**
     * 
     */
    @JSONField(name = "最大重复")
    private String continuous;
    
    /**
     * 
     */
    @JSONField(name = "号码出现统计")
    private List<String> numberCountList = new ArrayList<>();
}
