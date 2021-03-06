package edu.sdccd.cisc191.c;

public class Body extends Armor {

    public Body(String name, int defense, String rarity, boolean magical) {
        this.name = name;
        this.defense = defense;
        this.rarity = rarity;
        this.magical = magical;
    }

    public Body() {

    }

    @Override
    protected void getEffect() {

    }

    @Override
    public String toString() {
        return String.format(
                "Body[NAME=%s, DEFENSE=%d, RARITY=%s, MAGICAL=%b]",
                name, defense, rarity, magical);
    }

}