/**
 * 文件名称:          			ContractField.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.db.model;

import java.util.Date;

import com.fjt.common.db.annotation.EntityColumn;
import com.fjt.common.db.model.BaseEntity;
import com.fjt.common.dict.DictTypeHandler;
import com.ichao.lottery.dict.GroupElectionType;
import com.ichao.lottery.dict.NumberEnum;
import com.ichao.lottery.dict.SequenceType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * P3
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-04-21 15:55
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class P3 extends BaseEntity {

    /**
     * 期号
     */
    @EntityColumn(column = "draw_no")
    private String drawNo;
    /**
     * 号码
     */
    @EntityColumn(column = "draw_number")
    private String drawNumber;
    /**
     * 日期
     */
    @EntityColumn(column = "draw_time")
    private Date drawTime;
    /**
     * 百位
     */
    @EntityColumn(column = "bai", typeHandler = DictTypeHandler.class)
    private NumberEnum bai;
    /**
     * 十位
     */
    @EntityColumn(column = "shi", typeHandler = DictTypeHandler.class)
    private NumberEnum shi;
    /**
     * 个位
     */
    @EntityColumn(column = "ge", typeHandler = DictTypeHandler.class)
    private NumberEnum ge;
    /**
     * 组选类型
     */
    @EntityColumn(column = "group_type", typeHandler = DictTypeHandler.class)
    private GroupElectionType groupType;
    /**
     * 顺子类型
     */
    @EntityColumn(column = "sequence_type", typeHandler = DictTypeHandler.class)
    private SequenceType sequenceType;
}
