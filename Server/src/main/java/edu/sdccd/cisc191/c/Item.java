package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public abstract class Item {

    @CsvBindByName(column = "STACKABLE")
    protected boolean stackable;

    @CsvBindByName(column = "EQUIPPABLE")
    public boolean equippable;

    @CsvBindByName(column = "NAME")
    protected String name;

    @CsvBindByName(column = "DESCRIPTION")
    protected String description;

    @CsvBindByName(column = "QUANTITY")
    protected int quantity;

    @CsvBindByName(column = "COOLDOWN")
    protected int cooldown;

    @CsvBindByName(column = "RARITY")
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