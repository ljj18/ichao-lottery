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
 *  组选形态
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-04-26 11:26
 * 
 */
@JSONType(serializer = DictSerializer.class, deserializer = DictDeserializer.class, serializeEnumAsJavaBean = true)
public enum GroupElectionType implements IDict {
    
    /**
     * 组三形态
     */
    GE_3("组三", 1, "组三，AAB", AmountType.GROUP_3),
    /**
     * 组六形态
     */
    GE_6("组六", 2, "组六，ABC", AmountType.GROUP_6),
    /**
     * 豹子形态
     */
    GE_1("豹子", 3, "豹子，AAA", AmountType.DIRECT),
    /**
     * 豹子形态
     */
    DIRECT("直选", 3, "直选，ABC、ACB、BAC、BAC、CAB、CBA", AmountType.DIRECT),
    /**
     * 其它
     */
    OTHER("其它", 100, "其它", AmountType.OTHER);

    /**
     * 
     */
    private DictData dict;
    /**
     *  形态对应的奖金
     */
    private AmountType amount;
    
    /**
     * 
     * @param message
     * @param value
     */
    GroupElectionType(String message, int value, String description, AmountType amount){
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
   public AmountType getAmountType() {
      return this.amount;
   }
}
