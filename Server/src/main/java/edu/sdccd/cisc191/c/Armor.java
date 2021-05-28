package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public abstract class Armor extends Item {

    @CsvBindByName(column = "defense")
    protected int defense;

    @CsvBindByName(column = "magical")
    protected boolean magical;

    public Armor() {
        equippable = true;
    }

    public void setDefense(int def) {
        defense = def;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public void useOnPM(PartyMember member) {

    }

    @Override
    public void useOnEnemy(Enemy enemy) {

    }

}