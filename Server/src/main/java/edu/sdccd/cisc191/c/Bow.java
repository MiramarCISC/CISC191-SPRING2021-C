package edu.sdccd.cisc191.c;

public class Bow extends Weapon {
    Bow(String name, int attack, String rarity, boolean applyFire, boolean applyPoison) {
        this.name = name;
        this.attack = attack;
        this.rarity = rarity;
        this.applyFire = applyFire;
        this.applyPoison = applyPoison;
    }
    @Override
    protected void getEffect() {

    }
}