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
 * 单双
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-04-26 11:26
 * 
 */
@JSONType(serializer = DictSerializer.class, deserializer = DictDeserializer.class, serializeEnumAsJavaBean = true)
public enum OddEvenType implements IDict {
    
    /**
     * 偶数  number % 2=0
     */
    EVEN("双", 2, "02468"),
    /**
     * 奇数 number % 2=0
     */
    ODD("单", 1, "13579"),
    /**
     * 其它
     */
    OTHER("其它", 100, "其它");

    /**
     * 
     */
    private DictData dict;
    
    /**
     * 
     * @param message
     * @param value
     */
    OddEvenType(String message, int value, String description){
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
