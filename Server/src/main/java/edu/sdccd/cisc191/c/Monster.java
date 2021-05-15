package edu.sdccd.cisc191.c;

public abstract class Monster extends Being {

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