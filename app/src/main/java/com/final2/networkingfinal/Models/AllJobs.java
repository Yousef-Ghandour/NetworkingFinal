package com.final2.networkingfinal.Models;

public class AllJobs {
    int id;
    String name;
    String icon;
    String desc;
    int active;

    public AllJobs() {
    }

    public AllJobs(int id, String name, String icon, String desc, int active) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.desc = desc;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

}
