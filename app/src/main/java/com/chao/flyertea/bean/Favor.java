package com.chao.flyertea.bean;

import java.util.ArrayList;

public class Favor {
    private String name;
    private ArrayList<Bank> types;
//    private ArrayList<Object> subforms;
//    private Object newtypes;
//    private Object newsubforms;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Bank> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Bank> types) {
        this.types = types;
    }
}
