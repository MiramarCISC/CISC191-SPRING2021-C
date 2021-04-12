package edu.sdccd.cisc191.c;

public class SpellAoH extends Spell{

    private int element;
    private int damage;

    public SpellAoH(String call, int mL, int cst, boolean BoW, int el, int dmg) {
        super(call, mL, cst, BoW);
        element = el;
        damage = dmg;
    }

    @Override
    protected void cast(Battler participant) {

    }

}
