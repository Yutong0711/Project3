package com.yutong.readParse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Indivdual {
    String ID;
    String Name;
    String Gender;
    Date Birthday;
    int Age;
    boolean Alive;
    Date Death;
    List<String> Child = new ArrayList<>();
    List<String> Spouse = new ArrayList<>();

    public Indivdual() {
        super();
    }

    public Indivdual(String ID) {
        super();

        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}
