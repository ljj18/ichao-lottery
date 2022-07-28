/**
 * 文件名称:          			FileConfig.java
 * 版权所有@ 2020-2021 	富金通金融信息服务(上海)有限公司
 * 编译器:           			JDK1.8
 */

package com.ichao.lottery.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 配置文件
 * 
 * Version		1.0.0      
 * 
 * @author		FPM0393
 * 
 * Date			2021-07-02 13:43
 * 
 */
@Configuration
@RefreshScope
@Data
public class DemoConfig {

    @Value("${fjt.demo.home.path:${user.home}}")
    private String homePath;
}
