/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samelongjigs.andres.model;

import java.util.Map;

/**
 *
 * @author cris
 */
public class StringRes {
    
    protected ResFile container;
    
    protected String name;
    
    protected String value;
    
    protected String category;
    
    protected Map<String, String> attributes;
    
    
    public StringRes(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
}
