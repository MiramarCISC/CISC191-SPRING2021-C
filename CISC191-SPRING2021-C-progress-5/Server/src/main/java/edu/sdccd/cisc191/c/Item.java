package edu.sdccd.cisc191.c;

public abstract class Item {
    protected boolean inBattle;
    protected boolean stackable;
    public boolean equippable;
    protected boolean key;
    protected String name;
    protected String description;
    protected int quantity;
    protected int cooldown;
    protected String rarity;

    protected abstract void useItem(Battler participant);
    protected abstract void getEffect();

    protected void increaseQuantity() {
        quantity++;
    }

    protected void decreaseQuantity() {
        quantity--;
    }

    protected String getName() {
        return name;
    }

    protected String getRarity() { return rarity; }

    protected String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCooldown() { return cooldown; }


}