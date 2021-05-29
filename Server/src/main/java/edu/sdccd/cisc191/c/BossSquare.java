package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Random;

public class BossSquare extends GridSquare {
    public BossSquare() {
        abbreviation = 'B';
    }

    @Override
    public Inventory action(ArrayList<PartyMember> party, Inventory inv) {
        System.out.println("You found a boss! It's tougher than most enemies!");

        Random rand = new Random();
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        double p = party.size();
        int sum = 0;
        for (PartyMember member : party) {
            sum += member.getLevel();
        }
        int avg = (int)(Math.round(sum/p));
        if (avg == 0) {
            avg = 1;
        }
        avg += 3;

        boolean mag = rand.nextBoolean();
        Enemy temp = new Enemy("Boss");
        Enemy.EnemyBuilder builder = temp.new EnemyBuilder(avg, mag);
        enemies.add(temp);

        Fight bossF = new Fight(party, enemies, inv);
        inv = bossF.battle();

        return inv;
    }
}