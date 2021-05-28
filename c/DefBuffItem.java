package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public class DefBuffItem extends Item {

    @CsvBindByName(column = "defIncrease")
    private int defIncrease;

    DefBuffItem(String name, int defIncrease, int cooldown) {
        this.name = name;
        this.defIncrease = defIncrease;
        this.cooldown = cooldown;
        quantity = 0;
        stackable = true;
    }

    public DefBuffItem() {

    }

    /** This method assumes that buffCD is decreased by 1 after each turn */
    @Override
    public void useOnPM(PartyMember member) {
        if (member.getBuffCD() <= 0) {
            if (quantity >= 1) {
                quantity--;
                member.setCurrDef(member.getCurrDef() + defIncrease);
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
                enemy.setCurrDef(enemy.getCurrDef() + defIncrease);
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
                "DefBuffItem[name=%s, defIncrease=%d, cooldown=%d, quantity=%d, stackable=%b, defIncrease=%d]",
                name, defIncrease, cooldown, quantity, stackable, defIncrease);
    }

}