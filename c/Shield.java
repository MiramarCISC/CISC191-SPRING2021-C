package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public class Shield extends Item {

    @CsvBindByName(column = "dmgReduction")
    private int dmgReduction;

    @CsvBindByName(column = "defIncrease")
    private int defIncrease;

    public Shield() {
        equippable = true;
    }

    public int reduceDamage() {
        return dmgReduction;
    }

    public int increaseDefense() {
        return defIncrease;
    }

    public Shield(String name, int dmgReduction, int defIncrease, String rarity) {
        this.name = name;
        this.dmgReduction = dmgReduction;
        this.defIncrease = defIncrease;
        this.rarity = rarity;
    }

    @Override
    public void useOnPM(PartyMember member) {

    }

    @Override
    public void useOnEnemy(Enemy enemy) {

    }

    @Override
    protected void getEffect() {

    }

    @Override
    public String toString() {
        return String.format(
                "Shield[name=%s, dmgReduction=%d, defIncrease=%d, rarity=%s, dmgReduction=%d, defIncrease=%d]",
                name, dmgReduction, defIncrease, rarity, dmgReduction, defIncrease);
    }

}