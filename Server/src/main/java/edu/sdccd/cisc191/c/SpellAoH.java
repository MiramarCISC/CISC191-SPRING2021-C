package edu.sdccd.cisc191.c;

public class SpellAoH extends Spell{

    private int element;
    // 0 for none, 1 for fire, 2 for water, 3 for electric
    private int damage;
    // Damage wil be calculated in a (1 + x/100)*atk formula
    // HP healed will be calculated in a (x/100)*maxHP formula

    public SpellAoH(String call, int mL, int cst, boolean BoW, int el, int dmg) {
        super(call, mL, cst, BoW);
        element = el;
        damage = dmg;
    }

    @Override
    protected void castOnE(Enemy enemy) {

    }

    @Override
    protected void castOnPM(PartyMember member) {

    }

}