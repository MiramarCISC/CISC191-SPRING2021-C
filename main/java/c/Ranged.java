package c;

public class Ranged extends Job {

    private boolean trwORarc;

    public Ranged(boolean type) {
        magical = false;
        ranged = true;
        trwORarc = type;
    }

    public void setType(boolean type) { trwORarc = type; }

    public boolean getType() { return trwORarc; }

    @Override
    public int expToLevel(int exp) {
        return 0;
    }

}
