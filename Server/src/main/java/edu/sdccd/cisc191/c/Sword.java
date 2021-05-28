package edu.sdccd.cisc191.c;

public class Sword extends Weapon {

    public boolean applyBleeding;
    public boolean twoHanded;

    public void applyBleeding() {

    }
    Sword(String name, int attack, String rarity, boolean applyBleeding, boolean twoHanded) {
        this.name = name;
        this.attack = attack;
        this.rarity = rarity;
        this.applyBleeding = applyBleeding;
        this.twoHanded = twoHanded;
    }

    @Override
    protected void getEffect() {

    }

    @Override
    public String toString() {
        return String.format(
                "Sword[name=%s, attack=%d, rarity=%s, applyBleeding=%b, twoHanded=%b]",
                name, attack, rarity, applyBleeding, twoHanded);
    }

}