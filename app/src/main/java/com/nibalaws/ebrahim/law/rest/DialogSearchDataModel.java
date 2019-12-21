package com.nibalaws.ebrahim.law.rest;

public class DialogSearchDataModel {

    public String name;
    public boolean checked;
    public String id;

    public DialogSearchDataModel(String name, String id, boolean checked) {
        this.name = name;
        this.checked = checked;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DialogSearchDataModel{" +
                "name='" + name + '\'' +
                ", checked=" + checked +
                ", id='" + id + '\'' +
                '}';
    }
}
