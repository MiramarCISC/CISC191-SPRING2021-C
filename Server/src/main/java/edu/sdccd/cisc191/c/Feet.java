package edu.sdccd.cisc191.c;

public class Feet extends Armor {

    public Feet(String name, int defense, String rarity, boolean magical) {
        this.name = name;
        this.defense = defense;
        this.rarity = rarity;
        this.magical = magical;
    }

    @Override
    protected void getEffect() {

    }
}