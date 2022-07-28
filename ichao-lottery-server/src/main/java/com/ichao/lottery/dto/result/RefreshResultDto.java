/**
 * 文件名称:          			RefreshResultDto.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto.result;

import java.util.List;

import com.ichao.lottery.dto.P3Dto;

import lombok.Data;

/**
 * 刷新结果
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-21 15:12
 * 
 */
@Data
public class RefreshResultDto {

    /**
     * 增加
     */
    private List<P3Dto> newAdd;
    
    /**
     * 新增的总数
     */
    private int count;
    
    /**
     * 最近一期
     */
    private String lastDrawNo;
}
