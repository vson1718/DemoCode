package com.vson.code.ceiling;

public class CeilingModel {

    private String name;
    private String grounpName;

    public CeilingModel(String name, String grounpName) {
        this.name = name;
        this.grounpName = grounpName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrounpName() {
        return grounpName;
    }

    public void setGrounpName(String grounpName) {
        this.grounpName = grounpName;
    }
}
