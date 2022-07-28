/**
 * 文件名称:          			BatchDownloadDto.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.fjt.demo.api.dto;

import java.util.List;

import lombok.Data;

/**
 * TODO: 文件注释
 * 
 * Version 1.0.0
 * 
 * @author FPM0393
 * 
 * Date 2021-08-05 20:22
 * 
 */
@Data
public class DemoDto {

    /**
     * 
     */
    private List<String> ids;
    /**
     * zip文件名称
     */
    private String zipFileName;
    /**
     * zip文件有效期，默认7天
     */
    private int validPeriod;
}
