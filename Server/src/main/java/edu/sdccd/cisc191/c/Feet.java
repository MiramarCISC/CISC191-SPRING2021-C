package edu.sdccd.cisc191.c;

public class Feet extends Armor {

    public Feet(String name, int defense, String rarity, boolean magical) {
        this.name = name;
        this.defense = defense;
        this.rarity = rarity;
        this.magical = magical;
    }

    public Feet() {

    }

    @Override
    protected void getEffect() {

    }

    @Override
    public String toString() {
        return String.format(
                "Feet[name=%s, defense=%d, rarity=%s, magical=%b]",
                name, defense, rarity, magical);
    }

}