/**
 * 文件名称:          			SequenceAnalyze.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto.analyze;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 顺子分析结果
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-21 19:34
 * 
 */
@Data
public class SequenceAnalyzeResult {
    
    @JSONField(name = "全顺")
    private String full;
    @JSONField(name = "半顺")
    private String half;
    @JSONField(name = "杂顺")
    private String mixed;
    @JSONField(name = "豹子")
    private String same;
        
}
