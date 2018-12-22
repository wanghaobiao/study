package com.study.pattern.adapterPattern.objectAdapter;

import com.study.pattern.adapterPattern.classAdapter.Ps2;
import com.study.pattern.adapterPattern.classAdapter.Usber;
/*
*
* 对象适配器
*
* */
public class objectAdapterMain   {
    public static void main(String[] args) {
        Ps2 p = new Adapter(new Usber());
        p.isPs2();
    }
}
