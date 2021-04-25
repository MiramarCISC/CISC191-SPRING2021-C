package edu.sdccd.cisc191.c;

public class Shield extends Item {

    private int dmgReduction;
    private int defIncrease;
    private String rarity;

    public Shield() {
        equippable = true;
    }

    public void reduceDamage() {

    }

    public void setDmgReduction(int dRed) {
        dmgReduction = dRed;
    }

    public void setDefIncrease(int defI) {
        defIncrease = defI;
    }

    public int getDefIncrease() {
        return defIncrease;
    }

    public int getDmgReduction() {
        return dmgReduction;
    }

    @Override
    protected void useItem(Battler participant) {

    }

    @Override
    protected void getEffect() {

    }

}
