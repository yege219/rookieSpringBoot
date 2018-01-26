package com.rookie.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.Future;

/**
 * 线程池的使用
 * @author rookie
 * @date 2018/1/26
 */
@Slf4j
@Service
public class ThreadPoolService {


    @Async
    public Future<String> testThread() {
        log.info(String.valueOf(Thread.currentThread().getId()));

        return new AsyncResult<>("异步线程执行完毕");
    }


}
