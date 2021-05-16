package edu.sdccd.cisc191.c;

public class HealingItem extends Item {
    private final int healingAmount;

    HealingItem(String name, int healingAmount, int cooldown) {
        this.name = name;
        this.healingAmount = healingAmount;
        this.cooldown = cooldown;
        quantity = 0;
        stackable = true;
    }


    @Override
    protected void useItem(Battler participant) {
        if (participant.getBuffCD() <= 0) {
            if (quantity >= 1) {
                quantity--;
                int HP = participant.getCurrHP() + healingAmount;
                if (HP >= participant.getMaxHP()) {
                    participant.setCurrHP(participant.getMaxHP());
                }
                else {
                    participant.setCurrHP(HP);
                }
                participant.setBuffCD(cooldown);
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
}