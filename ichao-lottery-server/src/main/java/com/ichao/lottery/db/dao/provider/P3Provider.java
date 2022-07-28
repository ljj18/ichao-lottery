package com.ichao.lottery.db.dao.provider;

import com.fjt.common.db.annotation.SqlProvider;
import com.fjt.common.db.provider.BaseSqlProvider;
import com.ichao.lottery.db.model.P3;
import com.ichao.lottery.dto.search.P3Search;

/**
 * 
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-04-28 17:54
 * 
 */
@SqlProvider(tableName = "lottery_p3", entityClass = P3.class)
public class P3Provider extends BaseSqlProvider<P3, P3Search> {

}

