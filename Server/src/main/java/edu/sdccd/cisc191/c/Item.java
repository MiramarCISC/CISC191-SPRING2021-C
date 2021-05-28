package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public abstract class Item {

    @CsvBindByName(column = "stackable")
    protected boolean stackable;

    @CsvBindByName(column = "equippable")
    public boolean equippable;

    @CsvBindByName(column = "name")
    protected String name;

    @CsvBindByName(column = "description")
    protected String description;

    @CsvBindByName(column = "quantity")
    protected int quantity;

    @CsvBindByName(column = "cooldown")
    protected int cooldown;

    @CsvBindByName(column = "rarity")
    protected String rarity;

    // protected boolean inBattle;
    // protected boolean key;

    public abstract void useOnPM(PartyMember member);
    public abstract void useOnEnemy(Enemy enemy);

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