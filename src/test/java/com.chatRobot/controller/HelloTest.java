package com.chatRobot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HelloTest {
    private static Logger log = LoggerFactory.getLogger(HelloTest.class);

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        String  s ="C:\\Users\\HeZeLiang\\Documents\\WeChat " +
                "Files\\wxid_nwlrvqyqigc221\\FileStorage\\File\\test\\findAllFansInfo.jmx";
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        //创建解析器
        DocumentBuilder builder=documentBuilderFactory.newDocumentBuilder();
        //通过解析器读取文件，生成w3c.dom.Document象树
        Document document=builder.parse(s);
        XPath xPath= XPathFactory.newInstance().newXPath();
        String path = "/jmeterTestPlan/hashTree/hashTree/hashTree/hashTree/ResponseAssertion/collectionProp/stringProp";
        String idNode=(String) xPath.evaluate(path, document, XPathConstants.STRING);
        System.out.println("idNode="+idNode);
    }



}
