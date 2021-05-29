package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public class DummyInteger {

    public DummyInteger() {

    }

    public DummyInteger(int i) {
        integer = i;
    }

    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    @CsvBindByName(column = "INTEGER")
    private int integer;

    @Override
    public String toString() {
        return String.format(
                "DummyInteger[INTEGER=%d]",
                integer);
    }

}