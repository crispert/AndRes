/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samelongjigs.andres.controller;

import com.samelongjigs.andres.model.StringRes;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author cris
 */
public class XmlWriter {
    
    static final String E_RESOURCES = "resources";
    static final String E_STRING = "string";
    static final String E_ = "";

    static final String A_NAME = "name";
    
    
    public static void writeStrings(String path, List<StringRes> resources) {
        
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .newDocument();
            Element rootElem = doc.createElement(E_RESOURCES);
            doc.appendChild(rootElem);
            
            Element strElem;
            for (StringRes str : resources) {
                strElem = doc.createElement(E_STRING);
                strElem.setAttribute(A_NAME, str.getName());
                strElem.setTextContent(str.getValue());
                System.out.println("app " + str.getName() + " : " + str.getValue());
                rootElem.appendChild(strElem);
            }
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
        //    t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            
            StreamResult result = new StreamResult(new File(path));
            DOMSource xmlSource = new DOMSource(doc);
            t.transform(xmlSource, result);
            t.transform(xmlSource, new StreamResult(System.out));
        } catch (Exception ex) {
            Logger.getLogger(XmlWriter.class.getName()).log(Level.WARNING, null, ex);
        }
    }
}
