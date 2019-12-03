package com.chatRobot.controller;

import com.csvreader.CsvWriter;
import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestJMX {
    static File file = new File("C:\\Users\\HeZeLiang\\Documents\\WeChat " +
            "Files\\wxid_nwlrvqyqigc221\\FileStorage\\File\\test");//读取本地Jmeter脚本路径
    static String src = "C:\\Users\\HeZeLiang\\Documents\\WeChat " +
            "Files\\wxid_nwlrvqyqigc221\\FileStorage\\File\\writer.csv";//csv文件路径

    // 创建CSV读对象(文件路径，分隔符，编码格式)
    static CsvWriter csvWriter = new CsvWriter(src, ',', Charset.forName("gbk"));


    private static void WriteCsv(String[] s) {
        String src = "C:\\Users\\HeZeLiang\\Documents\\WeChat " +
                "Files\\wxid_nwlrvqyqigc221\\FileStorage\\File\\writer.csv";//csv文件路径
        try {
            // 创建CSV读对象(文件路径，分隔符，编码格式)
            CsvWriter csvWriter = new CsvWriter(src, ',', Charset.forName("gbk"));
            // 设置标题
            String[] csvHeaders = {"脚本名", "作者", "脚本编号", "脚本类型", "测试方法", "业务板块", "脚本功能说明", "接口协议", "接口","结果"};
            csvWriter.writeRecord(csvHeaders);
            csvWriter.writeRecord(s);
            csvWriter.writeRecord(s);
            // 关闭
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void readJmx(File file, String s) throws MalformedURLException {
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法获取xml文件,docuemnt对象。
            Document document = reader.read(file);
            // 通过document对象获取根节点
            Element root = document.getRootElement();
            List<String> ls = new ArrayList<>();
            ls.add(s);
            // 循环解析
            String f = file.getAbsolutePath();
            List<String> strings = readEle(root, ls,f);
            String[] strings1 = new String[10];
            for (int i = 0; i < strings.size(); i++) {
                strings1[i] = strings.get(i);
            }

            try {
                csvWriter.writeRecord(strings1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /*
     * 循环解析jmx文件
     */
    public static List<String> readEle(Element e, List<String> s, String f) {
        //判断是否有复合内容
        if (e.hasMixedContent()) {
            //输出该节点的名字，对他的子节点继续进行判断
            // System.out.println("节点名：" + e.getName());
            Iterator it = e.elementIterator();
            while (it.hasNext()) {
                Element arrrName = (Element) it.next();
                //递归
                readEle(arrrName, s, f);
            }
        } else {
            List<String> list = null;
            //如果没有复合内容，就可以输出了
            Attribute name = e.attribute("name");
            if (name != null && name.getValue().equals("TestPlan.comments")) {
                list = writeOut(e.getText());
            }
            if (CollectionUtils.isNotEmpty(list)) {
                for (String s1 : list) {
                    s.add(s1);
                }
            }
        }

        return s;
    }//输出.jmx文件中comments内容

    /**
     * 解析字符串
     * @param text
     * @return
     */
    private static List<String> writeOut(String text) {
        List<String> list = new ArrayList<>();
        Boolean flag = true;
        String m = "";
        String[] split1 = text.split("\\n");
        for (String s : split1) {
            if (flag) {
                String[] split2 = s.split(":");
                if (split2[0].equals("接口")) {
                    flag = false;
                } else {
                    list.add(split2[1]);
                }
            } else {
                m = m + s + System.lineSeparator();
            }
        }
        if (!flag) {
            list.add(m);
        }
        return list;
    }

    /*
     * 找到文件路径下的.jmx文件
     */
    private static void func(File file) throws MalformedURLException {
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isDirectory()) {
                func(f);
            } else if (f.isFile()) {
                if (f.getName().endsWith(".jmx")) {
                    String s = f.getName().substring(0, f.getName().lastIndexOf("."));
                    readJmx(f, s);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        String[] csvHeaders = {"脚本名", "作者", "脚本编号", "脚本类型", "测试方法", "业务板块", "脚本功能说明", "接口协议", "接口","结果"};
        csvWriter.writeRecord(csvHeaders);
        func(file);
        csvWriter.close();
    }
}






