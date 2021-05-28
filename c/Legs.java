package edu.sdccd.cisc191.c;

public class Legs extends Armor {

    public Legs(String name, int defense, String rarity, boolean magical) {
        this.name = name;
        this.defense = defense;
        this.rarity = rarity;
        this.magical = magical;
    }

    public Legs() {

    }

    @Override
    protected void getEffect() {

    }

    @Override
    public String toString() {
        return String.format(
                "Legs[name=%s, defense=%d, rarity=%s, magical=%b]",
                name, defense, rarity, magical);
    }

}