package com.imooc.ad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/********************************
 *** 线程池配置
 ***@Author chengchuanqiang
 ***@Date 2019/5/22 16:29
 ***@Version 1.0.0
 ********************************/
@Configuration
@EnableAsync
public class ExecutorConfiguration {

    @Bean(name = "binlogClientConnectExecutor")
    public Executor getBinlogClientConnectExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(1);
        //最大线程池大小
        executor.setMaxPoolSize(1);
        //任务队列大小
        executor.setQueueCapacity(1);
        //最大存活时间
        executor.setKeepAliveSeconds(60);
        //拒绝策略，抛弃，不执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //线程名称
        executor.setThreadNamePrefix("BinlogClientConnect-");
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;


    }

}
