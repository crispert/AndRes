/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samelongjigs.andres.controller;

import com.samelongjigs.andres.model.StringRes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author cris
 */
public class StringParseHandler extends DefaultHandler implements LexicalHandler {

    public List<StringRes> parsedStrings;
    
    StringRes currentRes;
    
    String element;
    String lastCategory; // == comment
    
    
    public StringParseHandler() {
        parsedStrings = new ArrayList<>(512);
    }
    
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;
        if (XmlWriter.E_STRING.equals(qName)) {
            String cn = attributes.getValue(XmlWriter.A_NAME);
            currentRes = new StringRes(cn, null);
            System.out.print(" # " + cn);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println(" ! " );
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);
//        System.out.print(element + " : " + value);
        if (currentRes != null) {
           // System.out.println("\n put " + currentName + " , " + value);
            currentRes.setValue(value);
            if (lastCategory != null) {
                currentRes.setCategory(lastCategory);
            }
            parsedStrings.add(currentRes);
            currentRes = null;
        }
    }

    @Override
    public void comment(char[] ch, int start, int length) throws SAXException {
        lastCategory = String.valueOf(ch, start, length);
//        System.out.println("found comment " + lastCategory);
    }
    
    
    @Override
    public void skippedEntity(String name) throws SAXException {
        System.out.println("skipent: " + name + "!");
    }


    
    @Override
    public void startDTD(String name, String publicId, String systemId) throws SAXException {
    }

    @Override
    public void endDTD() throws SAXException {
    }

    @Override
    public void startEntity(String name) throws SAXException {
    }

    @Override
    public void endEntity(String name) throws SAXException {
    }

    @Override
    public void startCDATA() throws SAXException {
    }

    @Override
    public void endCDATA() throws SAXException {
    }


    
    
    
}
