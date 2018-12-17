package com.smmizan.sqlitelistviewsearchfiltering;

/**
 * Created by Mizan on 16/12/2018.
 */

public class MyModel {
    String name = null;
    String code = null;

    public MyModel(String Sname, String code) {

        super();

        this.name = Sname;

        this.code = code;
    }

    public String getName() {

        return name;

    }
    public void setName(String Name2) {

        this.name = Name2;

    }
    public String getCode() {

        return code;

    }
    public void setCode(String code) {

        this.code = code;

    }

    @Override
    public String toString() {

        return  name + " " + code;

    }
}
