package com.study.file;

import java.io.File;
import java.util.LinkedList;

public class FileDealWith {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        File dir = new File("C:\\Users\\Administrator\\Desktop\\爱思导出音乐");
        File file[] = dir.listFiles();
        for (int i = 0; i < file.length; i++) {
            File oldFile = file[i];
            String name = oldFile.getName();
            try {
                if (name.contains( "(2)" )) {
                    oldFile.delete();
                    System.out.println(name);
                }
                /*String[] prefix = name.split( "-");
                //System.out.println( name );
                String rootPath = oldFile.getParent();
                File newFile = new File(rootPath + File.separator + name.replace( prefix[0] + "-","" ));
                if(!newFile.getName().equals( oldFile.getName() ) ){
                    System.out.println("===========>" + name);
                    System.out.println("修改前："+name);
                    System.out.println("修改后："+newFile.getName());
                }
                if (oldFile.renameTo(newFile)){
                    System.out.println("修改成功!!!");
                }else{
                    System.out.println("修改失败!!!");
                }*/
            }catch (Exception e){

            }


        }

    }
}
