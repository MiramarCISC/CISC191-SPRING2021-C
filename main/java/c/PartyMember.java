package c;

import java.util.ArrayList;

public class PartyMember extends Battler {

    private Knight knight1H;
    private Knight knight2H;

    private Ranged thrower;
    private Ranged archer;

    private Mage whiteMage;
    private Mage blackMage;
    private Mage redMage;

    // Can only be 0, 1, 2, 3, 4, 5, or 6
    private int currJob;

    private CharacterEquipment charEq;

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


    public void changeEq(CharacterEquipment newEq) {
        if (currJob < 1) {

        } else if (currJob < 2) {

        } else if (currJob < 4) {

        } else {

        }
    }

    public void setCurrJob(int c) {
        currJob = c;
    }

    public int getCurrJob() {
        return currJob;
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
