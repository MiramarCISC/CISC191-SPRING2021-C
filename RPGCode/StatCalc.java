package RPGCode;

public class StatCalc {

    private double dmgTaken;

    public double calcDmg(double defense, double dmgIn){
        dmgTaken = (1 - (defense/100))*dmgIn;
        return dmgTaken;
    }

    public Battler lvlUp(Battler stats, int level){

        stats.setLevel(stats.getLevel()+1);
        stats.setSpdStat((stats.getSpdStat()*1.01));
        stats.setMaxHP((stats.getMaxHP()*1.01));
        stats.setDefStat(stats.getDefStat()*1.01);
        stats.setAtkStat(stats.getAtkStat()*1.01);
        stats.setMaxMP(stats.getMaxMP()*1.01);

        stats.setCurrExp(0);
        stats.setMaxExp(stats.getMaxExp()*2);

        return stats;
    }

    // Stat modifiers
    public int increaseAtk(int atkVal, int atkInc){
        return atkVal + atkInc;
    }
    public int decreaseAtk(int atkVal, int atkDec){
        return atkVal - atkDec;
    }

    public int increaseDef(int defVal, int defInc){
        return defVal + defInc;
    }
    public int decreaseDef(int defVal, int defDec){
        return defVal - defDec;
    }

    public int restoreHP(int currHP, int hpUp){
        return currHP + hpUp;
    }



}
