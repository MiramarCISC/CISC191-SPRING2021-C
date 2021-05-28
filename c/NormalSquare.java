package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Random;

public class NormalSquare extends GridSquare {
    public NormalSquare() {
        abbreviation = 'N';
    }

    @Override
    public Inventory action(ArrayList<PartyMember> party, Inventory inv) {
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        boolean alive = true;

        if(chance >= 85) {
            System.out.println("An enemy approaches you! Fight!");
            alive = false;
        }

        else {
            System.out.println("No enemies appeared. Keep going!");
            alive = true;
        }

        inv.setGo(alive);
        return inv;
    }
}