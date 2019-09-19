package com.study.pattern.visitorPattern.visitorPattern;

import java.util.List;
/*
*
*
* */
public class VisitorMain {
    public static void main(String[] args){
        List<Element> list = ObjectStruture.getList();
        for(Element e: list){
            e.accept(new Visitor());
        }
    }

}

