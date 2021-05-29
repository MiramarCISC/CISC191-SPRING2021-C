package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public class DummyString {

    public DummyString() {

    }

    public DummyString(String string) {
        this.str = string;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String string) {
        this.str = string;
    }

    @CsvBindByName(column = "str")
    private String str;

    @Override
    public String toString() {
        return String.format(
                "DummyString[str=%s]",
                str);
    }

}