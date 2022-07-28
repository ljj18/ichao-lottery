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
public enum NumberEnum implements IDict {
    
    /**
     * 0
     */
    ZERO("0", 0, "1", BigSmallType.SMALL, OddEvenType.EVEN, RoadType.ROAD_0),
    /**
     * 0
     */
    ONE("1", 1, "1", BigSmallType.SMALL, OddEvenType.ODD, RoadType.ROAD_1),
    /**
     * 0
     */
    TWO("2", 2, "2", BigSmallType.SMALL, OddEvenType.EVEN, RoadType.ROAD_2),
    /**
     * 0
     */
    THREE("3", 3, "3", BigSmallType.SMALL, OddEvenType.ODD, RoadType.ROAD_0),
    /**
     * 0
     */
    FOUR("4", 4, "4", BigSmallType.SMALL, OddEvenType.EVEN, RoadType.ROAD_1),
    /**
     * 0
     */
    FIVE("5", 5, "5", BigSmallType.BIG, OddEvenType.ODD, RoadType.ROAD_2),
    /**
     * 0
     */
    SIX("6", 6, "6", BigSmallType.BIG, OddEvenType.EVEN, RoadType.ROAD_0),
    /**
     * 0
     */
    SEVEN("7", 7, "7", BigSmallType.BIG, OddEvenType.ODD, RoadType.ROAD_1),
    /**
     * 0
     */
    EIGHT("8", 8, "8", BigSmallType.BIG, OddEvenType.EVEN, RoadType.ROAD_2),
    /**
     * 0
     */
    NINE("9", 9, "9", BigSmallType.BIG, OddEvenType.ODD, RoadType.ROAD_0),
    /**
     * 0
     */
    OTHER("100", 100, "100", BigSmallType.OTHER, OddEvenType.EVEN, RoadType.OTHER);

    /**
     * 
     */
    private DictData dict;
    
    /**
     * 大小
     */
    private BigSmallType bigSmallType;
    
    /**
     * 单双
     */
    private OddEvenType oddEvenType;
    
    /**
     * mod3类型
     */
    private RoadType roadType;
    
    /**
     * 
     * @param message
     * @param value
     */
    NumberEnum(String message, int value, String description, 
        BigSmallType bigSmallType, OddEvenType oddEvenType, RoadType roadType){
        dict = new DictData(message, value, description);
        this.bigSmallType = bigSmallType;
        this.oddEvenType = oddEvenType;
        this.roadType = roadType;
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
   public BigSmallType getBigSmallType() {
       return this.bigSmallType;
   }
   
   /**
    * 
    * @return
    */
   public OddEvenType getOddEvenType() {
       return this.oddEvenType;
   }
   
   /**
    * 
    * @return
    */
   public RoadType getRoadType() {
       return this.roadType;
   }
   
}
