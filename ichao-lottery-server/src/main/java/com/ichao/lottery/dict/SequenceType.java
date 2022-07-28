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
 * 顺子类型
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-04-26 11:26
 * 
 */
@JSONType(serializer = DictSerializer.class, deserializer = DictDeserializer.class, serializeEnumAsJavaBean = true)
public enum SequenceType implements IDict {
    
    /**
     * 全顺
     */
    FULL("全顺", 1, "012, 123, 234, 345, 456, 567, 678, 789, 890, 901"),
    /**
     * 半顺
     */
    HALF("半顺", 2, "013, 013, 014等"), 
    /**
     * 杂顺
     */
    MIXED("杂顺", 3, "024, 026, 028等"),
    /**
     * 豹子
     */
    SAME("豹子", 4, "000, 111, 222, 333, 444, 555, 666, 777, 888, 999"),
    /**
     * 其它
     */
    OTHER("其它", 100, "未知");
    

    /**
     * 
     */
    private DictData dict;
    
    /**
     * 
     * @param message
     * @param value
     */
    SequenceType(String message, int value, String description){
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
