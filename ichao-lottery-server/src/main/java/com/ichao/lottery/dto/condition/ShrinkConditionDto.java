/**
 * 文件名称:          			ShrinkConditionDto.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto.condition;

import com.ichao.lottery.dto.IncludeExcludeDto;

import lombok.Data;

/**
 * 缩水条件
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-21 10:00
 * 
 */
@Data
public class ShrinkConditionDto {
    
    /**
     * 百位缩水条件
     */
    private IncludeExcludeDto bai;
    /**
     * 十位缩水条件
     */
    private IncludeExcludeDto shi;
    /**
     * 个位缩水条件
     */
    private IncludeExcludeDto ge;
    /**
     * 和值缩水条件
     */
    private IncludeExcludeDto total;
    /**
     * 跨度缩水条件
     */
    private IncludeExcludeDto span;
    /**
     * 顺子缩水条件
     */
    private IncludeExcludeDto sequence;
    /**
     * 胆码缩水条件
     */
    private IncludeExcludeDto aloneCode;
    /**
     * 2码缩水条件
     */
    private IncludeExcludeDto twoCode;
    /**
     * 大小缩水条件
     */
    private IncludeExcludeDto bigSmall;
    /**
     * 单双缩水条伯
     */
    private IncludeExcludeDto oddEven;
    /**
     * 组选
     */
    private IncludeExcludeDto group;
    /**
     * 012路
     */
    private IncludeExcludeDto road;
}
