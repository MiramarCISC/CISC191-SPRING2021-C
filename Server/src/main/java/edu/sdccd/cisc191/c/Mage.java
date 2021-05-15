package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Collections;

public class Mage extends Job implements Spellcaster {

    private boolean wMage;
    private boolean bMage;
    private int baseMP;
    private SpellBook spellbook;

    public Mage(String type, int bDef, int bAtk, int bSpd, int bHP, boolean white, boolean black) {
        super(type, bDef, bAtk, bSpd, bHP);
        magical = true;
        ranged = false;
        wMage = white;
        bMage = black;
    }

    public void setWMage(boolean white) { wMage = white; }

    public void setBMage(boolean black) { bMage = black; }

    public void setBaseMP(int bMP) { baseMP = bMP; }

    public void setSpellBook(SpellBook sb) {spellbook = sb;}

    public boolean getWMage() { return wMage; }

    public boolean getBMage() { return bMage; }

    public int getBaseMP() { return baseMP; }

    public SpellBook getSpellBook() { return spellbook; }

    @Override
    public int expToLevel(int exp) {
        return 0;
    }

    @Override
    public void fillBook(ArrayList<SpellAoH> mLA, ArrayList<SpellAoH> mLH, ArrayList<SpellBuff> mLI,
                         ArrayList<SpellBuff> mLD, ArrayList<SpellSE> mLN, ArrayList<SpellSE> mLC, int level) {
        spellbook = new SpellBook(mLA, mLH, mLI, mLD, mLN, mLC, level, wMage, bMage);
    }

    @Override
    public void splCast(Spell spl) {

    }

    /**
     * The anonymous version of the StatCalc interface gives this Job subclass specific methods that modify its stats
     * differently from the other Job subclasses, and is utilized in PartyMember when leveling up or switching Jobs.
     */
    StatCalc mageCalc = new StatCalc() {

        @Override
        public int setMaxHP() {
            double WIP = baseHP*1.0;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.2; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxDef() {
            double WIP = baseDef;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.2; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxAtk() {
            double WIP = baseAtk;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.2; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxSpd() {
            double WIP = baseSpd;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.2; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxMP() {
            double WIP = baseMP;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.2; }
            return (int)(Math.round(WIP));
        }
    };

    /*
    @Override
    public boolean checkPresence(Spell lookSpell, ArrayList<Spell> mySpells) {
        boolean there = false;

        for (Spell spell : mySpells) {
            if (lookSpell.getName().equals(spell.getName())) {
                there = true;
                break;
            }
        }

        return there;
    }
    */

    /*
    public void setLegalSpells(String name, int level, ArrayList<Spell> masterList) {

        for (Spell newSpell : masterList) {
            if(!(checkPresence(newSpell, spellList))) {
                if ((newSpell.getColor() && wMage) || (!(newSpell.getColor()) && bMage)) {
                    if (newSpell.getMinLev() <= level) {
                        spellList.add(newSpell);
                        System.out.println(name + "learned " + newSpell.getName() + "!");
                    }
                }
            }
        }
    }
    */

    /*
    public ArrayList<Spell> getLegalSpells() {
        return spellList;
    }

    public void printSpells() {
        Collections.sort(spellList);

        for(Spell spell : spellList) {
            spell.printInfo();
        }
    }
    */

}