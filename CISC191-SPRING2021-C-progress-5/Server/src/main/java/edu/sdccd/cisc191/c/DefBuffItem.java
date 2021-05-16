package edu.sdccd.cisc191.c;

public class DefBuffItem extends Item {
    private final int defIncrease;

    DefBuffItem(String name, int defIncrease, int cooldown) {
        this.name = name;
        this.defIncrease = defIncrease;
        this.cooldown = cooldown;
        quantity = 0;
        stackable = true;
    }

    /** This method assumes that buffCD is decreased by 1 after each turn */
    @Override
    protected void useItem(Battler participant) {
        if (participant.getBuffCD() <= 0) {
            if (quantity >= 1) {
                quantity--;
                participant.setCurrDef(participant.getCurrDef() + defIncrease);
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