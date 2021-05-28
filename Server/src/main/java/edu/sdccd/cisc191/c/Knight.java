package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public class Knight extends Job {

    @CsvBindByName(column = "handedness")
    private boolean handedness;

    public Knight() {

    }

    public Knight(String type, int bDef, int bAtk, int bSpd, int bHP, boolean hand) {
        super(type, bDef, bAtk, bSpd, bHP);
        magical = false;
        ranged = false;
        handedness = hand;
    }

    public void setHand(boolean hand) {
        handedness = hand;
    }

    public boolean getHand() {
        return handedness;
    }

    /**
     * The anonymous version of the StatCalc interface gives this Job subclass specific methods that modify its stats
     * differently from the other Job subclasses, and is utilized in PartyMember when leveling up or switching Jobs.
     */
    StatCalc knightCalc = new StatCalc() {

        @Override
        public int setMaxHP() {
            double WIP = baseHP*1.0;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.1; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxDef() {
            double WIP = baseDef;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.3; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxAtk() {
            double WIP = baseAtk;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.3; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxSpd() {
            double WIP = baseSpd;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.1; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxMP() {
            return 0;
        }
    };

    /*
    @Override
    public int expToLevel(int exp) {
        return 0;
    }
    */

    @Override
    public String toString() {
        return String.format(
                "Knight[typeName=%s, baseDef=%d, baseAtk=%d, baseSpd=%d, baseHP=%d, magical=%b, ranged=%b, handedness=%b]",
                typeName, baseDef, baseAtk, baseSpd, baseHP, magical, ranged, handedness);
    }

}