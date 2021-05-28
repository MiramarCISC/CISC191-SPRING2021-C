package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;

public class PartyMember extends Battler {

    private Knight knight1H;
    private Knight knight2H;

    private Ranged thrower;
    private Ranged archer;

    private Mage whiteMage;
    private Mage blackMage;
    private Mage redMage;

    public Knight getKnight1H() {
        return knight1H;
    }

    public void setKnight1H(Knight knight1H) {
        this.knight1H = knight1H;
    }

    public Knight getKnight2H() {
        return knight2H;
    }

    public void setKnight2H(Knight knight2H) {
        this.knight2H = knight2H;
    }

    public Ranged getThrower() {
        return thrower;
    }

    public void setThrower(Ranged thrower) {
        this.thrower = thrower;
    }

    public Ranged getArcher() {
        return archer;
    }

    public void setArcher(Ranged archer) {
        this.archer = archer;
    }

    public Mage getWhiteMage() {
        return whiteMage;
    }

    public void setWhiteMage(Mage whiteMage) {
        this.whiteMage = whiteMage;
    }

    public Mage getBlackMage() {
        return blackMage;
    }

    public void setBlackMage(Mage blackMage) {
        this.blackMage = blackMage;
    }

    public Mage getRedMage() {
        return redMage;
    }

    public void setRedMage(Mage redMage) {
        this.redMage = redMage;
    }

    // Can only be 0 (1H-Knight), 1 (2H-Knight), 2 (Throw), 3 (Archer), 4 (White Mage), 5 (Black Mage), or 6 (Red Mage)
    @CsvBindByName(column = "currJob")
    private int currJob;

    // Stores equipment objects and
    private CharacterEquipment charEq = new CharacterEquipment();
    private int weaponAtk;
    private int armorDef;

    @CsvBindByName(column = "maxXP")
    private int maxXP;

    public int getMaxXP() {
        return maxXP;
    }

    public void setMaxXP(int maxXP) {
        this.maxXP = maxXP;
    }

    public PartyMember() {

    }

