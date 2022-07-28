/**
 * 文件名称:          			IShrink.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.service;

import org.apache.commons.lang3.StringUtils;

import com.ichao.lottery.dict.GameType;
import com.ichao.lottery.dict.ShrinkStrategyType;
import com.ichao.lottery.dto.IncludeExcludeDto;
import com.ichao.lottery.dto.P3Dto;
import com.ichao.lottery.dto.condition.ShrinkConditionDto;

/**
 * 缩水策略接口
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-20 20:12
 * 
 */
public interface IShrinkStrategy {

    /**
     * 是否满足缩水条件
     * @param p3Dto
     * @param reqDto
     * @return
     */
    boolean doShrink(P3Dto p3Dto, ShrinkConditionDto reqDto);
    
    /**
     * 策略类型
     * @return
     */
    ShrinkStrategyType getShrinkType();
    
    /**
     * 策略接口是否玩法
     */
    boolean isSupport(GameType gameType);
    
    /**
     * 比较条件值和奖金值
     * @param conditionValue
     * @param prizeValue
     * @return
     */
    default boolean compare(String conditionValue, String prizeValue) {
        return prizeValue.contains(conditionValue);
    }
    
    /**
     * 执行条件
     * @param reqDto    包含的值
     * @param value 比较的值
     * @return
     */
    public default boolean matchCondition(IncludeExcludeDto reqDto, String value) {
        // 没有匹配条件
        if (reqDto == null || StringUtils.isEmpty(value)) {
            return true;
        }
        // 包含的值优先
        if (reqDto.getInclude() != null && reqDto.getInclude().length > 0) {
            for (String s : reqDto.getInclude()) {
                if (compare(s, value)) {
                    return true;
                }
            }
            // 与包含的值不匹配
            return false;
        }
        // 排除
        if (reqDto.getExclude() != null && reqDto.getExclude().length > 0) {
            for (String s : reqDto.getExclude()) {
                if (compare(s, value)) {
                    return false;
                }
            }
            // 与排除的值匹配
            return true;
        }
        // 没有过滤条件
        return true;
    }
}
