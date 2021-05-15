package edu.sdccd.cisc191.c;

public class Ranged extends Job {

    private boolean trwORarc;

    public Ranged(String type, int bDef, int bAtk, int bSpd, int bHP, boolean kind) {
        super(type, bDef, bAtk, bSpd, bHP);
        magical = false;
        ranged = true;
        trwORarc = kind;
    }

    public void setType(boolean type) { trwORarc = type; }

    public boolean getType() { return trwORarc; }

    /**
     * The anonymous version of the StatCalc interface gives this Job subclass specific methods that modify its stats
     * differently from the other Job subclasses, and is utilized in PartyMember when leveling up or switching Jobs.
     */
    StatCalc rangedCalc = new StatCalc() {

        @Override
        public int setMaxHP() {
            double WIP = baseHP*1.0;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.3; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxDef() {
            double WIP = baseDef;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.1; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxAtk() {
            double WIP = baseAtk;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.1; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxSpd() {
            double WIP = baseSpd;
            for (int i = 0; i < currLevel; ++i) { WIP = WIP*1.3; }
            return (int)(Math.round(WIP));
        }

        @Override
        public int setMaxMP() {
            return 0;
        }
    };

    @Override
    public int expToLevel(int exp) {
        return 0;
    }

}