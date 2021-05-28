package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Random;

public final class Grid {
    private ArrayList<GridSquare> grid = new ArrayList<GridSquare>();

    public Grid() {
        Random rand = new Random();

        int treasureSquares = 0;
        // int shopSquares = 0;
        int innSquares = 0;
        int bossSquares = 0;

        int treasureTimer = 30;
        // int shopTimer = 30;
        int innTimer = 30;
        int bossTimer = 30;

        for (int i = 0; i < 100; i++) {
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

    public Grid(ArrayList<GridSquare> grid) {
        this.grid = grid;
    }

    public ArrayList<GridSquare> getGrid() {
        return grid;
    }

    public void displayGrid() {
        for(int i = 1; i <= grid.size(); i++) {
            System.out.print(i + grid.get(i - 1).getAbbreviation() + " | ");

            if(i % 10 == 0) {
                System.out.println();
            }
        }
    }

    public GridSquare getGridSquare(int index) {
        return grid.get(index);
    }

    public void setGridSquare(int index, GridSquare square) {
        grid.set(index, square);
    }
}