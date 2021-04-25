package edu.sdccd.cisc191.c;

public abstract class Job extends Being {

    protected boolean magical;
    protected boolean ranged;
    // protected boolean currJob;

    public void setMagical(boolean mag) { magical = mag; }

    public void setRanged(boolean ran) { ranged = ran; }

    // public void setCurrJob(boolean curr) { currJob = curr; }

    public boolean getMagical() {
        return magical;
    }

    public boolean getRanged() {
        return ranged;
    }

    public void getAllowedWeapons() { }

    public void getAllowedArmor() { }

    // public boolean getCurrJob() { return currJob; }

    public abstract int expToLevel(int exp);

}