package com.ichao.lottery.db.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.fjt.common.db.dao.BaseDao;
import com.fjt.common.dict.DictTypeHandler;
import com.ichao.lottery.db.dao.provider.P3Provider;
import com.ichao.lottery.db.model.P3;
import com.ichao.lottery.dict.GroupElectionType;
import com.ichao.lottery.dict.NumberEnum;
import com.ichao.lottery.dict.SequenceType;
import com.ichao.lottery.dto.search.P3Search;

/**
 * 
 * 
 * Version 1.0.0
 * 
 * @author FPM0393
 * 
 * Date 2021-04-21 17:40
 * 
 */
@Mapper
public interface P3Dao extends BaseDao<P3, P3Search> {

    /**
     * 
     * 
     * @param p3
     * @return
     */
    @InsertProvider(type = P3Provider.class, method = "insert")
    boolean insert(P3 p3);

    /**
     * 
     * @param id
     * @return
     */
    @DeleteProvider(type = P3Provider.class, method = "delete")
    int delete(Long id);

    /**
     * 
     * @param p3
     * @return
     */
    @UpdateProvider(type = P3Provider.class, method = "update")
    int update(P3 p3);

    /**
     * 
     * @param id
     * @return
     */
    @Results(id = "p3", value = {
        @Result(id = true, property = "id", column = "id", javaType = Long.class),
        @Result(property = "drawNo", column = "draw_no", javaType = String.class),
        @Result(property = "drawNumber", column = "draw_number", javaType = String.class),
        @Result(property = "drawTime", column = "draw_time", javaType = Date.class),
        @Result(property = "bai", column = "bai", javaType = NumberEnum.class, typeHandler = DictTypeHandler.class),
        @Result(property = "shi", column = "shi", javaType = NumberEnum.class, typeHandler = DictTypeHandler.class),
        @Result(property = "ge", column = "ge", javaType = NumberEnum.class, typeHandler = DictTypeHandler.class),
        @Result(property = "groupType", column = "group_type", javaType = GroupElectionType.class, typeHandler = DictTypeHandler.class),
        @Result(property = "sequenceType", column = "sequence_type", javaType = SequenceType.class, typeHandler = DictTypeHandler.class),
        @Result(property = "createdName", column = "created_name", javaType = String.class),
        @Result(property = "createdTime", column = "created_time", javaType = Date.class),
        @Result(property = "updateName", column = "update_name", javaType = String.class),
        @Result(property = "updateTime", column = "update_time", javaType = Date.class)})
    @SelectProvider(type = P3Provider.class, method = "findById")
    P3 findById(Long id);
    
    /**
     * 查找最后一期排3
     */
    @ResultMap(value = "p3")
    @Select("select * from lottery_p3 order by draw_time desc limit 1")
    P3 findLastP3();
    
    /**
     * 
     * @return
     */
    @ResultMap(value = "p3")
    @Select("select * from lottery_p3 order by draw_time asc limit 10000")
    List<P3> findAll();
    /**
     * 
     * @param date
     * @return
     */
    @Select("select count(*) from lottery_p3 where draw_time = #{drawtime}")
    int countByDrawTime(Date drawtime);
    
    /**
     * 
     * @param date
     * @return
     */
    @Select("select count(*) from lottery_p3 where draw_no = #{drawNo}")
    int countByDrawNo(String drawNo);
    

    
    /**
     * 
     */
    @ResultMap(value = "p3")
    @SelectProvider(type = P3Provider.class, method = "search")
    List<P3> search(P3Search search);
}
