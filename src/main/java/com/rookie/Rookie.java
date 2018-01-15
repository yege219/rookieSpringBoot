package com.rookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangyechen on 2017/12/12.
 */
public class Rookie {

    /* 定义一个ThreadLocal */
    static ThreadLocal<ArrayList<String>> threadList = new ThreadLocal<>();

    static ArrayList<String> publicList = new ArrayList<>();

    static ConcurrentHashMap<String, String> cMap = new ConcurrentHashMap<>();


    /* 测试线程结束后再继续执行 */

    public static void main(String[] args) throws InterruptedException {

        /* 起20个子线程 */
        for (int i = 0; i < 30; i++) {

            Thread workThread = new Thread(new Runnable() {
                @Override
                public void run() {

                    cMap.put(Thread.currentThread().getName(),"name");

                    System.out.println("我是第一个子线程");
                }
            });

            workThread.start();
            /* join */
            workThread.join();
        }

        /* 所有子线程执行完成后进行后续业务 */

        System.out.println("子线程已完成，开始后续业务");
        System.out.println(cMap.size());
        System.out.println(cMap);

    }


    private static ArrayList<String> getList() {
        ArrayList<String> list = threadList.get();
        if (list == null) {
            list = new ArrayList<>();
            threadList.set(list);
        }
        return list;
    }
}
