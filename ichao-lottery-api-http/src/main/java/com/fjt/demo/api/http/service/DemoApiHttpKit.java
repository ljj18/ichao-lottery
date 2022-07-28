/**
 * 文件名称:          			XxxApiHttpKit.java
 * 版权所有@ 2020-2021 	富金通网络科技服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.fjt.demo.api.http.service;

import com.fjt.httpclient.AbstractHttpRequestAdapter;

/**
 * 应用封装 http 协议的 API
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2022-07-08 10:30
 * 
 */
public class DemoApiHttpKit extends AbstractHttpRequestAdapter {

    /**
     * 单例
     */
    private static final DemoApiHttpKit INSTANCE = new DemoApiHttpKit();

    public static DemoApiHttpKit instance() {
        return INSTANCE;
    }
}
