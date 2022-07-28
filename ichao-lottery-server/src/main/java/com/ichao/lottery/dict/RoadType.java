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
public enum RoadType implements IDict {
    
    /**
     * 0路 number % 3=0
     */
    ROAD_0("0", 0, "0369"),
    /**
     * 1路 number % 3=1
     */
    ROAD_1("1", 1, "147"),
    /**
     * 2路 number % 3=2
     */
    ROAD_2("2", 2, "258"),    
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
    RoadType(String message, int value, String description){
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
