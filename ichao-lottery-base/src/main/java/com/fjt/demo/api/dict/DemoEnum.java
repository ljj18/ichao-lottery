/**
 * 文件名称:          			SignStageEnum.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.fjt.demo.api.dict;

import com.alibaba.fastjson.annotation.JSONType;
import com.fjt.common.base.dict.DictData;
import com.fjt.common.base.dict.IDict;
import com.fjt.common.base.dict.serializer.DictDeserializer;
import com.fjt.common.base.dict.serializer.DictSerializer;

/**
 * 枚举Demo
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-04-26 11:26
 * 
 */
@JSONType(serializer = DictSerializer.class, deserializer = DictDeserializer.class, serializeEnumAsJavaBean = true)
public enum DemoEnum implements IDict {
    
    WORD("Word", 1, "word文档"),
    EXCEL("Excel", 2, "Excel文档"),
    PPT("PPT", 3, "PPT文档"),
    ZIP("压缩", 4, "压缩文档"),
    PDF("PDF", 6, "PDF文档"),
    TXT("文本", 7, "文本文件"),
    PICTURE("图片", 8, "图片文档"),
    OTHER("其它", 100, "其它文档"); 

    /**
     * 
     */
    private DictData dict;
    
    /**
     * 
     * @param message
     * @param value
     */
    DemoEnum(String message, int value, String description){
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
   
   
   public static DemoEnum getFileTypeEnumByExpandName(String message) {
       for (DemoEnum ft : DemoEnum.class.getEnumConstants()) {
           if (ft.getMessage().contains(message)) {
               return ft;
           }
       }
       return OTHER;
   }
}
