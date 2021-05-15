package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public class MagicMon extends Monster implements Spellcaster {

    private int baseMP;
    private boolean white;
    private boolean black;
    private SpellBook spellbook;

    public MagicMon(String type, int bDef, int bAtk, int bSpd, int bHP, int bMP, boolean wht, boolean blk) {
        super(type, bDef, bAtk, bSpd, bHP);
        baseMP = bMP;
        white = wht;
        black = blk;
    }

    public void setBaseMP(int bMP) { baseMP = bMP; }

    public void setWhite(boolean w) { white = w; }

    public void setBlack(boolean b) { black = b; }

    public void setSpellBook(SpellBook sb) { spellbook = sb; }

    public int getBaseMP() { return baseMP; }

    public boolean getWhite() { return white; }

    public boolean getBlack() { return black; }

    public SpellBook getSpellBook() { return spellbook; }

    @Override
    public void fillBook(ArrayList<SpellAoH> mLA, ArrayList<SpellAoH> mLH, ArrayList<SpellBuff> mLI,
                         ArrayList<SpellBuff> mLD, ArrayList<SpellSE> mLN, ArrayList<SpellSE> mLC, int level) {
        spellbook = new SpellBook(mLA, mLH, mLI, mLD, mLN, mLC, level, white, black);
    }

    @Override
    public void splCast(Spell spl) {

    }
}