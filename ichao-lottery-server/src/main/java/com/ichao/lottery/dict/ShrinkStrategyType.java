/**
 * 文件名称:          			SignStageEnum.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dict;

import com.alibaba.fastjson.annotation.JSONType;
import com.fjt.common.base.dict.DictData;
import com.fjt.common.base.dict.IDict;
import com.fjt.common.base.dict.serializer.DictDeserializer;
import com.fjt.common.base.dict.serializer.DictSerializer;

/**
 * 缩水策略类型
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-04-26 11:26
 * 
 */
@JSONType(serializer = DictSerializer.class, deserializer = DictDeserializer.class, serializeEnumAsJavaBean = true)
public enum ShrinkStrategyType implements IDict {
    
    /**
     * 百位策略
     */
    BAI("百位", 1, "百位必须包含指定数字"),
    /**
     * 十位策略
     */
    SHI("十位", 2, "十位必须包含指定数字"),
    /**
     * 组三
     */
    GE("个位", 3, "个位必须包含指定数字"),
    /**
     * 和值策略
     */
    TOTAL("和值", 4, "包含指定和值"),
    /**
     * 跨度策略
     */
    SPAN("跨度", 5, "包含指定跨度"),
    /**
     * 顺子策略
     */
    SEQ("顺子", 6, "包含指定顺子策略"),
    /**
     * 胆码策略
     */
    ALONE("胆码", 7, "包括指定胆码"),
    /**
     * 2码策略
     */
    TWOCODE("2码", 8, "包括指定2码"),
    /**
     * 大小策略
     */
    BIG_SMALL("大小", 9, "包括指定大小"),
    /**
     * 奇偶
     */
    ODD_EVEN("2码", 9, "包括指奇偶"),
    /**
     * 组选
     */
    GROUP("组选", 10, "包括指定组选形态"),
    /**
     *012路
     */
    ROAD("012", 11, "包括指定012路"),
    /**
     * 未知
     */
    OTHER("其知", 100, "未知");
    
    /**
     * 
     */
    private DictData dict;
    
    /**
     * 
     * @param message
     * @param value
     */
    ShrinkStrategyType(String message, int value, String description){
        dict = new DictData(message, value, description);
    }
    
    /**
     * 获取数据字典实例
     * 
     * @return
     */
   public DictData getDictData() {
       return dict;
   }
}
