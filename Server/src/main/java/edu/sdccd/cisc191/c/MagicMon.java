package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public class MagicMon extends Monster implements Spellcaster {

    private int baseMP;
    private SpellBook spellbook;

    public void setBaseMP(int bMP) { baseMP = bMP; }

    public void setSpellBook(SpellBook sb) {spellbook = sb;}

    public int getBaseMP() { return baseMP; }

    public SpellBook getSpellBook() { return spellbook; }

    @Override
    public void fillBook(ArrayList<SpellAoH> mLA, ArrayList<SpellAoH> mLH, ArrayList<SpellBuff> mLI,
                         ArrayList<SpellBuff> mLD, ArrayList<SpellSE> mLN, ArrayList<SpellSE> mLC, int level,
                         boolean white, boolean black) {

    }

    @Override
    public void splCast(Spell spl) {

    }
}