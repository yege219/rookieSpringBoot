package com.rookie.utils;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * @author rookie
 * @date 2018/1/29
 */
public class ThreadUtil {

    /**
     * 判断集合中所有子线程的异步任务是否全部完成
     * @param futureList 装载子线程异步任务的集合
     * @return 是否完成
     */
    public boolean childsIsAllOver(List<Future<String>> futureList) {
        // 设置标记
        boolean isOver = true;
        // 遍历集合，若有子线的任务未完成，则跳出循环，返回false
        Iterator<Future<String>> iterator = futureList.iterator();
        while (iterator.hasNext()) {
            Future<String> future = iterator.next();
            if (!future.isDone()) {
                isOver = false;
                break;
            }
        }
        // 返回标记
        return isOver;

    }
}
