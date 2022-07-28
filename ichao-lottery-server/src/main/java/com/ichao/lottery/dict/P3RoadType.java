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
public enum P3RoadType implements IDict {
    
    ROAD_000("000", 1, "0369,0369,0369", RoadType.ROAD_0, RoadType.ROAD_0, RoadType.ROAD_0),
    ROAD_001("001", 2, "0369,0369,147",  RoadType.ROAD_0, RoadType.ROAD_0, RoadType.ROAD_1),
    ROAD_002("001", 3, "0369,0369,258",  RoadType.ROAD_0, RoadType.ROAD_0, RoadType.ROAD_2),
    
    ROAD_010("010", 4, "0369,147,0369", RoadType.ROAD_0, RoadType.ROAD_1, RoadType.ROAD_0),
    ROAD_011("011", 5, "0369,147,147", RoadType.ROAD_0, RoadType.ROAD_1, RoadType.ROAD_1),
    ROAD_012("012", 6, "0369,147,258", RoadType.ROAD_0, RoadType.ROAD_1, RoadType.ROAD_2),
    
    ROAD_020("020", 7, "0369,258,0369", RoadType.ROAD_0, RoadType.ROAD_2, RoadType.ROAD_0),
    ROAD_021("021", 8, "0369,258,147", RoadType.ROAD_0, RoadType.ROAD_2, RoadType.ROAD_1),
    ROAD_022("023", 9, "0369,258,258", RoadType.ROAD_0, RoadType.ROAD_2, RoadType.ROAD_2),
    
    ROAD_100("100", 10, "147,0369,0369", RoadType.ROAD_1, RoadType.ROAD_0, RoadType.ROAD_0),
    ROAD_101("101", 11, "147,0369,147", RoadType.ROAD_1, RoadType.ROAD_0, RoadType.ROAD_1),
    ROAD_102("102", 12, "147,0369,258", RoadType.ROAD_1, RoadType.ROAD_0, RoadType.ROAD_2),
    
    ROAD_110("110", 13, "147,147,0369", RoadType.ROAD_1, RoadType.ROAD_1, RoadType.ROAD_0),
    ROAD_111("111", 14, "147,147,147", RoadType.ROAD_1, RoadType.ROAD_1, RoadType.ROAD_1),
    ROAD_112("112", 15, "147,147,258", RoadType.ROAD_1, RoadType.ROAD_1, RoadType.ROAD_2),
    
    ROAD_120("120", 16, "147,258,0369", RoadType.ROAD_1, RoadType.ROAD_2, RoadType.ROAD_0),
    ROAD_121("120", 17, "147,258,147", RoadType.ROAD_1, RoadType.ROAD_2, RoadType.ROAD_1),
    ROAD_122("122", 18, "147,258,258", RoadType.ROAD_1, RoadType.ROAD_2, RoadType.ROAD_2),
    
    ROAD_200("200", 19, "258,0369,0369", RoadType.ROAD_2, RoadType.ROAD_0, RoadType.ROAD_0),
    ROAD_201("201", 20, "258,0369,147", RoadType.ROAD_2, RoadType.ROAD_0, RoadType.ROAD_1),
    ROAD_202("202", 21, "258,0369,258", RoadType.ROAD_2, RoadType.ROAD_0, RoadType.ROAD_2),
    
    ROAD_210("210", 22, "258,147,0369", RoadType.ROAD_2, RoadType.ROAD_1, RoadType.ROAD_0),
    ROAD_211("211", 23, "258,147,147", RoadType.ROAD_2, RoadType.ROAD_1, RoadType.ROAD_1),
    ROAD_212("212", 24, "258,147,258", RoadType.ROAD_2, RoadType.ROAD_1, RoadType.ROAD_2),
    
    ROAD_220("220", 25, "258,258,0369", RoadType.ROAD_2, RoadType.ROAD_2, RoadType.ROAD_0),
    ROAD_221("221", 26, "258,258,147", RoadType.ROAD_2, RoadType.ROAD_2, RoadType.ROAD_1),
    ROAD_222("222", 27, "258,258,258", RoadType.ROAD_2, RoadType.ROAD_2, RoadType.ROAD_2);

    /**
     * 
     */
    private DictData dict;
    
    /**
     * 百位
     */
    private RoadType hundred;
    /**
     * 十位 
     */
    private RoadType ten;
    /**
     * 个位 
     */
    private RoadType one;
    /**
     * 
     * @param message
     * @param value
     */
    P3RoadType(String message, int value, String description, RoadType hundred, RoadType ten, RoadType one){
        dict = new DictData(message, value, description);
        this.hundred = hundred;
        this.ten = ten;
        this.one = one;
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
    * 百位数路
    * @return
    */
   public RoadType getHundred() {
       return this.hundred;
   }
   
   /**
    * 十位数路
    * @return
    */
   public RoadType getTen() {
       return this.ten;
   }
   
   /**
    * 个位数路
    * @return
    */
   public RoadType getOne() {
       return this.one;
   }
   
}
