package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public class InnSquare extends GridSquare {
    public InnSquare() {
        abbreviation = 'I';
    }

    @Override
    public Inventory action(ArrayList<PartyMember> party, Inventory inv) {
        System.out.println("You've arrived at an inn!");

        for (PartyMember member : party) {
            member.setCurrHP(member.getMaxHP());
            member.setCurrMP(member.getMaxMP());
            member.setDead(false);
        }

        inv.setGo(true);
        return inv;
    }
}