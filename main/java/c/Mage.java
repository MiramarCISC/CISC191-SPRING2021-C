package c;

import java.util.ArrayList;
import java.util.Collections;

public class Mage extends Job implements Spellcaster {

    private boolean wMage;
    private boolean bMage;
    private int baseMP;

    public Mage(boolean white, boolean black) {
        magical = true;
        ranged = false;
        wMage = white;
        bMage = black;
    }

    public void setWMage(boolean white) { wMage = white; }

    public void setBMage(boolean black) { bMage = black; }

    public boolean getWMage() { return wMage; }

    public boolean getBMage() { return bMage; }

    @Override
    public int expToLevel(int exp) {
        return 0;
    }

    @Override
    public boolean checkPresence(Spell lookSpell, ArrayList<Spell> mySpells) {
        boolean there = false;

        for (Spell spell : mySpells) {
            if (lookSpell.getName().equals(spell.getName())) {
                there = true;
                break;
            }
        }

        return there;
    }

    @Override
    public void setLegalSpells(String name, int level, ArrayList<Spell> masterList) {

        for (Spell newSpell : masterList) {
            if(!(checkPresence(newSpell, spellList))) {
                if ((newSpell.getColor() && wMage) || (!(newSpell.getColor()) && bMage)) {
                    if (newSpell.getMinLev() <= level) {
                        spellList.add(newSpell);
                        System.out.println(name + "learned " + newSpell.getName() + "!");
                    }
                }
            }
        }

    }

    @Override
    public ArrayList<Spell> getLegalSpells() {
        return spellList;
    }

    public void printSpells() {
        Collections.sort(spellList);

        for(Spell spell : spellList) {
            spell.printInfo();
        }
    }

    @Override
    public void splCast(Spell spl) {

    }
}
