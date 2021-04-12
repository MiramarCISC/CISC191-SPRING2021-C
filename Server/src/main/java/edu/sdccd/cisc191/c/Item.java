package edu.sdccd.cisc191.c;

public abstract class Item {
    protected boolean inBattle;
    protected boolean stackable;
    public boolean equippable;
    protected boolean key;
    protected String itemName;
    protected String description;
    protected int buyPrice;
    protected int sellPrice;

    protected abstract void useItem(Battler participant);
    protected abstract void getEffect();

    protected String getItemName() {
        return itemName;
    }

    protected String getDescription() {
        return description;
    }

}
