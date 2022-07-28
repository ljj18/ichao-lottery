/**
 * 文件名称:          			BaiAnalzyeStrategy.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.service.impl.strategy.analyze;

import org.springframework.stereotype.Service;

import com.ichao.lottery.dto.P3Dto;
import com.ichao.lottery.dto.analyze.ComplexAnalyzeResult;
import com.ichao.lottery.dto.result.AnalyzeResultDto;
import com.ichao.lottery.service.IAnalyzeStrategy;

/**
 *  综合分析策略
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-26 17:09
 * 
 */
@Service
public class ComplexAnalzyeStrategy implements IAnalyzeStrategy {

    @Override
    public void doAnalyze(P3Dto pre, P3Dto curr, AnalyzeResultDto respDto) {
        ComplexAnalyzeResult complex = respDto.getComplex();
        if (pre != null) {
            if (!curr.getBigSamll().equals(pre.getBigSamll())
                && !curr.getRoadType().equals(pre.getRoadType())
                && !curr.getOddEven().equals(pre.getOddEven())
                /*&& curr.getTotal() != pre.getTotal()*/
                /*&& curr.getSpan() != pre.getSpan()*/) {
                complex.setNoRepeat(complex.getNoRepeat() + 1);
            } else {
                complex.setRepeat(complex.getRepeat() + 1);
            }
        }
    }

    @Override
    public void finish(AnalyzeResultDto respDto) {
        
    }

}
