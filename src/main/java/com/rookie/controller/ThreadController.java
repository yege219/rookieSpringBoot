package com.rookie.controller;

import com.rookie.dto.PersonDTO;
import com.rookie.service.ThreadPoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.Future;

/**
 * @author rookie
 * @date 2018/1/26
 */
@RestController
@RequestMapping("/thread")
@Slf4j
@EnableAsync
public class ThreadController {

    @Autowired
    private ThreadPoolService threadPoolService;

    @RequestMapping("/testThread")
    public void getPerson() throws InterruptedException {

        ArrayList<Future<String>> futureList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            futureList.add(threadPoolService.testThread());
        }
        // 判断所有异步任务执行完毕
        boolean isOver = judge(futureList);
        while (!isOver) {
            // 若异步任务未完全执行完毕
            log.info("异步任务未完成等待1秒钟");
            Thread.sleep(1000);
            isOver = judge(futureList);
        }

        log.info("全部异步任务执行完毕");
    }

    /**
     * 判断集合内的所有异步任务是否全部执行完毕
     * @param futureList 存有异步任务的集合
     * @return 是否执行完毕
     */
    public boolean judge(ArrayList<Future<String>> futureList) {
        boolean isOver = true;
        for (Future<String> future : futureList) {
            if (!future.isDone()) {
                isOver = false;
                break;
            }
        }
        return isOver;
    }



}
