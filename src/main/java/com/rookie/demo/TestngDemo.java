package com.rookie.demo;

import com.rookie.TestNGTests;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;

/**
 * Created by rookie on 2018/1/31.
 */
public class TestngDemo {

    public static void main(String[] args) {

        TestngDemo testngDemo = new TestngDemo();
        testngDemo.testByMethod();

    }

    /**
     * 指定测试类进行测试
     */
    private void testByClass() {
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[]{TestNGTests.class});
        testNG.run();
    }

    /**
     * 指定测试方法进行测试
     */
    private void testByMethod() {
        TestNG testNG = new TestNG();

        // 创建一个xml标签，即创建一个xml配置文件
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName("xml");
        xmlSuite.setThreadCount(1);


        // 创建一个test标签，将test标签装到suite标签中
        XmlTest xmlTest = new XmlTest(xmlSuite);


        // 创建配置文件中的classes标签,确定测试类
        ArrayList<XmlClass> xmlClassesList = new ArrayList<>();
        XmlClass xmlClass = new XmlClass(TestNGTests.class.getName());
        xmlClassesList.add(xmlClass);
        // 将classes标签放到suite的test标签中
        xmlTest.setClasses(xmlClassesList);


        // 创建配置文件中的method标签，确定测试方法
        ArrayList<XmlInclude> includeMethodList = new ArrayList<>();
        XmlInclude funA = new XmlInclude("funcA");
        includeMethodList.add(funA);
        // 将method标签放到classes标签中
        xmlClass.setIncludedMethods(includeMethodList);


        // 将配置好的suite标签放入集合中
        ArrayList<XmlSuite> xmlSuitesList = new ArrayList<>();
        xmlSuitesList.add(xmlSuite);


        // testNG执行集合
        testNG.setXmlSuites(xmlSuitesList);
        testNG.run();

    }

    /**
     * <suite name="testng" verbose="1" thread-count="2">
     <parameter name="first-name" value="cedric" />
     <test name="Regression 1">
     <group>
     <run>
     <exclude name="excludeThisGroup"/>
     </run>
     </group>
     <classes>
     <class name="test.parameter.test1" />
     <class name="test.parameter.test2" />
     </classes>
     </test>
     </suite>
     */
}
