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
public enum AmountType implements IDict {
    
    /**
     * 组六
     */
    GROUP_6("组六", 1, "组六，ABC", 173, 280, 300),
    /**
     * 组三
     */
    GROUP_3("组三", 2, "组3，ABB、AAB", 346, 570, 600),
    /**
     * 直选
     */
    DIRECT("直选", 3, "直行，ABC、ACB、BAC、BCA、CAB、CBA", 1040, 1700, 1930),
    /**
     * 未知
     */
    OTHER("其知", 100, "未知", 0, 0, 0);
    
    /**
     * 
     */
    private DictData dict;
    
    /**
     * 官方
     */
    private int officialAmt;
    /**
     *  低频
     */
    private int lowAmt;
    /**
     *  高频
     */
    private int highAmt;
    

    
    /**
     * 
     * @param message
     * @param value
     */
    AmountType(String message, int value, String description, int officialAmt, int lowAmt, int highAmt){
        dict = new DictData(message, value, description);
        this.officialAmt = officialAmt;
        this.lowAmt = lowAmt;
        this.highAmt = highAmt;
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
   public int getOfficialAmt() {
      return this.officialAmt;
   }
   
   /**
    * 
    * @return
    */
   public int getLowAmt() {
      return this.lowAmt;
   }
   
   /**
    * 
    * @return
    */
   public int getHighAmt() {
      return this.highAmt;
   }
}
