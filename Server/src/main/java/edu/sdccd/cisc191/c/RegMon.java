package edu.sdccd.cisc191.c;

public class RegMon extends Monster {

    public RegMon(String type, int bDef, int bAtk, int bSpd, int bHP) {
        super(type, bDef, bAtk, bSpd, bHP);
    }

    @Override
    public String toString() {
        return String.format(
                "RegMon[typeName=%s, baseDef=%d, baseAtk=%d, baseSpd=%d, baseHP=%d]",
                typeName, baseDef, baseAtk, baseSpd, baseHP);
    }

}