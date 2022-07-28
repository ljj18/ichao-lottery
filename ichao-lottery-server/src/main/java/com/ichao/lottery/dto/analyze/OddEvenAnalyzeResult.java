/**
 * 文件名称:          			CountAnalyzeData.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto.analyze;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 奇偶分析结果
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-26 11:33
 * 
 */
@Data
public class OddEvenAnalyzeResult {

    
    /**
     * 重复
     */
    @JSONField(name = "重复次数")
    private int repeat;
    /**
     * 不重复
     */
    @JSONField(name = "不重复次数")
    private int noRepeat;
}
