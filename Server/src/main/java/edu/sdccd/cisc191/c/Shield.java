package edu.sdccd.cisc191.c;

public class Shield extends Item {

    private int dmgReduction;
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
    protected void useItem(Battler participant) {

    }

    @Override
    protected void getEffect() {

    }

}