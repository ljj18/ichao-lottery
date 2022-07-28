/**
 * 文件名称:          			AloneNumberStrategy.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.service.impl.strategy.shrink;

import org.springframework.stereotype.Service;

import com.ichao.lottery.dict.GameType;
import com.ichao.lottery.dict.ShrinkStrategyType;
import com.ichao.lottery.dto.P3Dto;
import com.ichao.lottery.dto.condition.ShrinkConditionDto;
import com.ichao.lottery.service.IShrinkStrategy;

/**
 * 胆码策略
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-20 20:19
 * 
 */
@Service
public class AloneCodeShrinkStrategy implements IShrinkStrategy {

    @Override
    public boolean doShrink(P3Dto p3Dto, ShrinkConditionDto reqDto) {
        return matchCondition(reqDto.getAloneCode(), p3Dto.getDrawNumber());
    }

    @Override
    public ShrinkStrategyType getShrinkType() {
        return ShrinkStrategyType.ALONE;
    }

    @Override
    public boolean isSupport(GameType gameType) {
        return true;
    }

}
