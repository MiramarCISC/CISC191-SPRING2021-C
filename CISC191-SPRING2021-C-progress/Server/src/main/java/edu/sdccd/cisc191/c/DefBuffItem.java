package edu.sdccd.cisc191.c;

public class DefBuffItem extends Item {
    private int defIncrease;

    DefBuffItem(String name, int defIncrease, int cooldown, int buyPrice, int sellPrice) {
        this.name = name;
        this.defIncrease = defIncrease;
        this.cooldown = cooldown;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        quantity = 0;
        stackable = true;
    }

    public int getDefIncrease() {
        return defIncrease;
    }

    public void setDefIncrease(int defIncrease) {
        this.defIncrease = defIncrease;
    }

    @Override
    protected void useItem(Battler participant) {

    }

    @Override
    protected void getEffect() {

    }
}
