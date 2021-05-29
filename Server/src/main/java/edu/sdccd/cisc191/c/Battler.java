package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;

public abstract class Battler {

    public Battler() {

    }

    @CsvBindByName(column = "name")
    protected String name;

    @CsvBindByName(column = "level")
    protected int level;

    @CsvBindByName(column = "exp")
    protected int exp;

    protected int maxMP;

    @CsvBindByName(column = "currMP")
    protected int currMP;

    protected int maxHP;

    @CsvBindByName(column = "currHP")
    protected int currHP;

    protected int defStat;
    protected int currDef;
    protected int atkStat;
    protected int currAtk;
    protected int spdStat;
    protected int currSpd;
    protected int durSE;
    protected int buffCD;
    protected boolean stunned;
    protected boolean burned;
    protected boolean poisoned;
    protected boolean asleep;

    @CsvBindByName(column = "dead")
    protected boolean dead;

    protected boolean presentSE;
    protected boolean presentBuff;
    // protected StatusEffect status;
    // protected StatCalc calculator;

    public void setName(String visName) { name = visName; }

    public void setLevel(int lvl) { level = lvl; }

    public void setExp(int ex) { exp = ex; }

    public void setMaxHP(int mHP) { maxHP = mHP; }

    public void setCurrHP(int cHP) { currHP = cHP; }

    public void setMaxMP(int mMP) { maxMP = mMP; }

    public void setCurrMP(int cMP) { currMP = cMP; }

    public void setDefStat(int dStat) { defStat = dStat; }

    public void setCurrDef(int cDef) { currDef = cDef; }

    public void setAtkStat(int aStat) { atkStat = aStat; }

    public void setCurrAtk(int cAtk) { currAtk = cAtk; }

    public void setSpdStat(int sStat) { spdStat = sStat; }

    public void setCurrSpd(int cSpd) { currSpd = cSpd; }

    public void setDurSE(int dSE) { durSE = dSE; }

    public void setBuffCD(int bCD) { buffCD = bCD; }

    public void setStunned(boolean stun) { stunned = stun; }

    public void setBurned(boolean burn) { burned = burn; }

    public void setPoisoned(boolean poison) { poisoned = poison; }

    public void setAsleep(boolean sleep) { asleep = sleep; }

    public void setDead(boolean death) { dead = death; }

    public void setPresentSE(boolean statusEf) { presentSE = statusEf; }

    public void setPresentBuff(boolean presBuff) { presentBuff = presBuff; }

    public String getName() { return name; }

    public int getLevel() { return level; }

    public int getExp() { return exp; }

    public int getMaxHP() { return maxHP; }

    public int getCurrHP() { return currHP; }

    public int getMaxMP() { return maxMP; }

    public int getCurrMP() { return currMP; }

    public int getDefStat() { return defStat; }

    public int getCurrDef() { return currDef; }

    public int getAtkStat() { return atkStat; }

    public int getCurrAtk() { return currAtk; }

    public int getSpdStat() { return spdStat; }

    public int getCurrSpd() { return currSpd; }

    public int getDurSE() { return durSE; }

    public int getBuffCD() { return buffCD; }

    public boolean getStunned() { return stunned; }

    public boolean getBurned() { return burned; }

    public boolean getPoisoned() { return poisoned; }

    public boolean getAsleep() { return asleep; }

    public boolean getDead() { return dead; }

    public boolean getPresentSE() { return presentSE; }

    public boolean getPresentBuff() { return presentBuff; }

    // protected abstract void moveHappens();
    // protected abstract StatusEffect getStatus();
    // protected abstract void setStatus(StatusEffect newStatus);
}