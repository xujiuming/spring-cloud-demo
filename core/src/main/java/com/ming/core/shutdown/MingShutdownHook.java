package com.ming.core.shutdown;

import lombok.extern.slf4j.Slf4j;

/**
 * jvm关闭钩子
 *
 * @author ming
 * @date 2019-04-16 11:16:18
 */
@Slf4j
public class MingShutdownHook extends Thread {

    @Override
    public void run() {
        log.info("shutdown hook start 。。。。。。。。");
        //先无脑休眠10s
        try {
            // todo ming  通知 redis 强制写磁盘
            Thread.sleep(1);
        } catch (InterruptedException e) {
            log.error("jvm关闭钩子异常", e);
        }
        log.info("shutdown hook end 。。。。。。。。");
    }
}
