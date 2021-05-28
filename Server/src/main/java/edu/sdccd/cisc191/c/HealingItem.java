package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public class HealingItem extends Item {

    @CsvBindByName(column = "healingAmount")
    private int healingAmount;

    public HealingItem(String name, int healingAmount, int cooldown) {
        this.name = name;
        this.healingAmount = healingAmount;
        this.cooldown = cooldown;
        quantity = 0;
        stackable = true;
    }

    public HealingItem() {

    }


    @Override
    public void useOnPM(PartyMember member) {
        if (member.getBuffCD() <= 0) {
            if (quantity >= 1) {
                quantity--;
                int HP = member.getCurrHP() + healingAmount;
                if (HP >= member.getMaxHP()) {
                    member.setCurrHP(member.getMaxHP());
                }
                else {
                    member.setCurrHP(HP);
                }
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
                int HP = enemy.getCurrHP() + healingAmount;
                if (HP >= enemy.getMaxHP()) {
                    enemy.setCurrHP(enemy.getMaxHP());
                }
                else {
                    enemy.setCurrHP(HP);
                }
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
                "HealingBuffItem[name=%s, atkIncrease=%d, cooldown=%d, quantity=%d, stackable=%b. healingAmount=%d]",
                name, healingAmount, cooldown, quantity, stackable, healingAmount);
    }

}