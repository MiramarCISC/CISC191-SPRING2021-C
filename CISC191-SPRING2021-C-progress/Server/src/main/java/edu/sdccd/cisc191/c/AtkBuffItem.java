package edu.sdccd.cisc191.c;

public class AtkBuffItem extends Item {
    private int atkIncrease;

    AtkBuffItem(String name, int atkIncrease, int duration, int buyPrice, int sellPrice) {
        this.name = name;
        this.atkIncrease = atkIncrease;
        this.cooldown = duration;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        quantity = 0;
        stackable = true;
    }


    public int getAtkIncrease() {
        return atkIncrease;
    }

    public void setAtkIncrease(int atkIncrease) {
        this.atkIncrease = atkIncrease;
    }



    @Override
    protected void useItem(Battler participant) {

    }

    @Override
    protected void getEffect() {

    }
}
