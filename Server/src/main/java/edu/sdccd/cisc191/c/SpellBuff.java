package edu.sdccd.cisc191.c;

public class SpellBuff extends Spell{

    private int duration;
    // Amount of turns
    private int stat;
    // 0 is defense, 1 is attack, 2 is speed
    private int bump;
    // Amount of stat change

    public SpellBuff(String call, int mL, int cst, boolean BoW, int dur, int st, int bmp) {
        super(call, mL, cst, BoW);
        duration = dur;
        stat = st;
        bump = bmp;
    }

    @Override
    protected void castOnE(Enemy enemy) {

    }

    @Override
    protected void castOnPM(PartyMember member) {

    }

}