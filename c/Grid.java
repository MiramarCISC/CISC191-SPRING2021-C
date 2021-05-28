package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Random;

public final class Grid {
    private ArrayList<GridSquare> grid = new ArrayList<GridSquare>();

    public Grid() {
        Random rand = new Random();

        byte treasureSquares = 0;
        // byte shopSquares = 0;
        byte innSquares = 0;
        byte bossSquares = 0;

        byte treasureTimer = 30;
        // byte shopTimer = 30;
        byte innTimer = 30;
        byte bossTimer = 30;

        for (byte i = 0; i < 100; i++) {
            double chance;

            chance = Math.ceil(rand.nextFloat() * 100);

            if (chance <= 10 && treasureTimer >= 25 && treasureSquares < 4) {
                grid.add(new TreasureSquare());
                treasureSquares++;
                treasureTimer = 0;
            }

            /*
            else if(chance <= 20 && shopTimer >= 25 && shopSquares < 4) {
                grid.add(new ShopSquare());
                shopSquares++;
                shopTimer = 0;
            }
            */

            else if (chance <= 30 && innTimer >= 25 && innSquares < 4) {
                grid.add(new InnSquare());
                innSquares++;
                innTimer = 0;
            }

            else if (chance <= 40 && bossTimer >= 35 && bossSquares < 3) {
                grid.add(new BossSquare());
                bossSquares++;
                bossTimer = 0;
            }

            else {
                grid.add(new NormalSquare());
            }

            treasureTimer++;
            // shopTimer++;
            innTimer++;
            bossTimer++;
        }
    }

    public Grid(int maxEnemies, int maxDifficulty, ArrayList<GridSquare> grid) {
        this.grid = grid;
    }

    public ArrayList<GridSquare> getGrid() {
        return grid;
    }

    public void displayGrid() {
        for(byte i = 1; i <= grid.size(); i++) {
            System.out.print(i + grid.get(i - 1).getAbbreviation() + " | ");

            if(i % 10 == 0) {
                System.out.println();
            }
        }
    }

    public GridSquare getGridSquare(byte index) {
        return grid.get(index);
    }

    public void setGridSquare(byte index, GridSquare square) {
        grid.set(index, square);
    }
}