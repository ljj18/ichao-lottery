/**
 * 文件名称:          			ShrinkNumberDto.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto.result;

import com.ichao.lottery.dict.GameType;

import lombok.Data;

/**
 * 缩水的结果
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-20 13:49
 * 
 */
@Data
public class ShrinkResultDto {
    
    /**
     * 玩法类型
     */
    private GameType gameType;
    /**
     * 缩水的注数
     */
    private int count;
    
    /**
     * 号码
     */
    private String value;
}
