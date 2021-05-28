package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public class SpellAoH extends Spell{

    @CsvBindByName(column = "element")
    private int element;
    // 0 for none, 1 for fire, 2 for water, 3 for electric

    @CsvBindByName(column = "damage")
    private int damage;
    // Damage wil be calculated in a (1 + x/100)*atk formula
    // HP healed will be calculated in a (x/100)*maxHP formula

    // If BoW is true (white), the damage is added to the HP. If BoW is false (black), the damage is subtracted from it

    public SpellAoH() {

    }

    public SpellAoH(String call, int mL, int cst, boolean BoW, int el, int dmg) {
        super(call, mL, cst, BoW);
        element = el;
        damage = dmg;
    }

    public void setElement(int el) {
        element = el;
    }

    public void setDamage(int dmg) {
        damage = dmg;
    }

    public int getElement() {
        return element;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    protected void castOnE(Enemy enemy) {

        if (color) {
            enemy.setCurrHP(enemy.getCurrHP() + damage);
            if (enemy.getCurrHP() > enemy.getMaxHP()) {
                enemy.setCurrHP(enemy.getMaxHP());
            }
        }
        else {
            enemy.setCurrHP(enemy.getCurrHP() - damage);
            if (enemy.getCurrHP() < 0) {
                enemy.setCurrHP(0);
            }
        }

    }

    @Override
    protected void castOnPM(PartyMember member) {

        if (color) {
            member.setCurrHP(member.getCurrHP() + damage);
            if (member.getCurrHP() > member.getMaxHP()) {
                member.setCurrHP(member.getMaxHP());
            }
        }
        else {
            member.setCurrHP(member.getCurrHP() - damage);
            if (member.getCurrHP() < 0) {
                member.setCurrHP(0);
            }
        }

    }

    @Override
    public String toString() {
        return String.format(
                "SpellAoH[name=%s, minLev=%d, cost=%d, color=%b, element=%d, damage=%d]",
                name, minLev, cost, color, element, damage);
    }

}