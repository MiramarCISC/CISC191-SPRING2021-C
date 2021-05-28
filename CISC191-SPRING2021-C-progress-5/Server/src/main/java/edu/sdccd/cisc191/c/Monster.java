package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public abstract class Monster extends Being {

    @CsvBindByName(column = "magical")
    protected boolean magical;

    public Monster(String type, int bDef, int bAtk, int bSpd, int bHP) {
        super(type, bDef, bAtk, bSpd, bHP);
    }

    public boolean isMagical() {
        return magical;
    }

    public void setMagical(boolean magical) {
        this.magical = magical;
    }

}