    public PartyMember(String pmName, int lvl, int ex, int cHP, boolean d, int cJob, int mXP) {
        name = pmName;
        level = lvl;
        exp = ex;
        currHP = cHP;
        currJob = cJob;
        maxXP = mXP;

        stunned = false;
        burned = false;
        poisoned = false;
        asleep = false;
        presentSE = false;
        presentBuff = false;
        durSE = 0;
        buffCD = 0;

        dead = d;

        knight1H = new Knight("One-Handed", 10, 8, 7, 20, false);
        knight2H = new Knight("Two-Handed", 8, 10, 5, 20, true);
        blackMage = new Mage("Black Mage", 4, 6, 10, 15, false, true, 20);
        whiteMage = new Mage("White Mage", 6, 4, 10, 15, true, false, 20);
        redMage = new Mage("Red Mage", 5, 5, 10, 15, true, true, 10);
        thrower = new Ranged("Thrower", 12, 7, 12, 13, false);
        archer = new Ranged("Archer", 12, 9, 10, 13, true);
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

    public void calculateStats(ArrayList<SpellAoH> mLA, ArrayList<SpellAoH> mLH, ArrayList<SpellBuff> mLI,
                               ArrayList<SpellBuff> mLD, ArrayList<SpellSE> mLN, ArrayList<SpellSE> mLC) {
        switch (currJob) {
            case 0:
                knight1H.setCurrLevel(level);
                maxHP = knight1H.knightCalc.setMaxHP();
                defStat = knight1H.knightCalc.setMaxDef();
                atkStat = knight1H.knightCalc.setMaxAtk();
                spdStat = knight1H.knightCalc.setMaxSpd();
                maxMP = knight1H.knightCalc.setMaxMP();
                break;
            case 1:
                knight2H.setCurrLevel(level);
                maxHP = knight2H.knightCalc.setMaxHP();
                defStat = knight2H.knightCalc.setMaxDef();
                atkStat = knight2H.knightCalc.setMaxAtk();
                spdStat = knight2H.knightCalc.setMaxSpd();
                maxMP = knight2H.knightCalc.setMaxMP();
                break;
            case 2:
                thrower.setCurrLevel(level);
                maxHP = thrower.rangedCalc.setMaxHP();
                defStat = thrower.rangedCalc.setMaxDef();
                atkStat = thrower.rangedCalc.setMaxAtk();
                spdStat = thrower.rangedCalc.setMaxSpd();
                maxMP = thrower.rangedCalc.setMaxMP();
                break;
            case 3:
                archer.setCurrLevel(level);
                maxHP = archer.rangedCalc.setMaxHP();
                defStat = archer.rangedCalc.setMaxDef();
                atkStat = archer.rangedCalc.setMaxAtk();
                spdStat = archer.rangedCalc.setMaxSpd();
                maxMP = archer.rangedCalc.setMaxMP();
                break;
            case 4:
                whiteMage.setCurrLevel(level);
                whiteMage.fillBook(mLA, mLH, mLI, mLD, mLN, mLC, level);
                maxHP = whiteMage.mageCalc.setMaxHP();
                defStat = whiteMage.mageCalc.setMaxDef();
                atkStat = whiteMage.mageCalc.setMaxAtk();
                spdStat = whiteMage.mageCalc.setMaxSpd();
                maxMP = whiteMage.mageCalc.setMaxMP();
                break;
            case 5:
                blackMage.setCurrLevel(level);
                blackMage.fillBook(mLA, mLH, mLI, mLD, mLN, mLC, level);
                maxHP = blackMage.mageCalc.setMaxHP();
                defStat = blackMage.mageCalc.setMaxDef();
                atkStat = blackMage.mageCalc.setMaxAtk();
                spdStat = blackMage.mageCalc.setMaxSpd();
                maxMP = blackMage.mageCalc.setMaxMP();
                break;
            case 6:
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

    public void levelUp(ArrayList<SpellAoH> mLA, ArrayList<SpellAoH> mLH, ArrayList<SpellBuff> mLI,
                        ArrayList<SpellBuff> mLD, ArrayList<SpellSE> mLN, ArrayList<SpellSE> mLC) {
        exp -= maxXP;
        ++level;

        if (level < 20) {
            maxXP = (int) (Math.round(maxXP * 1.25));
        }
        else {
            maxXP = 2147483647;
        }

        calculateStats(mLA, mLH, mLI, mLD, mLN, mLC);
    }

    public void changeJob(int newJob, ArrayList<SpellAoH> mLA, ArrayList<SpellAoH> mLH, ArrayList<SpellBuff> mLI,
                          ArrayList<SpellBuff> mLD, ArrayList<SpellSE> mLN, ArrayList<SpellSE> mLC) {

        currJob = newJob;
        charEq = new CharacterEquipment();

        calculateStats(mLA, mLH, mLI, mLD, mLN, mLC);

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

    public String getCurrJobName() {
        String jobName = "";

        switch (currJob) {
            case 0:
                jobName = "1H-Knight";
                break;
            case 1:
                jobName = "2H-Knight";
                break;
            case 2:
                jobName = "Throw";
                break;
            case 3:
                jobName = "Archer";
                break;
            case 4:
                jobName = "White Mage";
                break;
            case 5:
                jobName = "Black Mage";
                break;
            case 6:
                jobName = "Red Mage";
                break;
            default:
                jobName = "NO CURR JOB";
                break;
        }
        return jobName;
    }

    public int getArmorDef() {
        return armorDef;
    }

    public CharacterEquipment getCurrEquip() {
        return charEq;
    }

    public int getWeaponAtk() {
        return weaponAtk;
    }

    @Override
    protected void moveHappens() {

    }

    @Override
    public String toString() {
        return String.format(
                "PartyMember[name=%s, level=%d, exp=%d, currHP=%d, dead=%b, currJob=%d, maxXP=%d]",
                name, level, exp, currHP, dead, currJob, maxXP);
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