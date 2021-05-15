package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public class SpellBook {

    private ArrayList<SpellAoH> listAtk = new ArrayList<SpellAoH>();
    private ArrayList<SpellAoH> listHeal = new ArrayList<SpellAoH>();

    private ArrayList<SpellBuff> listInc = new ArrayList<SpellBuff>();
    private ArrayList<SpellBuff> listDec = new ArrayList<SpellBuff>();

    private ArrayList<SpellSE> listInf = new ArrayList<SpellSE>();
    private ArrayList<SpellSE> listCure = new ArrayList<SpellSE>();

    public SpellBook() {

    }

    public SpellBook(ArrayList<SpellAoH> mLA, ArrayList<SpellAoH> mLH, ArrayList<SpellBuff> mLI,
                     ArrayList<SpellBuff> mLD, ArrayList<SpellSE> mLN, ArrayList<SpellSE> mLC, int level,
                     boolean white, boolean black) {

        /**
         * In order, these lambda expressions accomplish the following:
         * Check each spell from the master attack list, and if the user can wield it, add it to their spell book
         * Check each spell from the master healing list, and if the user can wield it, add it to their spell book
         * Check each spell from the master buff list, and if the user can wield it, add it to their spell book
         * Check each spell from the master de-buff list, and if the user can wield it, add it to their spell book
         * Check each spell from the master inflict list, and if the user can wield it, add it to their spell book
         * Check each spell from the master cure list, and if the user can wield it, add it to their spell book
         */
        mLA.forEach(i -> { if ((level >= i.getMinLev()) && (black)) listAtk.add(i);});

        mLH.forEach(i -> { if ((level >= i.getMinLev()) && (white)) listHeal.add(i);});

        mLI.forEach(i -> { if ((level >= i.getMinLev()) && (white)) listInc.add(i);});

        mLD.forEach(i -> { if ((level >= i.getMinLev()) && (black)) listDec.add(i);});

        mLN.forEach(i -> { if ((level >= i.getMinLev()) && (black)) listInf.add(i);});

        mLC.forEach(i -> { if ((level >= i.getMinLev()) && (black)) listCure.add(i);});

    }

    public static void main(String[] args) {

        ArrayList<SpellAoH> masterListAtk = new ArrayList<SpellAoH>();

        masterListAtk.add(new SpellAoH("Fire Ball", 1, 3, false, 1, 10));
        masterListAtk.add(new SpellAoH("Fire Plume", 3, 5, false, 1, 20));
        masterListAtk.add(new SpellAoH("Flame Riser", 7, 7, false, 1, 30));
        masterListAtk.add(new SpellAoH("Flame Blast", 13, 9, false, 1, 40));
        masterListAtk.add(new SpellAoH("Heat Sink", 20, 11, false, 1, 50));

        masterListAtk.add(new SpellAoH("Water Stream", 1, 3, false, 2, 10));
        masterListAtk.add(new SpellAoH("Water Cutter", 3, 5, false, 2, 20));
        masterListAtk.add(new SpellAoH("Rapid Stream", 7, 7, false, 2, 30));
        masterListAtk.add(new SpellAoH("Liquid Bullets", 13, 9, false, 2, 40));
        masterListAtk.add(new SpellAoH("Glacier Crusher", 20, 11, false, 2, 50));

        masterListAtk.add(new SpellAoH("Small Shock", 1, 3, false, 2, 10));
        masterListAtk.add(new SpellAoH("Large Shock", 3, 5, false, 2, 20));
        masterListAtk.add(new SpellAoH("Thunder Coup", 7, 7, false, 2, 30));
        masterListAtk.add(new SpellAoH("Thunder Reign", 13, 9, false, 2, 40));
        masterListAtk.add(new SpellAoH("Lightning Bolt", 20, 11, false, 2, 50));

        ArrayList<SpellAoH> masterListHeal = new ArrayList<SpellAoH>();

        masterListHeal.add(new SpellAoH("Small Heal", 3, 3, true, 0, 12));
        masterListHeal.add(new SpellAoH("Moderate Heal", 5, 7, true, 0, 24));
        masterListHeal.add(new SpellAoH("Large Heal", 9, 11, true, 0, 36));
        masterListHeal.add(new SpellAoH("Half Heal", 17, 15, true, 0, 50));
        masterListHeal.add(new SpellAoH("Full Heal", 20, 25, true, 0, 100));

        ArrayList<SpellBuff> masterListInc = new ArrayList<SpellBuff>();

        masterListInc.add(new SpellBuff("Fortify", 7, 7, true, 2, 0, 10));
        masterListInc.add(new SpellBuff("Strengthen", 7, 7, true, 2, 1, 10));
        masterListInc.add(new SpellBuff("Haste", 7, 7, true, 2, 2, 10));

        ArrayList<SpellBuff> masterListDec = new ArrayList<SpellBuff>();

        masterListDec.add(new SpellBuff("Disrupt", 10, 7, false, 2, 0, 10));
        masterListDec.add(new SpellBuff("Weaken", 10, 7, false, 2, 1, 10));
        masterListDec.add(new SpellBuff("Slow", 10, 7, false, 2, 2, 10));

        ArrayList<SpellSE> masterListInf = new ArrayList<SpellSE>();

        masterListInf.add(new SpellSE("Paralyze", 5, 5, false, 2, 0));
        masterListInf.add(new SpellSE("Burn", 5, 5, false, 2, 1));
        masterListInf.add(new SpellSE("Poison", 5, 5, false, 2, 2));
        masterListInf.add(new SpellSE("Lull", 5, 5, false, 2, 3));

        ArrayList<SpellSE> masterListCure = new ArrayList<SpellSE>();

        masterListCure.add(new SpellSE("Mobilize", 5, 5, true, 0, 0));
        masterListCure.add(new SpellSE("Cool Down", 5, 5, true, 0, 1));
        masterListCure.add(new SpellSE("Anti-Venom", 5, 5, true, 0, 2));
        masterListCure.add(new SpellSE("Awaken", 5, 5, true, 0, 3));

        SpellBook rMageSB = new SpellBook(masterListAtk, masterListHeal, masterListInc, masterListDec, masterListInf,
                masterListCure, 10, true, true);

    }


}
