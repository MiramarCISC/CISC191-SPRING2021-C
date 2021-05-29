package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public class PartyMember extends Battler {
                             // JOB ID: //
                             //-KNIGHT--//
    private Knight knight1H; //    0    //
    private Knight knight2H; //    1    //
                             //-RANGED--//
    private Ranged thrower;  //    2    //
    private Ranged archer;   //    3    //
                             //-MAGE----//
    private Mage whiteMage;  //    4    //
    private Mage blackMage;  //    5    //
    private Mage redMage;    //    6    //

    // Can only be 0 (1H-Knight), 1 (2H-Knight), 2 (Throw), 3 (Archer), 4 (White Mage), 5 (Black Mage), or 6 (Red Mage)
    private int currJob;

    // Stores equipment objects and
    private CharacterEquipment charEq;
    private int weaponAtk;
    private int armorDef;

    public PartyMember(String pmName) {
        name = pmName;
        stunned = false;
        burned = false;
        poisoned = false;
        asleep = false;
        presentSE = false;
        presentBuff = false;
        dead = false;

        exp = 0;
        durSE = 0;
        buffCD = 0;
    }

    public void evalEquip() {
        weaponAtk = 0;

        if (currJob < 2) {
            if (charEq.getSwd1() != null) {
                weaponAtk += charEq.getSwd1().getAttack();
            }

            if (charEq.getSwd2() != null) {
                weaponAtk += charEq.getSwd2().getAttack();
            }
        }

        if (currJob > 3) {
            if (charEq.getStf1() != null) {
                weaponAtk += charEq.getStf1().getAttack();
            }

            if (charEq.getStf2() != null) {
                weaponAtk += charEq.getStf2().getAttack();
            }
        }

        if (currJob == 2) {
            if (charEq.getTrw() != null) {
                weaponAtk += charEq.getTrw().getAttack();
            }
        }

        if (currJob == 3) {
            if (charEq.getBow() != null) {
                weaponAtk += charEq.getBow().getAttack();
            }
        }

        if ((currJob < 2) || (currJob > 3)) {
            if (charEq.getShield() != null) {
                weaponAtk -= charEq.getShield().reduceDamage();
            }
        }

        armorDef = 0;

        if (charEq.getHead() != null) {
            armorDef += charEq.getHead().getDefense();
        }

        if (charEq.getBody() != null) {
            armorDef += charEq.getBody().getDefense();
        }

        if (charEq.getLegs() != null) {
            armorDef += charEq.getLegs().getDefense();
        }

        if (charEq.getFeet() != null) {
            armorDef += charEq.getFeet().getDefense();
        }

        if (charEq.getShield() != null) {
            armorDef += charEq.getShield().increaseDefense();
        }

    }

    public void changeSwd1(Sword newSwd1) {
        if ((currJob == 0) && (charEq.getSwd2() != null)) {
            System.out.println(name + " can only hold one sword.");
        }
        else if ((currJob == 1) && (charEq.getSwd2() != null) && (charEq.getShield() != null)) {
            System.out.println(name + "'s hands are already full. Un-equip a sword or shield.");
        }
        else if (currJob > 1) {
            System.out.println(name + " cannot hold a sword.");
        }
        else {
            charEq.setSwd1(newSwd1);
            System.out.println(name + " is now holding a " + newSwd1.getName() + ".");
            evalEquip();
        }
    }

    public void changeSwd2(Sword newSwd2) {
        if ((currJob == 0) && (charEq.getSwd1() != null)) {
            System.out.println(name + " cannot hold another sword.");
        }
        else if ((currJob == 1) && (charEq.getSwd1() != null) && (charEq.getShield() != null)) {
            System.out.println(name + "'s hands are already full. Un-equip a sword or shield.");
        }
        else if (currJob > 1) {
            System.out.println(name + " cannot hold a sword.");
        }
        else {
            charEq.setSwd2(newSwd2);
            System.out.println(name + " is now holding a " + newSwd2.getName() + ".");
            evalEquip();
        }
    }

    public void changeStf1(Staff newStf1) {
        if (currJob < 4) {
            System.out.println(name + " cannot hold a staff.");
        }
        else if ((charEq.getStf2() != null) && (charEq.getShield() != null)) {
            System.out.println(name + "'s hands are already full. Un-equip a staff or shield.");
        }
        else {
            charEq.setStf1(newStf1);
            System.out.println(name + " is now holding a " + newStf1.getName() + ".");
            evalEquip();
        }
    }

    public void changeStf2(Staff newStf2) {
        if (currJob < 4) {
            System.out.println(name + " cannot hold a staff.");
        }
        else if ((charEq.getStf1() != null) && (charEq.getShield() != null)) {
            System.out.println(name + "'s hands are already full. Un-equip a staff or shield.");
        }
        else {
            charEq.setStf2(newStf2);
            System.out.println(name + " is now holding a " + newStf2.getName() + ".");
        }
    }

    public void changeBow(Bow newBow) {
        if (currJob != 3) {
            System.out.println(name + " cannot hold a bow.");
        }
        else {
            charEq.setBow(newBow);
            System.out.println(name + " is now holding a " + newBow.getName() + ".");
        }
    }

    public void changeThrow(Throwable newTrw) {
        if (currJob != 2) {
            System.out.println(name + " cannot hold a throwable object.");
        }
        else {
            charEq.setTrw(newTrw);
            System.out.println(name + " is now holding a " + newTrw.getName() + ".");
        }
    }

    public void changeHead(Head newHead) {
        charEq.setHead(newHead);
        System.out.println(name + " is now wearing a " + newHead.getName() + ".");
    }

    public void changeBody(Body newBody) {
        charEq.setBody(newBody);
        System.out.println(name + " is now wearing a " + newBody.getName() + ".");
    }

    public void changeLegs(Legs newLegs) {
        charEq.setLegs(newLegs);
        System.out.println(name + " is now wearing " + newLegs.getName() + ".");
    }

    public void changeFeet(Feet newFeet) {
        charEq.setFeet(newFeet);
        System.out.println(name + " is now wearing " + newFeet.getName() + ".");
    }

    public void changeShield(Shield newShield) {
        if ((currJob == 0) || (currJob == 2) || (currJob == 3)) {
            System.out.println(name + " cannot hold a shield.");
        }
        else if ((charEq.getSwd1() != null) && (charEq.getSwd2() != null)) {
            System.out.println(name + "'s hands are already full. Un-equip a sword.");
        }
        else if ((charEq.getStf1() != null) && (charEq.getStf2() != null)) {
            System.out.println(name + "'s hands are already full. Un-equip a staff.");
        }
        else {
            charEq.setShield(newShield);
            System.out.println(name + " is now holding a " + newShield.getName() + ".");
        }
    }

    public void changeJob(int newJob, ArrayList<SpellAoH> mLA, ArrayList<SpellAoH> mLH, ArrayList<SpellBuff> mLI,
                          ArrayList<SpellBuff> mLD, ArrayList<SpellSE> mLN, ArrayList<SpellSE> mLC) {

        currJob = newJob;
        charEq = new CharacterEquipment();

        switch (newJob) {
            case 0:
                level = knight1H.expToLevel(exp);
                knight1H.setCurrLevel(level);
                maxHP = knight1H.knightCalc.setMaxHP();
                defStat = knight1H.knightCalc.setMaxDef();
                atkStat = knight1H.knightCalc.setMaxAtk();
                spdStat = knight1H.knightCalc.setMaxSpd();
                maxMP = knight1H.knightCalc.setMaxMP();
                break;
            case 1:
                level = knight2H.expToLevel(exp);
                knight2H.setCurrLevel(level);
                maxHP = knight2H.knightCalc.setMaxHP();
                defStat = knight2H.knightCalc.setMaxDef();
                atkStat = knight2H.knightCalc.setMaxAtk();
                spdStat = knight2H.knightCalc.setMaxSpd();
                maxMP = knight2H.knightCalc.setMaxMP();
                break;
            case 2:
                level = thrower.expToLevel(exp);
                thrower.setCurrLevel(level);
                maxHP = thrower.rangedCalc.setMaxHP();
                defStat = thrower.rangedCalc.setMaxDef();
                atkStat = thrower.rangedCalc.setMaxAtk();
                spdStat = thrower.rangedCalc.setMaxSpd();
                maxMP = thrower.rangedCalc.setMaxMP();
                break;
            case 3:
                level = archer.expToLevel(exp);
                archer.setCurrLevel(level);
                maxHP = archer.rangedCalc.setMaxHP();
                defStat = archer.rangedCalc.setMaxDef();
                atkStat = archer.rangedCalc.setMaxAtk();
                spdStat = archer.rangedCalc.setMaxSpd();
                maxMP = archer.rangedCalc.setMaxMP();
                break;
            case 4:
                level = whiteMage.expToLevel(exp);
                whiteMage.setCurrLevel(level);
                whiteMage.fillBook(mLA, mLH, mLI, mLD, mLN, mLC, level);
                maxHP = whiteMage.mageCalc.setMaxHP();
                defStat = whiteMage.mageCalc.setMaxDef();
                atkStat = whiteMage.mageCalc.setMaxAtk();
                spdStat = whiteMage.mageCalc.setMaxSpd();
                maxMP = whiteMage.mageCalc.setMaxMP();
                break;
            case 5:
                level = blackMage.expToLevel(exp);
                blackMage.setCurrLevel(level);
                blackMage.fillBook(mLA, mLH, mLI, mLD, mLN, mLC, level);
                maxHP = blackMage.mageCalc.setMaxHP();
                defStat = blackMage.mageCalc.setMaxDef();
                atkStat = blackMage.mageCalc.setMaxAtk();
                spdStat = blackMage.mageCalc.setMaxSpd();
                maxMP = blackMage.mageCalc.setMaxMP();
                break;
            case 6:
                level = redMage.expToLevel(exp);
                redMage.setCurrLevel(level);
                redMage.fillBook(mLA, mLH, mLI, mLD, mLN, mLC, level);
                maxHP = redMage.mageCalc.setMaxHP();
                defStat = redMage.mageCalc.setMaxDef();
                atkStat = redMage.mageCalc.setMaxAtk();
                spdStat = redMage.mageCalc.setMaxSpd();
                maxMP = redMage.mageCalc.setMaxMP();
                break;
        }

    }

    public void resetStatus() {
        stunned = false;
        burned = false;
        poisoned = false;
        asleep = false;
        presentSE = false;
        presentBuff = false;
        durSE = 0;
        buffCD = 0;
        currDef = defStat;
        currAtk = atkStat;
        currSpd = spdStat;
    }

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

    /*
    @Override
    protected void setStatus(StatusEffect newStatus) {

    }
    */

    /*
    @Override
    protected StatusEffect getStatus() {
        return null;
    }
    */
}