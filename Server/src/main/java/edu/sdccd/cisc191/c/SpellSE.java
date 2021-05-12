package edu.sdccd.cisc191.c;

public class SpellSE extends Spell{

    // Number of turns effect will last
    private int duration;
    // 0 for stunned, 1 for burned, 2 for poisoned, 3 for asleep
    private int effect;
    // If BoW is true (white), condition is cured. If BoW is false (black), condition is inflicted

    public SpellSE(String call, int mL, int cst, boolean BoW, int dur, int ef) {
        super(call, mL, cst, BoW);
        duration = dur;
        effect = ef;
    }

    @Override
    protected void castOnE(Enemy enemy) {

    }

    @Override
    protected void castOnPM(PartyMember member) {

    }

}