package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Random;

public class Inventory {
    private final HealingItem smallHealingPotion = new HealingItem("Small Healing Potion", 100, 2);
    private final HealingItem mediumHealingPotion = new HealingItem("Medium Healing Potion", 250, 3);
    private final HealingItem largeHealingPotion = new HealingItem("Large Healing Potion", 500, 4);

    private final AtkBuffItem smallAttackPotion = new AtkBuffItem("Small Attack Potion", 10, 4);
    private final AtkBuffItem mediumAttackPotion = new AtkBuffItem("Medium Attack Potion", 20, 4);
    private final AtkBuffItem largeAttackPotion = new AtkBuffItem("Large Attack Potion", 40, 4);

    private final DefBuffItem smallDefensePotion = new DefBuffItem("Small Defense Potion", 10, 4);
    private final DefBuffItem mediumDefensePotion = new DefBuffItem("Medium Defense Potion", 20, 4);
    private final DefBuffItem largeDefensePotion = new DefBuffItem("Large Defense Potion", 40, 4);

    private final ArrayList<Sword> swords = new ArrayList<>();
    private final ArrayList<Bow> bows = new ArrayList<>();
    private final ArrayList<Staff> staffs = new ArrayList<>();
    private final ArrayList<Throwable> throwable = new ArrayList<>();

    private final ArrayList<Head> heads = new ArrayList<>();
    private final ArrayList<Body> bodies = new ArrayList<>();
    private final ArrayList<Legs> legs = new ArrayList<>();
    private final ArrayList<Feet> feet = new ArrayList<>();

    private final ArrayList<Shield> shields = new ArrayList<>();

    public void showInventory() {
        int i;
        if (!swords.isEmpty()) {
            System.out.println("Swords:");
            for (i = 0; i < swords.size(); i++) {
                System.out.println(swords.get(i).getName() + " [" + swords.get(i).getRarity() + "] ");
            }
            System.out.println();
        }
        if (!bows.isEmpty()) {
            System.out.println("Bows:");
            for (i = 0; i < bows.size(); i++) {
                System.out.println(bows.get(i).getName() + " [" + bows.get(i).getRarity() + "] ");
            }
            System.out.println();
        }
        if (!staffs.isEmpty()) {
            System.out.println("Staffs: ");
            for (i = 0; i < staffs.size(); i++) {
                System.out.println(staffs.get(i).getName() + " [" + staffs.get(i).getRarity() + "] ");
            }
            System.out.println();
        }
        if (!throwable.isEmpty()) {
            System.out.println("Throwable:");
            for (i = 0; i < throwable.size(); i++) {
                System.out.println(throwable.get(i).getName() + " [" + throwable.get(i).getRarity() + "] ");
            }
            System.out.println();
        }
        if (!shields.isEmpty()) {
            System.out.println("Shields: ");
            for (i = 0; i < shields.size(); i++) {
                System.out.println(shields.get(i).getName() + " [" + shields.get(i).getRarity() + "] ");
            }
            System.out.println();
        }
        if (!heads.isEmpty()) {
            System.out.println("Head Armors:");
            for (i = 0; i < heads.size(); i++) {
                System.out.println(heads.get(i).getName() + " [" + heads.get(i).getRarity() + "] ");
            }
            System.out.println();
        }
        if (!bodies.isEmpty()) {
            System.out.println("Body Armors:");
            for (i = 0; i < bodies.size(); i++) {
                System.out.println(bodies.get(i).getName() + " [" + bodies.get(i).getRarity() + "] ");
            }
            System.out.println();
        }
        if (!legs.isEmpty()) {
            System.out.println("Legs Armors:");
            for (i = 0; i < legs.size(); i++) {
                System.out.println(legs.get(i).getName() + " [" + legs.get(i).getRarity() + "] ");
            }
            System.out.println();
        }
        if (!feet.isEmpty()) {
            System.out.println("Feet Armors:");
            for (i = 0; i < feet.size(); i++) {
                System.out.println(feet.get(i).getName() + " [" + feet.get(i).getRarity() + "] ");
            }
            System.out.println();
        }
        if (smallHealingPotion.getQuantity() > 0) {
            System.out.println(smallHealingPotion.getQuantity() + " " + smallHealingPotion.getName());
        }
        if (smallAttackPotion.getQuantity() > 0) {
            System.out.println(smallAttackPotion.getQuantity() + " " + smallAttackPotion.getName());
        }
        if (smallDefensePotion.getQuantity() > 0) {
            System.out.println(smallDefensePotion.getQuantity() + " " + smallDefensePotion.getName());
        }
        if (mediumHealingPotion.getQuantity() > 0) {
            System.out.println(mediumHealingPotion.getQuantity() + " " + mediumHealingPotion.getName());
        }
        if (mediumAttackPotion.getQuantity() > 0) {
            System.out.println(mediumAttackPotion.getQuantity() + " " + mediumAttackPotion.getName());
        }
        if (mediumDefensePotion.getQuantity() > 0) {
            System.out.println(mediumDefensePotion.getQuantity() + " " + mediumDefensePotion.getName());
        }
        if (largeHealingPotion.getQuantity() > 0) {
            System.out.println(largeHealingPotion.getQuantity() + " " + largeHealingPotion.getName());
        }
        if (largeAttackPotion.getQuantity() > 0) {
            System.out.println(largeAttackPotion.getQuantity() + " " + largeAttackPotion.getName());
        }
        if (largeDefensePotion.getQuantity() > 0) {
            System.out.println(largeDefensePotion.getQuantity() + " " + largeDefensePotion.getName());
        }
        if (swords.isEmpty() && bows.isEmpty() && staffs.isEmpty() && throwable.isEmpty() && shields.isEmpty() && heads.isEmpty() && bodies.isEmpty() && legs.isEmpty() && feet.isEmpty()
                && smallHealingPotion.getQuantity() <= 0 && smallAttackPotion.getQuantity() <= 0 && smallDefensePotion.getQuantity() <= 0 && mediumHealingPotion.getQuantity() <= 0 && mediumAttackPotion.getQuantity() <= 0 && mediumDefensePotion.getQuantity() <= 0 && largeHealingPotion.getQuantity() <= 0 && largeAttackPotion.getQuantity() <= 0 && largeDefensePotion.getQuantity() <= 0) {
            System.out.println("Inventory is empty.");
        }
    }

