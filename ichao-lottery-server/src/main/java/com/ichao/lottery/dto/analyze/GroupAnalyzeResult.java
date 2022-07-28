/**
 * 文件名称:          			CountAnalyzeData.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto.analyze;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 组选分析结果
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-26 11:33
 * 
 */
@Data
public class GroupAnalyzeResult {

    /**
     * 组三
     */
    @JSONField(name = "组三次数")
    private int ge3;
    
    /**
     * 组六
     */
    @JSONField(name = "组六次数")
    private int ge6;
    /**
     * 豹子
     */
    @JSONField(name = "豹子次数")
    private int same;
}
