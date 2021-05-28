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

            double p = party.size();
            int sum = 0;
            for (PartyMember member : party) {
                sum += member.getLevel();
            }
            int avg = (int)(Math.round(sum/p));
            if (avg == 0) {
                avg = 1;
            }

            ArrayList<Enemy> enemies = new ArrayList<Enemy>();
            int foes = rand.nextInt(4) + 1;
            for (int i = 0; i < foes; ++i) {
                String eName = "";
                switch (i) {
                    case 0:
                        eName = "Enemy 1";
                        break;
                    case 1:
                        eName = "Enemy 2";
                        break;
                    case 2:
                        eName = "Enemy 3";
                        break;
                    case 3:
                        eName = "Enemy 4";
                        break;
                }
                boolean mag = rand.nextBoolean();
                Enemy temp = new Enemy(eName);
                Enemy.EnemyBuilder builder = temp.new EnemyBuilder(avg, mag);
                enemies.add(temp);
            }

            Fight normalF = new Fight(party, enemies, inv);
            inv = normalF.battle();

        }
        else {
            System.out.println("No enemies appeared. Keep going!");
            inv.setGo(true);
        }

        return inv;
    }
}