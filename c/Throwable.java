package edu.sdccd.cisc191.c;

public class Throwable extends Weapon {

    Throwable(String name, int attack, String rarity, boolean applyPoison) {
        this.name = name;
        this.attack = attack;
        this.rarity = rarity;
        this.applyPoison = applyPoison;
    }
    @Override
    protected void getEffect() {

    }

    @Override
    public String toString() {
        return String.format(
                "Throwable[name=%s, attack=%d, rarity=%s, applyPosion=%b]",
                name, attack, rarity, applyPoison);
    }

}