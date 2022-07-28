/**
 * 文件名称:          			ShrinkConditionDto.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto;

import lombok.Data;

/**
 * 包含或排除
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-20 11:44
 * 
 */
@Data
public class IncludeExcludeDto {
    /**
     * 包含的值，与exclude互斥，如果include和exclude都有，则include优先
     */
    private String[] include;
    /**
     * 排除
     */
    private String[] exclude;
}
