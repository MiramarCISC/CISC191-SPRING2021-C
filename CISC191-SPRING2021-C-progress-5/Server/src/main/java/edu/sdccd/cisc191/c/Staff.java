package edu.sdccd.cisc191.c;

public class Staff extends Weapon {

    Staff(String name, int attack, String rarity, boolean applyFire, boolean applyElec, boolean applyIce) {
        this.name = name;
        this.attack = attack;
        this.rarity = rarity;
        this.applyFire = applyFire;
        this.applyElec = applyElec;
        this.applyIce = applyIce;
    }

    @Override
    protected void getEffect() {

    }

    @Override
    public String toString() {
        return String.format(
                "Staff[name=%s, attack=%d, rarity=%s, applyFire=%b, applyElec=%b, applyIce=%b]",
                name, attack, rarity, applyFire, applyElec, applyIce);
    }

}