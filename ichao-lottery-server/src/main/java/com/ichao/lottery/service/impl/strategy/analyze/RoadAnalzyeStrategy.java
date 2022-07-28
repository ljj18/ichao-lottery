/**
 * 文件名称:          			BaiAnalzyeStrategy.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.service.impl.strategy.analyze;

import org.springframework.stereotype.Service;

import com.ichao.lottery.dto.P3Dto;
import com.ichao.lottery.dto.analyze.RoadAnalyzeResult;
import com.ichao.lottery.dto.result.AnalyzeResultDto;
import com.ichao.lottery.service.IAnalyzeStrategy;

/**
 * 012路分析策略
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-26 17:09
 * 
 */
@Service
public class RoadAnalzyeStrategy implements IAnalyzeStrategy {

    @Override
    public void doAnalyze(P3Dto pre, P3Dto curr, AnalyzeResultDto respDto) {
        RoadAnalyzeResult road = respDto.getRoad();
        if (pre != null) {
            if (curr.getRoadType().equals(pre.getRoadType())) {
                road.setRepeat(road.getRepeat() + 1);
            } else {
                road.setNoRepeat(road.getNoRepeat() + 1);
            }
        }
    }

    @Override
    public void finish(AnalyzeResultDto respDto) {
        
    }

}
