package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Random;

public class TreasureSquare extends GridSquare {
    public TreasureSquare() {
        abbreviation = 'T';
    }

    @Override
    public Inventory action(ArrayList<PartyMember> party, Inventory inv) {
        Random rand  = new Random();
        int type = rand.nextInt(3);

        switch(type) {
            case 0:
                System.out.println("You found a small chest!");
                inv.obtainedSmallChest();
                break;

            case 1:
                System.out.println("You found a medium chest!");
                inv.obtainedMediumChest();
                break;

            case 2:
                System.out.println("You found a big chest!");
                inv.obtainedBigChest();
                break;
        }

        inv.setGo(true);
        return inv;
    }
}