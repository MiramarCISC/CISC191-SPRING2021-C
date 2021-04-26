package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public interface Spellcaster {

    public boolean checkPresence(Spell spell, ArrayList<Spell> mySpells);
    public void setLegalSpells(String name, int level, ArrayList<Spell> masterList);
    public ArrayList<Spell> getLegalSpells();
    public void splCast(Spell spl);

}