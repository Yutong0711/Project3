package com.yutong.readParse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Family {
    String ID;
    Date Married;
    Date Divorced;
    String HusbandID;
    String HusbandName;
    String WifeID;
    String WifeName;
    List<String> Children = new ArrayList<>();

    public Family() {
        super();
    }

    public Family(String ID) {
        super();
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}