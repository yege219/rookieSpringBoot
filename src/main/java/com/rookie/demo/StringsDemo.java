package com.rookie.demo;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterators;

import java.util.Iterator;

/**
 * Created by zhangyechen on 2017/12/18.
 * google guava 集合工具demo
 */
public class StringsDemo {

    public static void splitDemo() {

        Iterable<String> it = Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux");

        Iterator<String> iterator = it.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    public static void main(String[] args) {
        splitDemo();
    }
}
