package edu.sdccd.cisc191.c;

public class HealingItem extends Item {
    private int healingAmount;

    HealingItem(String name, int healingAmount, int cooldown, int buyPrice, int sellPrice) {
        this.name = name;
        this.healingAmount = healingAmount;
        this.cooldown = cooldown;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        quantity = 0;
        stackable = true;
    }


    public int getHealingAmount() {
        return healingAmount;
    }

    public void setHealingAmount(int healingAmount) {
        this.healingAmount = healingAmount;
    }


    @Override
    protected void useItem(Battler participant) {

    }

    @Override
    protected void getEffect() {

    }
}