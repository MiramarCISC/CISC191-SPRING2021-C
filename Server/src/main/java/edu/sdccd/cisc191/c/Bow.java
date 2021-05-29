package edu.sdccd.cisc191.c;

public class Bow extends Weapon {

    public Bow(String name, int attack, String rarity, boolean applyFire, boolean applyPoison) {
        this.name = name;
        this.attack = attack;
        this.rarity = rarity;
        this.applyFire = applyFire;
        this.applyPoison = applyPoison;
    }

    public Bow() {

    }

    @Override
    protected void getEffect() {

    }

    @Override
    public String toString() {
        return String.format(
                "Sword[NAME=%s, ATTACK=%d, RARITY=%s, APPLYFIRE=%b, APPLYPOISON=%b]",
                name, attack, rarity, applyFire, applyPoison);
    }
    
}