    public void acquireItem() {
        Random rand = new Random();
        int randInt = rand.nextInt(100);
        int randInt2 = rand.nextInt(100);
        if (randInt <= 9) {
            if (randInt2 <= 32) {
                largeHealingPotion.increaseQuantity();
                System.out.println("Obtained " + largeHealingPotion.getName());
            }
            if (randInt2 >= 33 && randInt2 <= 66) {
                largeAttackPotion.increaseQuantity();
                System.out.println("Obtained " + largeAttackPotion.getName());
            }
            if (randInt2 >= 67) {
                largeDefensePotion.increaseQuantity();
                System.out.println("Obtained " + largeDefensePotion.getName());
            }
        }
        if (randInt >= 10 && randInt <= 34) {
            if (randInt2 <= 32) {
                mediumHealingPotion.increaseQuantity();
                System.out.println("Obtained " + mediumHealingPotion.getName());
            }
            if (randInt2 >= 33 && randInt2 <= 66) {
                mediumAttackPotion.increaseQuantity();
                System.out.println("Obtained " + mediumAttackPotion.getName());
            }
            if (randInt2 >= 67) {
                mediumDefensePotion.increaseQuantity();
                System.out.println("Obtained " + mediumDefensePotion.getName());
            }
        }
        if (randInt >= 35) {
            if (randInt2 <= 32) {
                smallHealingPotion.increaseQuantity();
                System.out.println("Obtained " + smallHealingPotion.getName());
            }
            if (randInt2 >= 33 && randInt2 <= 66) {
                smallAttackPotion.increaseQuantity();
                System.out.println("Obtained " + smallAttackPotion.getName());
            }
            if (randInt2 >= 67) {
                smallDefensePotion.increaseQuantity();
                System.out.println("Obtained " + smallDefensePotion.getName());
            }
        }
    }

