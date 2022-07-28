/**
 * 文件名称:          			FileApiUtil.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.fjt.demo.api.util;

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
public class DemoUtil {

    /**
     * 单例
     */
    private static final DemoUtil INSTANCE = new DemoUtil();
    
    
    
    public static DemoUtil instance() {
        log.info("实例化");
        return INSTANCE;
    }
    
    
}
