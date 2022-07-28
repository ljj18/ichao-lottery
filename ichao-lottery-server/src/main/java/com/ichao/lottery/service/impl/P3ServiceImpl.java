/**
 * 文件名称:          			FileServiceImpl.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjt.common.base.dict.DictUtil;
import com.fjt.common.db.annotation.DaoService;
import com.fjt.common.service.impl.BaseServiceImpl;
import com.fjt.common.util.BeanConvertUtils;
import com.ichao.lottery.db.dao.P3Dao;
import com.ichao.lottery.db.model.P3;
import com.ichao.lottery.dict.AmountType;
import com.ichao.lottery.dict.GameType;
import com.ichao.lottery.dict.NumberEnum;
import com.ichao.lottery.dto.IncludeExcludeDto;
import com.ichao.lottery.dto.P3Dto;
import com.ichao.lottery.dto.condition.AnalyzeConditionDto;
import com.ichao.lottery.dto.condition.ShrinkConditionDto;
import com.ichao.lottery.dto.condition.SimulateConditionDto;
import com.ichao.lottery.dto.result.AnalyzeResultDto;
import com.ichao.lottery.dto.result.RefreshResultDto;
import com.ichao.lottery.dto.result.ShrinkResultDto;
import com.ichao.lottery.dto.result.SimulateResultDto;
import com.ichao.lottery.dto.search.P3Search;
import com.ichao.lottery.service.IAnalyzeStrategy;
import com.ichao.lottery.service.IShrinkStrategy;
import com.ichao.lottery.service.P3Service;
import com.ichao.lottery.util.LotteryUtils;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 
 * Version 1.0.0
 * 
 * @author FPM0393
 * 
 * Date 2021-05-14 10:16
 * 
 */
@Service
@Slf4j
@DaoService(entityClass = P3.class, dtoClass = P3Dto.class)
public class P3ServiceImpl extends BaseServiceImpl<P3Dto, P3Dao, P3Search> implements P3Service {

    @Autowired
    private IShrinkStrategy[] shrinkStrategy;

    @Autowired
    private IAnalyzeStrategy[] analyzeStrategy;

    /**
     * 
     */
    private List<P3Dto> allDataCache;

    /**
     * 
     * @return
     */
    public List<P3Dto> findAll() {
        if (allDataCache == null) {
            allDataCache = BeanConvertUtils.instance().batchConvert(dao.findAll(), P3Dto.class);
        }
        return allDataCache;
    }

    @Override
    public RefreshResultDto refreshNumber() {
        log.info("开始刷新数据");
        allDataCache = null;
        RefreshResultDto respDto = new RefreshResultDto();
        // 新增
        P3 lastP3 = dao.findLastP3();
        if (lastP3 != null) {
            respDto.setLastDrawNo(lastP3.getDrawNo());
        }
        List<P3Dto> newAdd = new ArrayList<>();
        respDto.setNewAdd(newAdd);
        boolean abort = false;
        for (int pageNo = 1; pageNo < Integer.MAX_VALUE && !abort; pageNo++) {
            List<P3Dto> p3DtoList = LotteryUtils.instance().downloadP3Data(pageNo);
            // 没有获取到数据
            if (p3DtoList == null || p3DtoList.isEmpty()) {
                break;
            }
            for (P3Dto p3Dto : p3DtoList) {
                String drawNum = p3Dto.getDrawNumber();
                // 库里已经保存了最后一期
                if (lastP3 != null && lastP3.getDrawNo().equals(p3Dto.getDrawNo())) {
                    abort = true;
                    break;
                }
                if (dao.countByDrawNo(p3Dto.getDrawNo()) > 0) {
                    continue;
                }
                if (!StringUtils.isEmpty(drawNum)) {
                    String[] sArr = StringUtils.split(drawNum, " ");
                    if (sArr.length == 3) {
                        // 去除空格
                        p3Dto.setDrawNumber(drawNum.replace(" ", ""));
                        p3Dto.setBai(
                            DictUtil.getEnumByMessage(NumberEnum.class, sArr[0], NumberEnum.ZERO));
                        p3Dto.setShi(
                            DictUtil.getEnumByMessage(NumberEnum.class, sArr[1], NumberEnum.ZERO));
                        p3Dto.setGe(
                            DictUtil.getEnumByMessage(NumberEnum.class, sArr[2], NumberEnum.ZERO));
                        // 组选形态
                        p3Dto.setGroupType(LotteryUtils.instance()
                            .getGroupElectionType(p3Dto.getBai(), p3Dto.getShi(), p3Dto.getGe()));
                        // 顺子类型
                        p3Dto.setSequenceType(LotteryUtils.instance()
                            .getSequenceType(p3Dto.getBai(), p3Dto.getShi(), p3Dto.getGe()));
                        // 持久化
                        insert(p3Dto);
                        newAdd.add(p3Dto);
                    }
                }
            }
            log.info("pageNo={}", pageNo);
        }
        respDto.setCount(newAdd.size());
        return respDto;
    }

