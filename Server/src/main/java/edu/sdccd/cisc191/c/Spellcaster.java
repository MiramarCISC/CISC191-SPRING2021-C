package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public interface Spellcaster {

    public abstract void fillBook(ArrayList<SpellAoH> mLA, ArrayList<SpellAoH> mLH, ArrayList<SpellBuff> mLI,
                         ArrayList<SpellBuff> mLD, ArrayList<SpellSE> mLN, ArrayList<SpellSE> mLC, int level,
                         boolean white, boolean black);

    public void splCast(Spell spl);

}