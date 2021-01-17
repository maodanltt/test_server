package com.tywh.httpserver.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ServerParser {

    public static int getServerPort() {
        SAXReader reader = new SAXReader();
        Document doc = null;
        Element ele = null;
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("server.xml").toString();
            doc = reader.read(path);
            ele = (Element) doc.selectSingleNode("//connector");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(ele.attributeValue("port"));
    }
}
