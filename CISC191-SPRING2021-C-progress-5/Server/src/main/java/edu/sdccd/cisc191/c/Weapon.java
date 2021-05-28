package edu.sdccd.cisc191.c;

public abstract class Weapon extends Item {

    protected int attack;
    protected String rarity;
    protected boolean applyFire;
    protected boolean applyPoison;
    protected boolean applyElec;
    protected boolean applyIce;

    public Weapon() {
        equippable = true;
    }

    public void setAttack(int atk) {
        attack = atk;
    }

    public int getAttack() {
        return attack;
    }

    public void applyFire() {

    }

    public void applyPoison() {

    }

    public void applyElec() {

    }

    public void applyIce() {

    }

    @Override
    public void useOnPM(PartyMember member) {

    }

    @Override
    public void useOnEnemy(Enemy enemy) {

    }

}