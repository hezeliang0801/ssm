package com.chatRobot.controller;



import com.csvreader.CsvWriter;
import com.sun.deploy.util.StringUtils;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Test1 {
    //预加载信息
    static File file = new File("C:\\Users\\HeZeLiang\\Documents\\WeChat " +
            "Files\\wxid_nwlrvqyqigc221\\FileStorage\\File\\test");//读取本地Jmeter脚本路径
    static String CSVpath = "C:\\Users\\HeZeLiang\\Documents\\WeChat " +
            "Files\\wxid_nwlrvqyqigc221\\FileStorage\\File\\writer.csv";//csv文件路径
    static CsvWriter csvWriter =new CsvWriter(CSVpath,',', Charset.forName("gbk") );


    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException, DocumentException {
        String[] csvHeaders = {"脚本名", "作者", "脚本编号", "脚本类型", "测试方法", "业务板块", "脚本功能说明", "接口协议", "接口","断言"};
        csvWriter.writeRecord(csvHeaders);
        func(file,csvWriter,"/jmeterTestPlan/hashTree/TestPlan/stringProp[@name='TestPlan.comments']" ,"/jmeterTestPlan" +
                "/hashTree/hashTree/ThreadGroup/stringProp[@name='TestPlan.comments']","//ResponseAssertion/collectionProp/stringProp");
        csvWriter.close();
    }



    //遍历文件夹，获取jmx文件，并解析，打印
    public static void func(File file, CsvWriter csvWriter, String TestPlanXpath, String ThreadGroupXpath, String ResponseAssertionXpath) throws IOException, ParserConfigurationException, SAXException,
            XPathExpressionException, DocumentException {
        File[] fs = file.listFiles();
        SAXReader saxReader = new SAXReader();;
        List<String[]> listAll = new ArrayList<>();
        for (File f : fs) {
            if (f.isDirectory()) {
                func(f, csvWriter, TestPlanXpath,ThreadGroupXpath,ResponseAssertionXpath);
            } else if (f.isFile()) {
                if (f.getName().endsWith(".jmx")) {
                    List<String> list = new ArrayList<>();
                    String s = f.getName().substring(0, f.getName().lastIndexOf("."));
                    list.add(s);
                    List<String> testPlanComments = exportMsg(TestPlanXpath, saxReader, f);
                    list.addAll(testPlanComments);
                    List<String> threadGroupComments =exportMsg(ThreadGroupXpath, saxReader, f);
                    list.addAll(threadGroupComments);
                    List<String> responseAssertionComments = exportMsg(ResponseAssertionXpath, saxReader, f);
                    String responseAssertionCommentStr = StringUtils.join(responseAssertionComments, System.lineSeparator());
                    list.add(responseAssertionCommentStr);
                    String[] array =new String[list.size()];
                    String[] strings = list.toArray(array);
                    listAll.add(strings);
                }
            }
        }
        for (String[] strings : listAll) {
            csvWriter.writeRecord(strings);
        }
    }

    //处理节点内容
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
                    if(split2.length == 1){
                        list.add("暂缺");
                    }else{
                        list.add(split2[1]);
                    }
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

    //解析节点内容
    private static List<String> exportMsg(String TestPlanXpath, SAXReader saxReader, File f) throws DocumentException, MalformedURLException {
        Document document = saxReader.read(f);
        List<String> strings  = new ArrayList<>();
        List<Node> TXT = document.selectNodes(TestPlanXpath);
        for (Node ele : TXT) {
            List<String> strings1 = writeOut(ele.getText());
            for (String s : strings1) {
                strings.add(s);
            }
        }
        return strings;
    }
}
