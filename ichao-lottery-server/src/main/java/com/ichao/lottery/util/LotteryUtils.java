/**
 * 文件名称:          			FileApiUtil.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fjt.common.base.dict.DictUtil;
import com.fjt.httpclient.DefaultHttpRequestAdapter;
import com.fjt.httpclient.FjtRequestMethod;
import com.fjt.httpclient.RequestInfo;
import com.ichao.lottery.dict.GroupElectionType;
import com.ichao.lottery.dict.NumberEnum;
import com.ichao.lottery.dict.SequenceType;
import com.ichao.lottery.dto.P3Dto;

import lombok.extern.slf4j.Slf4j;

/**
 *  工具类
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-05-19 08:46
 * 
 */
@Slf4j
public class LotteryUtils {
    
    private static final String SPORT_URL = "https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=35&provinceId=0&pageSize=50&isVerify=1&pageNo=%s";
    /**
     * 直行号池
     */
    public static List<P3Dto> DIRECT_POOL = new ArrayList<>();
    
    /**
     *  组六号池
     */
    public static  List<P3Dto> GE3_POOL = new ArrayList<>();
    
    /**
     *  组六号池
     */
    public static List<P3Dto> GE6_POOL = new ArrayList<>();

    /**
     * 单例
     */
    private static final LotteryUtils INSTANCE = new LotteryUtils();
    
    /**
     * 
     * @return
     */
    public static LotteryUtils instance() {
        return INSTANCE;
    }
    
    public LotteryUtils() {
        init();
    }
    
    /**
     * 初始化
     */
    private void init() {
        // 直选
        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 0; c <= 9; c++) {
                    DIRECT_POOL.add(buildP3Dto(a, b, c));
                }
            }
        }
        log.info("direct, len={}, value={}", DIRECT_POOL.size(), DIRECT_POOL);
        // 组3
        for (int a = 0; a <= 9; a++) {
            for (int b = a + 1; b <= 9; b++) {
                GE3_POOL.add(buildP3Dto(a, a, b));
                GE3_POOL.add(buildP3Dto(a, b, b));
            }
        }
        log.info("GE3, len={}, value={}", GE3_POOL.size(), GE3_POOL);
        // 组六
        for (int a = 0; a <= 9; a++) {
            for (int b = a + 1; b <= 9; b++) {
                for (int c = b + 1; c <= 9; c++) {
                    GE6_POOL.add(buildP3Dto(a, b, c));
                }
            }
        }      
        log.info("GE6, len={}, value={}", GE6_POOL.size(), GE6_POOL);
    }

    /**
     *  获取组选类型
     * @param bai
     * @param shi
     * @param ge
     * @return
     */
    public GroupElectionType getGroupElectionType(NumberEnum bai, NumberEnum shi, NumberEnum ge) {
        if (bai == null || shi == null || ge == null) {
            return GroupElectionType.OTHER;
        }
        // 豹子
        if (bai.getValue() == shi.getValue() && shi.getValue() == ge.getValue()) {
            return GroupElectionType.GE_1;
        }
        // 组三
        if (bai.getValue() == shi.getValue() 
            || bai.getValue() == ge.getValue()
            || shi.getValue() == ge.getValue()) {
            return GroupElectionType.GE_3;
        }
        // 组六
        return GroupElectionType.GE_6;
    }
    
    /**
     * 获取顺子类型
     * @param bai
     * @param shi
     * @param ge
     * @return
     */
    public SequenceType getSequenceType(NumberEnum bai, NumberEnum shi, NumberEnum ge) {
        if (bai == null || shi == null || ge == null) {
            return SequenceType.OTHER;
        }
        int[] a = new int[] {bai.getValue(), shi.getValue(), ge.getValue()};
        Arrays.sort(a);
        // 豹子
        if (a[0] == a[1] && a[1] == a[2]) {
            return SequenceType.SAME;
        }
        // 全顺
        if (a[0] + 1 == a[1] && a[0] + 2 == a[2]) {
            return SequenceType.FULL;
        }
        // 半顺
        if (a[0] + 1 == a[1] || a[1] + 1 == a[2]) {
            return SequenceType.HALF;
        }
        return SequenceType.MIXED;
    }

    /**
     * 下载P3数据
     * 
     * @param pageNo
     * @return
     */
    public List<P3Dto> downloadP3Data(int pageNo) {
        RequestInfo requestInfo = RequestInfo.custom().setUrl(String.format(SPORT_URL, pageNo))
            .build();
        JSONObject json = DefaultHttpRequestAdapter.doSyncRequest(requestInfo,
            FjtRequestMethod.GET);
        JSONObject value = json.getJSONObject("value");
        if (value != null) {
            JSONArray jsonArray = value.getJSONArray("list");
            if (jsonArray != null) {
                return jsonArray.toJavaList(P3Dto.class);
            }
        }
        return null;
    }
    
    /**
     * 
     * @param bai
     * @param shi
     * @param ge
     * @return
     */
    private P3Dto buildP3Dto(int bai, int shi, int ge) {
        P3Dto dto = new P3Dto();
        // 百位
        dto.setBai(DictUtil.getEnumByValue(NumberEnum.class, bai, NumberEnum.OTHER));
        // 十倍
        dto.setShi(DictUtil.getEnumByValue(NumberEnum.class, shi, NumberEnum.OTHER));
        // 个位
        dto.setGe(DictUtil.getEnumByValue(NumberEnum.class, ge, NumberEnum.OTHER));
        // 组选类型
        dto.setGroupType(getGroupElectionType(dto.getBai(), dto.getShi(), dto.getGe()));
        // 顺子类型
        dto.setSequenceType(getSequenceType(dto.getBai(), dto.getShi(), dto.getGe()));
        // 
        dto.setDrawNumber(String.valueOf(bai) + String.valueOf(shi) + String.valueOf(ge));
        
        return dto;
    }
}