    /**
     * 
     *
     */
    @Override
    public List<ShrinkResultDto> shrink(ShrinkConditionDto reqDto) {
        ShrinkResultDto direct = doShrink(LotteryUtils.DIRECT_POOL, reqDto, GameType.DIRECT);
        ShrinkResultDto ge3 = doShrink(LotteryUtils.GE3_POOL, reqDto, GameType.GROUP_3);
        ShrinkResultDto ge6 = doShrink(LotteryUtils.GE6_POOL, reqDto, GameType.GROUP_6);
        return Arrays.asList(direct, ge3, ge6);
    }

    @Override
    public AnalyzeResultDto analyzeHistoryData(AnalyzeConditionDto reqDto) {
        AnalyzeResultDto respDto = new AnalyzeResultDto();
        List<P3Dto> allData = findAll();
        // 开始分析
        P3Dto pre = null;
        for (P3Dto dto : allData) {
            for (IAnalyzeStrategy strategy : analyzeStrategy) {
                strategy.doAnalyze(pre, dto, respDto);
            }
            pre = dto;
        }
        // 分析完成
        for (IAnalyzeStrategy strategy : analyzeStrategy) {
            strategy.finish(respDto);
        }
        //
        respDto.setCount(allData.size());
        return respDto;
    }

    /**
     * 模拟
     */
    @Override
    public SimulateResultDto simulate(SimulateConditionDto reqDto) {
        SimulateResultDto respDto = new SimulateResultDto();
        List<P3Dto> allData = findAll();
        P3Dto pre = null;

        ShrinkConditionDto shrinkDto = new ShrinkConditionDto();
        // 和值
        IncludeExcludeDto total = new IncludeExcludeDto();
        shrinkDto.setTotal(total);
        // 跨度
        IncludeExcludeDto span = new IncludeExcludeDto();
        shrinkDto.setSpan(span);
        // 大小
        IncludeExcludeDto bigSmall = new IncludeExcludeDto();
        shrinkDto.setBigSmall(bigSmall);
        // 单双
        IncludeExcludeDto oddEven = new IncludeExcludeDto();
        shrinkDto.setOddEven(oddEven);
        // 012
        IncludeExcludeDto road = new IncludeExcludeDto();
        shrinkDto.setRoad(road);
        // 2码
        IncludeExcludeDto twoCode = new IncludeExcludeDto();
        shrinkDto.setTwoCode(twoCode);
        // 顺子
        IncludeExcludeDto sequence = new IncludeExcludeDto();
        shrinkDto.setSequence(sequence);

        long spending = 0;
        long revenue = 0;

        for (P3Dto dto : allData) {
            if (pre != null) {
                // 和值
                total.setExclude(new String[]{String.valueOf(pre.getTotal())});
                // 跨度
                span.setExclude(new String[]{String.valueOf(pre.getSpan())});
                // 大小
                bigSmall.setExclude(new String[]{pre.getBigSamll()});
                // 单双
                oddEven.setExclude(new String[]{pre.getOddEven()});
                // 012
                road.setExclude(new String[]{pre.getRoadType()});
                // 顺子
                sequence.setExclude(new String[]{"豹子"});
                // 2码
                int[] rd = pre.removeDuplicateAndSort();
                twoCode.setExclude(null);
                if (rd.length == 2) {
                    twoCode.setExclude(new String[]{String.valueOf(rd[0]) + String.valueOf(rd[1])});
                } else if (rd.length == 3) {
                    twoCode.setExclude(new String[]{String.valueOf(rd[0]) + String.valueOf(rd[1]),
                        String.valueOf(rd[0]) + String.valueOf(rd[2]),
                        String.valueOf(rd[1]) + String.valueOf(rd[2])});
                }

                //
                ShrinkResultDto shrinkResultDto = doShrink(LotteryUtils.DIRECT_POOL, shrinkDto,
                    GameType.DIRECT);
                // 支出
                spending += shrinkResultDto.getCount() * 2;
                // 营收
                if (shrinkResultDto.getValue().contains(dto.getDrawNumber())) {
                    revenue += AmountType.DIRECT.getHighAmt();
                    respDto.setWinningCount(respDto.getWinningCount() + 1);
                }
            }
            pre = dto;
        }
        respDto.setSpending(spending);
        respDto.setRevenue(revenue);
        respDto.setIncome(revenue - spending);
        respDto.setTotal(allData.size());
        return respDto;
    }

    /**
     * 
     * @param pool
     * @param reqDto
     * @return
     */
    private ShrinkResultDto doShrink(List<P3Dto> originPool, ShrinkConditionDto reqDto,
        GameType gameType) {
        ShrinkResultDto respDto = new ShrinkResultDto();
        respDto.setGameType(gameType);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (P3Dto dto : originPool) {
            boolean isAdd = true;
            for (IShrinkStrategy strategy : shrinkStrategy) {
                if (strategy.isSupport(gameType) && !strategy.doShrink(dto, reqDto)) {
                    isAdd = false;
                    break;
                }
            }
            if (isAdd) {
                count++;
                sb.append(dto.getDrawNumber()).append(",");
            }
        }
        respDto.setCount(count);
        if (sb.length() > 0) {
            respDto.setValue(sb.substring(0, sb.length() - 1));
        }
        return respDto;
    }
}