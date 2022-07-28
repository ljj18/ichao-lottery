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
 * 012路类型
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-04-26 11:26
 * 
 */
@JSONType(serializer = DictSerializer.class, deserializer = DictDeserializer.class, serializeEnumAsJavaBean = true)
public enum BigSmallType implements IDict {
    
    /**
     * 大数
     */
    SMALL("小", 1, "01234"),
    /**
     * 大数
     */
    BIG("大", 2, "56789"),
    /**
     * 
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
    BigSmallType(String message, int value, String description){
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
