package com.study.leetcode.lc_;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;


public class LcFreemarkerUtil {

    /**
     * 模板路径
     */
    private final static String templatePath = "/templates/";

    /**
     * @description  创建文件
     * @param  entity  参数值
     * @date  20/07/20 14:08
     * @author  wanghb
     * @edit
     */
    public static void buildFile(FreemarkerEntity entity)throws IOException {
        Integer index = entity.getIndex();
        String targetPath = "E:\\SpringCloud\\study\\src\\main\\java\\com.study\\leetcode\\" + "lc_" + index ;

        // 模板生成后新文件名
        String fileName = "Solution.java";
        // 创建文件夹
        new File(targetPath).mkdirs();
        File nFile = new File(targetPath +"/"+ fileName);
        if (nFile.exists()) {
            nFile.delete();
        }
        // 生成目标文件
        Writer writer = null;
        try {
            writer = new FileWriter(nFile);
            LcFreemarkerUtil lcFreemarkerUtil = new LcFreemarkerUtil();
            Template template = lcFreemarkerUtil.getConfiguration(templatePath + "leetcodeTemplate/leetcode.ftl");
            template.process(entity, writer);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }
    }

    /**
     * @description  创建模板构造器
     * @param  templatePath  模板目录
     * @return  返回模板构造器
     * @date  20/07/20 14:03
     * @author  wanghb
     * @edit
     */
    private Template getConfiguration(String templatePath) throws IOException {
        Configuration configuration = new Configuration( Configuration.VERSION_2_3_22 );
        configuration.setTagSyntax( Configuration.AUTO_DETECT_TAG_SYNTAX );
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = this.getClass().getResourceAsStream(templatePath);
            br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            Template template = new Template( "temp.flt", br, configuration );
            return template;
        } finally {
            if (is != null) {
                is.close();
            }
            if (br != null) {
                br.close();
            }

        }
    }

    public static void main(String[] args) {
        try {
            LcFreemarkerUtil.buildFile( new FreemarkerEntity( 12 ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
