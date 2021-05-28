package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public abstract class GridSquare {
    protected char abbreviation;

    public GridSquare() {

    }

    public abstract Inventory action(ArrayList<PartyMember> party, Inventory inv);

    public String getAbbreviation() {
        return Character.toString(abbreviation);
    }
}