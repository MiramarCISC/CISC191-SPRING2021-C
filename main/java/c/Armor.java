package c;

public abstract class Armor extends Item {

    protected int defense;
    protected String rarity;
    protected boolean magical;

    public Armor() {
        equippable = true;
    }

    @Override
    protected void useItem(Battler participant) {

    }

}

