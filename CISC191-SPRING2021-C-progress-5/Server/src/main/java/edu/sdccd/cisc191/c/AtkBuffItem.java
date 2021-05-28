package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public class AtkBuffItem extends Item {

    @CsvBindByName(column = "atkIncrease")
    private int atkIncrease;

    public AtkBuffItem(String name, int atkIncrease, int cooldown) {
        this.name = name;
        this.atkIncrease = atkIncrease;
        this.cooldown = cooldown;
        quantity = 0;
        stackable = true;
    }

    public AtkBuffItem() {

    }

    /** This method assumes that buffCD is decreased by 1 after each turn */
    @Override
    public void useOnPM(PartyMember member) {
        if (member.getBuffCD() <= 0) {
            if (quantity >= 1) {
                quantity--;
                member.setCurrAtk(member.getCurrAtk() + atkIncrease);
                member.setBuffCD(cooldown);
            } else {
                System.out.println("You don't have this item!");
            }
        }
        else {
            System.out.println("Item is on cooldown!");
        }
    }

    @Override
    public void useOnEnemy(Enemy enemy) {
        if (enemy.getBuffCD() <= 0) {
            if (quantity >= 1) {
                quantity--;
                enemy.setCurrAtk(enemy.getCurrAtk() + atkIncrease);
                enemy.setBuffCD(cooldown);
            } else {
                System.out.println("You don't have this item!");
            }
        }
        else {
            System.out.println("Item is on cooldown!");
        }
    }

    @Override
    protected void getEffect() {

    }

    @Override
    public String toString() {
        return String.format(
                "AtkBuffItem[name=%s, atkIncrease=%d, cooldown=%d, quantity=%d, stackable=%b, atkIncrease=%d]",
                name, atkIncrease, cooldown, quantity, stackable, atkIncrease);
    }

}