    public void acquireEquipment() {
        Random rand = new Random();
        int randInt = rand.nextInt(100);
        int randInt2 = rand.nextInt(100);
        if (randInt <= 6) {
            if (randInt2 <= 9) {
                swords.add(new Sword("Excalibur", 50, "Legendary", true, false));
                System.out.println("Congratulation, you received Excalibur!");
            }
            if (randInt2 >= 10 && randInt2 <= 19) {
                swords.add(new Sword("Warrior's Prowess", 75, "Legendary", false, true));
                System.out.println("Congratulation, you received Warrior's Prowess!");
            }
            if (randInt2 >= 20 && randInt2 <= 29) {
                bows.add(new Bow("Dragon's Fang", 65, "Legendary", false, true));
                System.out.println("Congratulation, you received Dragon's Fang!");
            }
            if (randInt2 >= 30 && randInt2 <= 39) {
                bows.add(new Bow("Fire Bow", 65, "Legendary", true, false));
                System.out.println("Congratulation, you received Fire Bow!");
            }
            if (randInt2 >= 40 && randInt2 <= 49) {
                staffs.add(new Staff("Inferno Staff", 65, "Legendary", true, false, false));
                System.out.println("Congratulation, you received Inferno Staff!");
            }
            if (randInt2 >= 50 && randInt2 <= 59) {
                staffs.add(new Staff("Blizzard Staff", 65, "Legendary", false, false, true));
                System.out.println("Congratulation, you received Blizzard Staff!");
            }
            if (randInt2 >= 60 && randInt2 <= 69) {
                staffs.add(new Staff("Thunderer's Staff", 65, "Legendary", false, true, false));
                System.out.println("Congratulation, you received Thunderer's Staff!");
            }
            if (randInt2 >= 70 && randInt2 <= 79) {
                throwable.add(new Throwable("Throwing Shuriken", 50, "Legendary", true));
                System.out.println("Congratulation, you received Throwing Shuriken!");
            }
            if (randInt2 >= 80 && randInt2 <= 89) {
                shields.add(new Shield("Obsidian Shield", 20, 30, "Legendary"));
                System.out.println("Congratulation, you received Obsidian Shield!");
            }
            if (randInt2 >= 90) {
                heads.add(new Head("King's Crown", 50, "Legendary", true));
                bodies.add(new Body("King's armor", 50, "Legendary", true));
                legs.add(new Legs("King's Greaves", 50, "Legendary", true));
                feet.add(new Feet("King's Shoes", 50, "Legendary", true));
                System.out.println("Congratulation, you received King's Armor set!");
            }
        }

        if (randInt >= 7 && randInt <= 19) {
            if (randInt2 <= 9) {
                swords.add(new Sword("Great Sword", 60, "Elite", false, true));
                System.out.println("You received Great Sword");
            }
            if (randInt2 >= 10 && randInt2 <= 19) {
                swords.add(new Sword("Silver Sword", 35, "Elite", true, false));
                System.out.println("You received Silver Sword");
            }
            if (randInt2 >= 20 && randInt2 <= 29) {
                bows.add(new Bow("Longbow", 40, "Elite", true, false));
                System.out.println("You received Longbow");
            }
            if (randInt2 >= 30 && randInt2 <= 39) {
                staffs.add(new Staff("Mage's Fire Staff", 40, "Elite", true, false, false));
                System.out.println("You received Mage's Fire Staff");
            }
            if (randInt2 >= 40 && randInt2 <= 49) {
                staffs.add(new Staff("Mage's Ice Staff", 40, "Elite", false, false, true));
                System.out.println("You received Mage's Ice Staff");
            }
            if (randInt2 >= 50 && randInt2 <= 59) {
                staffs.add(new Staff("Mage's Electro Staff", 40, "Elite", false, true, false));
                System.out.println("You received Mage's Electro Staff");
            }
            if (randInt2 >= 60 && randInt2 <= 69) {
                throwable.add(new Throwable("Throwing Dagger", 40, "Elite", true));
                System.out.println("You received Throwing Dagger");
            }
            if (randInt2 >= 70 && randInt2 <= 79) {
                shields.add(new Shield("Kite Shield", 15, 25, "Elite"));
                System.out.println("You received Kite Shield");
            }
            if (randInt2 >= 80) {
                heads.add(new Head("Knight's Helmet", 40, "Elite", false));
                bodies.add(new Body("Knight's Armor", 40, "Elite", false));
                legs.add(new Legs("Knight's Greaves", 40, "Elite", false));
                feet.add(new Feet("Knight's Shoes", 40, "Elite", false));
                System.out.println("You received Knight Armor set");
            }
        }

        if (randInt >= 20 && randInt <= 49) {
            if (randInt2 <= 9) {
                swords.add(new Sword("Sharpened Blade", 45, "Rare", false, true));
                System.out.println("You received Sharpened Blade");
            }
            if (randInt2 >= 10 && randInt2 <= 19) {
                swords.add(new Sword("Iron Sword", 30, "Rare", false, false));
                System.out.println("You received Iron Sword");
            }
            if (randInt2 >= 20 && randInt2 <= 29) {
                bows.add(new Bow("Finewood Bow", 30, "Rare", false, false));
                System.out.println("You received Finewood Bow");
            }
            if (randInt2 >= 30 && randInt2 <= 39) {
                staffs.add(new Staff("Finewood Fire Staff", 30, "Rare", true, false, false));
                System.out.println("You received Finewood Fire Staff");
            }
            if (randInt2 >= 40 && randInt2 <= 49) {
                staffs.add(new Staff("Finewood Ice Staff", 30, "Rare", false, false, true));
                System.out.println("You received Finewood Ice Staff");
            }
            if (randInt2 >= 50 && randInt2 <= 59) {
                staffs.add(new Staff("Finewood Electro Staff", 30, "Rare", false, true, false));
                System.out.println("You received Finewood Electro Staff");
            }
            if (randInt2 >= 60 && randInt2 <= 69) {
                throwable.add(new Throwable("Throwing Knife", 30, "Rare", false));
                System.out.println("You received Throwing Knife");
            }
            if (randInt2 >= 70 && randInt2 <= 79) {
                shields.add(new Shield("Bronze Shield", 10, 20, "Rare"));
                System.out.println("You received Bronze Shield");
            }
            if (randInt2 >= 80) {
                heads.add(new Head("Soldier's Helmet", 30, "Rare", false));
                bodies.add(new Body("Soldier's Armor", 30, "Rare", false));
                legs.add(new Legs("Soldier's Greaves", 30, "Rare", false));
                feet.add(new Feet("Soldier's Shoes", 30, "Rare", false));
                System.out.println("You received Soldier Armor set");
            }
        }
        if (randInt >= 50){
            if (randInt2 <= 9) {
                swords.add(new Sword("Stone Blade", 35, "Common", false, true));
                System.out.println("You received Stone Blade");
            }
            if (randInt2 >= 10 && randInt2 <= 19) {
                swords.add(new Sword("Bronze Sword", 20, "Common", false, false));
                System.out.println("You received Bronze Sword");
            }
            if (randInt2 >= 20 && randInt2 <= 29) {
                bows.add(new Bow("Recurve Bow", 20, "Common", false, false));
                System.out.println("You received Recurve Bow");
            }
            if (randInt2 >= 30 && randInt2 <= 39) {
                staffs.add(new Staff("Common Fire Staff", 20, "Common", true, false, false));
                System.out.println("You received Common Fire Staff");
            }
            if (randInt2 >= 40 && randInt2 <= 49) {
                staffs.add(new Staff("Common Ice Staff", 20, "Common", false, false, true));
                System.out.println("You received Common Ice Staff");
            }
            if (randInt2 >= 50 && randInt2 <= 59) {
                staffs.add(new Staff("Common Electro Staff", 20, "Common", false, true, false));
                System.out.println("You received Common Electro Staff");
            }
            if (randInt2 >= 60 && randInt2 <= 69) {
                throwable.add(new Throwable("Throwing Needle", 20, "Common", false));
                System.out.println("You received Throwing Needle");
            }
            if (randInt2 >= 70 && randInt2 <= 79) {
                shields.add(new Shield("Wooden Shield", 5, 15, "Common"));
                System.out.println("You received Wooden Shield");
            }
            if (randInt2 >= 80) {
                heads.add(new Head("Leather Hat", 20, "Common", false));
                bodies.add(new Body("Leather Armor", 20, "Common", false));
                legs.add(new Legs("Leather Leggings", 20, "Common", false));
                feet.add(new Feet("Leather Shoes", 20, "Common", false));
                System.out.println("You received Leather Armor set");
            }
        }

    }

    public void obtainedBigChest() {
        for (int i = 0; i < 6; i++) {
            acquireEquipment();
        }
        for (int i = 0; i < 6; i++) {
            acquireItem();
        }
    }

    public void obtainedMediumChest() {
        for (int i = 0; i < 4; i++) {
            acquireEquipment();
        }
        for (int i = 0; i < 4; i++) {
            acquireItem();
        }
    }

    public void obtainedSmallChest() {
        for (int i = 0; i < 2; i++) {
            acquireEquipment();
        }
        for (int i = 0; i < 2; i++) {
            acquireItem();
        }
    }
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.obtainedBigChest();
        System.out.println();

        inventory.obtainedMediumChest();
        System.out.println();

        inventory.obtainedSmallChest();
        System.out.println();

        inventory.showInventory();
    }

}