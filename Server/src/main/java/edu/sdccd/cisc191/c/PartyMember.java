package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public class PartyMember extends Battler {

    private Knight knight1H;
    private Knight knight2H;

    private Ranged thrower;
    private Ranged archer;

    private Mage whiteMage;
    private Mage blackMage;
    private Mage redMage;

    // Can only be 0 (1H-Knight), 1 (2H-Knight), 2 (Throw), 3 (Archer), 4 (White Mage), 5 (Black Mage), or 6 (Red Mage)
    private int currJob;

    // Stores equipment objects and
    private CharacterEquipment charEq;
    private int weaponAtk;
    private int armorDef;

    public void evalEquip() {
        weaponAtk = 0;

        if (currJob < 2) {
            if (!charEq.getSwd1().equals(null)) {
                weaponAtk += charEq.getSwd1().getAttack();
            }

            if (!charEq.getSwd2().equals(null)) {
                weaponAtk += charEq.getSwd2().getAttack();
            }
        }

        if (currJob > 3) {
            if (!charEq.getStf1().equals(null)) {
                weaponAtk += charEq.getStf1().getAttack();
            }

            if (!charEq.getStf2().equals(null)) {
                weaponAtk += charEq.getStf2().getAttack();
            }
        }

        if (currJob == 2) {
            if (!charEq.getTrw().equals(null)) {
                weaponAtk += charEq.getTrw().getAttack();
            }
        }

        if (currJob == 3) {
            if (!charEq.getBow().equals(null)) {
                weaponAtk += charEq.getBow().getAttack();
            }
        }

        if ((currJob < 2) || (currJob > 3)) {
            if (!charEq.getShield().equals(null)) {
                weaponAtk -= charEq.getShield().getDmgReduction();
            }
        }

        armorDef = 0;

        if (!charEq.getHead().equals(null)) {
            armorDef += charEq.getHead().getDefense();
        }

        if (!charEq.getBody().equals(null)) {
            armorDef += charEq.getBody().getDefense();
        }

        if (!charEq.getLegs().equals(null)) {
            armorDef += charEq.getLegs().getDefense();
        }

        if (!charEq.getFeet().equals(null)) {
            armorDef += charEq.getFeet().getDefense();
        }

        if (!charEq.getShield().equals(null)) {
            armorDef += charEq.getShield().getDefIncrease();
        }

    }

    public void changeSwd1(Sword newSwd1) {
        if ((currJob == 0) && (!charEq.getSwd2().equals(null))) {
            System.out.println(name + " can only hold one sword.");
        }
        else if ((currJob == 1) && (!charEq.getSwd2().equals(null)) && (!charEq.getShield().equals(null))) {
            System.out.println(name + "'s hands are already full. Un-equip a sword or shield.");
        }
        else if (currJob > 1) {
            System.out.println(name + " cannot hold a sword.");
        }
        else {
            charEq.setSwd1(newSwd1);
            System.out.println(name + " is now holding a " + newSwd1.getItemName() + ".");
            evalEquip();
        }
    }

    public void changeSwd2(Sword newSwd2) {
        if ((currJob == 0) && (!charEq.getSwd1().equals(null))) {
            System.out.println(name + " cannot hold another sword.");
        }
        else if ((currJob == 1) && (!charEq.getSwd1().equals(null)) && (!charEq.getShield().equals(null))) {
            System.out.println(name + "'s hands are already full. Un-equip a sword or shield.");
        }
        else if (currJob > 1) {
            System.out.println(name + " cannot hold a sword.");
        }
        else {
            charEq.setSwd2(newSwd2);
            System.out.println(name + " is now holding a " + newSwd2.getItemName() + ".");
            evalEquip();
        }
    }

    public void changeStf1(Staff newStf1) {
        if (currJob < 4) {
            System.out.println(name + " cannot hold a staff.");
        }
        else if ((!charEq.getStf2().equals(null)) && (!charEq.getShield().equals(null))) {
            System.out.println(name + "'s hands are already full. Un-equip a staff or shield.");
        }
        else {
            charEq.setStf1(newStf1);
            System.out.println(name + " is now holding a " + newStf1.getItemName() + ".");
            evalEquip();
        }
    }

    public void changeStf2(Staff newStf2) {
        if (currJob < 4) {
            System.out.println(name + " cannot hold a staff.");
        }
        else if ((!charEq.getStf1().equals(null)) && (!charEq.getShield().equals(null))) {
            System.out.println(name + "'s hands are already full. Un-equip a staff or shield.");
        }
        else {
            charEq.setStf2(newStf2);
            System.out.println(name + " is now holding a " + newStf2.getItemName() + ".");
        }
    }

    public void changeBow(Bow newBow) {
        if (currJob != 3) {
            System.out.println(name + " cannot hold a bow.");
        }
        else {
            charEq.setBow(newBow);
            System.out.println(name + " is now holding a " + newBow.getItemName() + ".");
        }
    }

    public void changeThrow(Throwable newTrw) {
        if (currJob != 2) {
            System.out.println(name + " cannot hold a throwable object.");
        }
        else {
            charEq.setTrw(newTrw);
            System.out.println(name + " is now holding a " + newTrw.getItemName() + ".");
        }
    }

    public void changeHead(Head newHead) {
        charEq.setHead(newHead);
        System.out.println(name + " is now wearing a " + newHead.getItemName() + ".");
    }

    public void changeBody(Body newBody) {
        charEq.setBody(newBody);
        System.out.println(name + " is now wearing a " + newBody.getItemName() + ".");
    }

    public void changeLegs(Legs newLegs) {
        charEq.setLegs(newLegs);
        System.out.println(name + " is now wearing " + newLegs.getItemName() + ".");
    }

    public void changeFeet(Feet newFeet) {
        charEq.setFeet(newFeet);
        System.out.println(name + " is now wearing " + newFeet.getItemName() + ".");
    }

    public void changeShield(Shield newShield) {
        if ((currJob == 0) || (currJob == 2) || (currJob == 3)) {
            System.out.println(name + " cannot hold a shield.");
        }
        else if (!charEq.getSwd1().equals(null) && !charEq.getSwd2().equals(null)) {
            System.out.println(name + "'s hands are already full. Un-equip a sword.");
        }
        else if (!charEq.getStf1().equals(null) && !charEq.getStf2().equals(null)) {
            System.out.println(name + "'s hands are already full. Un-equip a staff.");
        }
        else {
            charEq.setShield(newShield);
            System.out.println(name + " is now holding a " + newShield.getItemName() + ".");
        }
    }

    public void changeJob(int newJob, ArrayList<Spell> allSpells) {

        currJob = newJob;
        charEq = new CharacterEquipment();

        switch (newJob) {
            case 0:
                level = knight1H.expToLevel(exp);
                // Change stats based on class and level
                break;
            case 1:
                level = knight2H.expToLevel(exp);
                // Change stats based on class and level
                break;
            case 2:
                level = thrower.expToLevel(exp);
                // Change stats based on class and level
                break;
            case 3:
                level = archer.expToLevel(exp);
                // Change stats based on class and level
                break;
            case 4:
                level = whiteMage.expToLevel(exp);
                whiteMage.setLegalSpells(name, level, allSpells);
                // Change stats based on class and level
                break;
            case 5:
                level = blackMage.expToLevel(exp);
                blackMage.setLegalSpells(name, level, allSpells);
                // Change stats based on class and level
                break;
            case 6:
                level = redMage.expToLevel(exp);
                redMage.setLegalSpells(name, level, allSpells);
                // Change stats based on class and level
                break;
        }

    }

/*
    public void changeEq(CharacterEquipment newEq) {
        if (currJob < 1) {

        } else if (currJob < 2) {

        } else if (currJob < 4) {

        } else {

        }
    }
 */

    public void setCurrJob(int c) {
        currJob = c;
    }

    public void setArmorDef(int armD) {
        armorDef = armD;
    }

    public void setWeaponAtk(int wepA) {
        weaponAtk = wepA;
    }

    public int getCurrJob() {
        return currJob;
    }

    public int getArmorDef() {
        return armorDef;
    }

    public int getWeaponAtk() {
        return weaponAtk;
    }

    @Override
    protected void moveHappens() {

    }

    @Override
    protected void setStatus(StatusEffect newStatus) {

    }

    @Override
    protected StatusEffect getStatus() {
        return null;
    }
}
