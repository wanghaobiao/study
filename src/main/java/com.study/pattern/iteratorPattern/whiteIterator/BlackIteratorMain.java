package com.study.pattern.iteratorPattern.whiteIterator;


import java.util.*;

/*
*
* 黑箱聚集与内禀迭代子
*
* */
public class BlackIteratorMain {
    public static void main(String[] args) {
        Object[] objArray = {"One","Two","Three","Four","Five","Six"};
        //创建聚合对象
        Aggregate agg = new ConcreteAggregate(objArray);
        //循环输出聚合对象中的值
        Iterator it = agg.createIterator();
        while(!it.isDone()){
            System.out.println(it.currentItem());
            it.next();
        }
        List<String> list = new ArrayList<String>();
        list.add("张三1");
        list.add("张三2");
        list.add("张三3");
        list.add("张三4");

        List<String> linkList = new LinkedList<String>();
        linkList.add("link1");
        linkList.add("link2");
        linkList.add("link3");
        linkList.add("link4");

        Set<String> set = new HashSet<String>();
        set.add("set1");
        set.add("set2");
        set.add("set3");
        set.add("set4");

        //迭代器 可以不分容器类型

        //使用迭代器遍历ArrayList集合
        java.util.Iterator listIt = list.iterator();
        while(listIt.hasNext()){
            System.out.println(listIt.next());
        }
        //使用迭代器遍历LinkedList集合
        java.util.Iterator linkIt = linkList.iterator();
        while(linkIt.hasNext()){
            System.out.println(linkIt.next());
        }
        //使用迭代器遍历Set集合
        java.util.Iterator setIt = set.iterator();
        while(setIt.hasNext()){
            System.out.println(setIt.next());
        }


    }


}
