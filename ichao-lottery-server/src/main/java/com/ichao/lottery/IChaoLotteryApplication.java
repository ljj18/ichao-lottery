/**
 * 文件名称:          			FjtSignatureApplication.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import com.fjt.common.BaseApplication;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-01-08 10:50
 * 
 */
@Slf4j
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.ichao.lottery.db.dao"})
@ComponentScan(basePackages = {"com.ichao.lottery"})
public class IChaoLotteryApplication extends BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(IChaoLotteryApplication.class, args);
        log.info("Demo Server Application start success");
    }
}
