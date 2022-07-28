/**
 * 文件名称:          			SimulateResultDto.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto.result;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 模拟结果
 * 
 * Version 1.0.0
 * 
 * @author FPM0393
 * 
 * Date 2022-07-22 16:33
 * 
 */
@Data
public class SimulateResultDto {
    
    

    /**
     * 
     */
    @JSONField(name = "支出")
    private long spending;
    /**
     * 
     */
    @JSONField(name = "营收")
    private long revenue;
    /**
     * 
     */
    @JSONField(name = "收益")
    private long income;
    /**
     * 
     */
    @JSONField(name = "匹配次数")
    private int winningCount;
    /**
     * 
     */
    @JSONField(name = "总次数")
    private int total;
    
}
