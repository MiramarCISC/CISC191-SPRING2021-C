package c;

public abstract class Battler {

    protected String name;
    protected int level;
    protected int exp;
    protected int maxHP;
    protected int currHP;
    protected int defStat;
    protected int currDef;
    protected int atkStat;
    protected int currAtk;
    protected int spdStat;
    protected int currSpd;
    protected boolean stunned;
    protected boolean burned;
    protected boolean poisoned;
    protected boolean asleep;
    protected boolean dead;
    protected StatusEffect status;
    protected StatCalc calculator;

    public void setName(String visName) { name = visName; }

    public void setLevel(int lvl) { level = lvl; }

    public void setExp(int ex) { exp = ex; }

    public void setMaxHP(int mHP) { maxHP = mHP; }

    public void setCurrHP(int cHP) { currHP = cHP; }

    public void setDefStat(int dStat) { defStat = dStat; }

    public void setCurrDef(int cDef) { currDef = cDef; }

    public void setAtkStat(int aStat) { atkStat = aStat; }

    public void setCurrAtk(int cAtk) { currAtk = cAtk; }

    public void setSpdStat(int sStat) { spdStat = sStat; }

    public void setCurrSpd(int cSpd) { currSpd = cSpd; }

    public void setStunned(boolean stun) { stunned = stun; }

    public void setBurned(boolean burn) { burned = burn; }

    public void setPoisoned(boolean poison) { poisoned = poison; }

    public void setAsleep(boolean sleep) { asleep = sleep; }

    public void setDead(boolean death) { dead = death; }

    public String getName() { return name; }

    public int getLevel() { return level; }

    public int getExp() { return exp; }

    public int getMaxHP() { return maxHP; }

    public int getCurrHP() { return currHP; }

    public int getDefStat() { return defStat; }

    public int getCurrDef() { return currDef; }

    public int getAtkStat() { return atkStat; }

    public int getCurrAtk() { return currAtk; }

    public int getSpdStat() { return spdStat; }

    public int getCurrSpd() { return currSpd; }

    public boolean getStunned() { return stunned; }

    public boolean getBurned() { return burned; }

    public boolean getPoisoned() { return poisoned; }

    public boolean getAsleep() { return asleep; }

    public boolean getDead() { return dead; }


    protected abstract void moveHappens();
    protected abstract void setStatus(StatusEffect newStatus);
    protected abstract StatusEffect getStatus();
}