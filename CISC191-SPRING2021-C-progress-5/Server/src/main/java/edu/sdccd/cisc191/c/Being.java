package edu.sdccd.cisc191.c;

public abstract class Being {

    protected String typeName;
    protected int baseDef;
    protected int baseAtk;
    protected int baseSpd;
    protected int baseHP;
    protected int currLevel;

    public Being(String type, int bDef, int bAtk, int bSpd, int bHP) {
        typeName = type;
        baseDef = bDef;
        baseAtk = bAtk;
        baseSpd = bSpd;
        baseHP = bHP;
    }

    public void setTypeName(String type) {
        typeName = type;
    }

    public void setBaseDef(int bDef) { baseDef = bDef; }

    public void setBaseAtk(int bAtk) { baseAtk = bAtk; }

    public void setBaseSpd(int bSpd) { baseSpd = bSpd; }

    public void setBaseHP(int bHP) { baseHP = bHP; }

    public void setCurrLevel(int currL) { currLevel = currL; }

    public String getTypeName() { return typeName; }

    public int getBaseDef() { return baseDef; }

    public int getBaseAtk() { return baseAtk; }

    public int getBaseSpd() { return baseDef; }

    public int getBaseHP() { return baseHP; }

    public int getCurrLevel() { return currLevel; }

}