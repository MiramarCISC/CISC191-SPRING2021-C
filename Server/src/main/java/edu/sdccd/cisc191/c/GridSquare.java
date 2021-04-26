package edu.sdccd.cisc191.c;

import java.util.Random;

public class GridSquare {

    private int maxEnemies;    // 1 to 4
    private int maxDifficulty; // 1 to 100
    private int probability;   // Minimum of 1, which is guaranteed encounter
    private boolean treasure;
    private boolean special;

    public GridSquare() {
    }

    public GridSquare(int maxE, int maxD, int prob, boolean treas, boolean sp) {
        maxEnemies = maxE;
        maxDifficulty = maxD;
        probability = prob;
        treasure = treas;
        special = sp;
    }

    public void randEncounter() {
        Random rand = new Random();
        int determine = rand.nextInt(probability);
        if (determine == 0) {
            // startBattle(maxEnemies, maxDifficulty);
        }
        else {
            System.out.println("No enemies appeared.");
        }
    }

    public void openChest(Inventory inv) {
        if (treasure) {
            Random rand  = new Random();
            int type = rand.nextInt(3);
            switch (type) {
                case 0:
                    inv.obtainedSmallChest();
                    break;
                case 1:
                    inv.obtainedMediumChest();
                    break;
                case 2:
                    inv.obtainedBigChest();
                    break;
            }
        }
        else {
            System.out.println("There is no treasure here.");
        }
    }

    public void bossEncounter() {
        if (special) {
            System.out.println("You found the boss! Defeat it to beat the game!");
            // startBoss();
        }
        else {
            // Do nothing
        }
    }

    public void setMinEnemies(int maxE) {
        maxEnemies = maxE;
    }

    public void setMinDifficulty(int maxD) {
        maxDifficulty = maxD;
    }

    public void setMinChance(int prob) {
        probability = prob;
    }

    public void setTreasure(boolean treas) {
        treasure = treas;
    }

    public void setSpecial(boolean sp) {
        special = sp;
    }

    public int getMaxEnemies() {
        return maxEnemies;
    }

    public int getMaxDifficulty() {
        return maxDifficulty;
    }

    public int getProbability() {
        return probability;
    }

    public boolean getTreasure() {
        return treasure;
    }

    public boolean getSpecial() {
        return special;
    }

}