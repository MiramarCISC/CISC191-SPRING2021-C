package edu.sdccd.cisc191.c;

public abstract class Armor extends Item {

    protected int defense;
    protected boolean magical;

    public Armor() {
        equippable = true;
    }



    @Override
    protected void useItem(Battler participant) {

    }

}
