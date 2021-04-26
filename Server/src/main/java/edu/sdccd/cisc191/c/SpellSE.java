package edu.sdccd.cisc191.c;

public class SpellSE extends Spell{

    private int duration;
    private int effect;

    public SpellSE(String call, int mL, int cst, boolean BoW, int dur, int ef) {
        super(call, mL, cst, BoW);
        duration = dur;
        effect = ef;
    }

    @Override
    protected void cast(Battler participant) {

    }
}