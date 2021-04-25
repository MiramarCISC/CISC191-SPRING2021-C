package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public class MagicMon extends Monster implements Spellcaster {

    private ArrayList<Spell> spellList;
    private int baseMP;

    @Override
    public boolean checkPresence(Spell spell, ArrayList<Spell> mySpells) {
        return false;
    }

    @Override
    public void setLegalSpells(String name, int level, ArrayList<Spell> masterList) {

    }

    @Override
    public ArrayList<Spell> getLegalSpells() {
        return spellList;
    }

    @Override
    public void splCast(Spell spl) {

    }
}
