package edu.sdccd.cisc191.c;

/**
 * This is the interface that will be used for anonymous classes in every subclass of Job so that Party Members and
 * Enemies can calculate stats based on their level and type. Look for usage in these classes:
 * Knight, Ranged, Mage
 */
public interface StatCalc {

    public abstract int setMaxHP();
    public abstract int setMaxDef();
    public abstract int setMaxAtk();
    public abstract int setMaxSpd();
    public abstract int setMaxMP();

}