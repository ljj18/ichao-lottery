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
 * 奖金类型
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-04-26 11:26
 * 
 */
@JSONType(serializer = DictSerializer.class, deserializer = DictDeserializer.class, serializeEnumAsJavaBean = true)
public enum GameType implements IDict {
    
    /**
     * 组六
     */
    GROUP_6("组六", 1, "组六，ABC", 280),
    /**
     * 组三
     */
    GROUP_3("组三", 2, "组3，ABB、AAB", 570),
    /**
     * 直选
     */
    DIRECT("直选", 3, "直行，ABC、ACB、BAC、BCA、CAB、CBA", 1700), 
    /**
     * 未知
     */
    OTHER("其知", 100, "未知", 0);
    
    /**
     * 
     */
    private DictData dict;
    
    /**
     *  形态对应的奖金
     */
    private int amount;
    
    /**
     * 
     * @param message
     * @param value
     */
    GameType(String message, int value, String description, int amount){
        dict = new DictData(message, value, description);
        this.amount = amount;
    }
    
    /**
     * 获取数据字典实例
     * 
     * @return
     */
   public DictData getDictData() {
       return dict;
   }
   
   /**
    * 
    * @return
    */
   public int getAmount() {
      return this.amount;
   }
}
