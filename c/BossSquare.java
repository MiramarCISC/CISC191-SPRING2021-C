package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public class BossSquare extends GridSquare {
    public BossSquare() {
        abbreviation = 'B';
    }

    @Override
    public Inventory action(ArrayList<PartyMember> party, Inventory inv) {
        boolean alive = true;

        System.out.println("You found the boss! Defeat it to beat the game!");
        alive = false;

        inv.setGo(alive);
        return inv;
    }
}