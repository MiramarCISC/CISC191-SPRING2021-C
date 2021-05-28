package edu.sdccd.cisc191.c;

public class Head extends Armor {

    public Head(String name, int defense, String rarity, boolean magical) {
        this.name = name;
        this.defense = defense;
        this.rarity = rarity;
        this.magical = magical;
    }

    public Head() {

    }

    @Override
    protected void getEffect() {

    }

    @Override
    public String toString() {
        return String.format(
                "Head[name=%s, defense=%d, rarity=%s, magical=%b]",
                name, defense, rarity, magical);
    }

}