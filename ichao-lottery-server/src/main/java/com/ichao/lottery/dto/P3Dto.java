/**
 * 文件名称:          			P3Dto.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.dto;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.fjt.common.base.dto.BaseDto;
import com.ichao.lottery.dict.GroupElectionType;
import com.ichao.lottery.dict.NumberEnum;
import com.ichao.lottery.dict.SequenceType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 排三Dto
 * 
 * Version 1.0.0
 * 
 * @author FPM0393
 * 
 * Date 2022-07-19 10:58
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class P3Dto extends BaseDto {

    /**
     * 期号
     */
    @JSONField(name = "lotteryDrawNum")
    private String drawNo;
    /**
     * 日期
     */
    @JSONField(name = "lotteryDrawTime", format = "yyyy-MM-dd")
    private Date drawTime;
    /**
     * 开奖结果
     */
    @JSONField(name = "lotteryDrawResult")
    private String drawNumber;

    /**
     * 排序后号码
     */
    @JSONField(deserialize = false)
    private String sortNumber;

    /**
     * 组选类型
     */
    private GroupElectionType groupType;
    /**
     * 顺子类型
     */
    private SequenceType sequenceType;

    /**
     * 百位
     */
    private NumberEnum bai;
    /**
     * 十位数
     */
    private NumberEnum shi;
    /**
     * 个位数
     */
    private NumberEnum ge;

    /**
     * 
     * @return
     */
    public String getSortNumber() {
        if (StringUtils.isEmpty(sortNumber)) {
            int[] a = new int[]{bai.getValue(), shi.getValue(), ge.getValue()};
            Arrays.sort(a);
            sortNumber = String.valueOf(a[0]) + String.valueOf(a[1]) + String.valueOf(a[2]);
        }
        return sortNumber;
    }

    /**
     * 和值
     * 
     * @return
     */
    public int getTotal() {
        return bai.getValue() + shi.getValue() + ge.getValue();
    }

    /**
     * 获得跨度
     * 
     * @return
     */
    public int getSpan() {
        int ab = Math.abs(bai.getValue() - shi.getValue());
        int ac = Math.abs(bai.getValue() - ge.getValue());
        int bc = Math.abs(shi.getValue() - ge.getValue());
        return Math.max(Math.max(ab, ac), bc);
    }

    /**
     * 012路
     * 
     * @return
     */
    public String getRoadType() {
        return bai.getRoadType().getMessage() + shi.getRoadType().getMessage()
            + ge.getRoadType().getMessage();
    }

    /**
     * 大小
     * 
     * @return
     */
    public String getBigSamll() {
        return bai.getBigSmallType().getMessage() + shi.getBigSmallType().getMessage()
            + ge.getBigSmallType().getMessage();
    }

    /**
     * 单双
     * 
     * @return
     */
    public String getOddEven() {
        return bai.getOddEvenType().getMessage() + shi.getOddEvenType().getMessage()
            + ge.getOddEvenType().getMessage();
    }

    /**
     * 判断两个奖号是否有两个重号
     * 
     * @param other
     * @return
     */
    public boolean isTwoRepeatCode(P3Dto other) {
        int[] otherArr = other.removeDuplicateAndSort();
        int[] currArr = removeDuplicateAndSort();
        if (otherArr == null || currArr == null) {
            return false;
        }
        // 其中一个号只有一个数字，则不存在两个重号
        if (otherArr.length == 1 || currArr.length == 1) {
            return false;
        }
        int count = 0;
        for (int a : otherArr) {
            for (int b : currArr) {
                if (a == b) {
                    count++;
                    break;
                }
            }
        }
        return count >= 2;
    }
    
    /**
     * 判断两个奖号是否至少下1个码
     * 
     * @param other
     * @return
     */
    public boolean isOneRepeatCode(P3Dto other) {
        int[] otherArr = other.removeDuplicateAndSort();
        int[] currArr = removeDuplicateAndSort();
        if (otherArr == null || currArr == null) {
            return false;
        }
        for (int a : otherArr) {
            for (int b : currArr) {
                if (a == b) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @return
     */
    public int[] removeDuplicateAndSort() {
        int[] a = null;
        if (getGroupType() == GroupElectionType.GE_1
            || getGroupType() == GroupElectionType.DIRECT) {
            a = new int[] {bai.getValue()};
        } else if (getGroupType() == GroupElectionType.GE_3) {
            if (bai == shi) {
                a = new int[] {bai.getValue(), ge.getValue()};
            } else if (bai == ge){
                a = new int[] {bai.getValue(), shi.getValue()};
            } else if (shi == ge){
                a = new int[] {bai.getValue(), ge.getValue()};
            }
        } else if (getGroupType() == GroupElectionType.GE_6) {
            a = new int[] {bai.getValue(), shi.getValue(), ge.getValue()};
        }
        Arrays.sort(a);
        return a;
    }
}
