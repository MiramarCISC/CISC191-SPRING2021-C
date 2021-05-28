package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public abstract class Spell implements Comparable<Spell> {

    @CsvBindByName(column = "name")
    protected String name;

    @CsvBindByName(column = "minLev")
    protected int minLev;

    @CsvBindByName(column = "cost")
    protected int cost;

    @CsvBindByName(column = "color")
    protected boolean color;

    public Spell() {

    }

    protected abstract void castOnE(Enemy enemy);

    protected abstract void castOnPM(PartyMember member);

    public Spell(String call, int mL, int cst, boolean BoW) {
        name = call;
        minLev = mL;
        cost = cst;
        color = BoW;
    }

    public void setName(String call) { name = call; }

    public void setMinLev(int mL) { minLev = mL; }

    public void setCost(int cst) { cost = cst; }

    public void setColor(boolean BoW) { color = BoW; }

    public String getName() { return name; }

    public int getMinLev() { return minLev; }

    public int getCost() { return cost; }

    public boolean getColor() { return color; }

    public void printInfo() {
        if (color) {
            System.out.print("(W) ");
        }
        else {
            System.out.print("(B) ");
        }
        System.out.println(name + " costs " + cost + " MP");
    }

    @Override
    public int compareTo(Spell otherS) {
        int compVal;
        // Top-level sorts by White & Black
        compVal = Boolean.compare(this.color, otherS.color);
        // Next level down sorts by minimum level
        if (compVal == 0) {
            compVal = Integer.compare(this.minLev, otherS.minLev);
        }
        // Spells of same type and level bracket are further sorted alphabetically
        if (compVal == 0) {
            compVal = this.name.compareTo(otherS.name);
        }

        return compVal;
    }

}