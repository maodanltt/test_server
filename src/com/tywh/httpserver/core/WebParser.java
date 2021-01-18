package com.tywh.httpserver.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebParser {

    public static Map<String, Map<String, String>> servletMaps = new HashMap<>();

    public static void parse(String[] webAppNames) {
        for (String webAppName : webAppNames) {
            Map<String, String> servletMap = parseSingle(webAppName);
            servletMaps.put(webAppName, servletMap);
        }
    }

    private static Map<String, String> parseSingle(String webAppName) {
        Map<String, String> servletMap = new HashMap<>();
        try {
            SAXReader reader = new SAXReader();
            String filePath = "test_server/" + webAppName + "/WEB-INF/web.xml";
            Document doc = reader.read(new InputStreamReader(new FileInputStream(filePath)));
            List<Element> sevletList = doc.selectNodes("//servlet");
            Map<String, String> servletInfoMap = new HashMap<>();
            for (Element e : sevletList) {
                String servletName = e.selectSingleNode("servlet-name").getText();
                String servletClassName = e.selectSingleNode("servlet-class").getText();
                servletInfoMap.put(servletName, servletClassName);
            }

            List<Element> servletMappingList = doc.selectNodes("//servlet-mapping");
            Map<String, String> servletMappingInfoMap = new HashMap<>();
            for (Element e : servletMappingList) {
                String servletName = e.selectSingleNode("servlet-name").getText();
                String urlPattern = e.selectSingleNode("url-pattern").getText();
                servletMappingInfoMap.put(servletName, urlPattern);
            }
            Set<String> servletNameSet = servletInfoMap.keySet();
            for (String servletName : servletNameSet) {
                String servletClassName = servletInfoMap.get(servletName);
                String servletUrlPattern = servletMappingInfoMap.get(servletName);
                servletMap.put(servletUrlPattern, servletClassName);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return servletMap;
    }
}
