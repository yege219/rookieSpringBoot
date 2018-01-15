package com.rookie.demo;


import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangyechen on 2017/12/19.
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {

        String[] array = {"A","B","C","D","E","F","A","B","C","D","E","F","A","B","C","D","E","F"};
        int num = array.length;

        CountDownLatch latch = new CountDownLatch(num);

        for (int i = 0; i < num; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"输出");
                    latch.countDown();
                }
            }).start();

        }

        latch.await();
        System.out.println("主线程输出");

    }

}
