package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public class SpellBuff extends Spell{

    @CsvBindByName(column = "duration")
    private int duration;
    // Amount of turns

    @CsvBindByName(column = "stat")
    private int stat;
    // 0 is defense, 1 is attack, 2 is speed

    @CsvBindByName(column = "bump")
    private int bump;
    // Amount of stat change

    // If BoW is true (white), the stat is bumped up. If BoW is false (black), the stat is bumped down

    public SpellBuff() {

    }

    public SpellBuff(String call, int mL, int cst, boolean BoW, int dur, int st, int bmp) {
        super(call, mL, cst, BoW);
        duration = dur;
        stat = st;
        bump = bmp;
    }

    public void setDuration(int dur) {
        duration = dur;
    }

    public void setStat(int st) {
        stat= st;
    }

    public void setBump(int bmp) {
        bump = bmp;
    }

    public int getDuration() {
        return duration;
    }

    public int getStat() {
        return stat;
    }

    public int getBump() {
        return bump;
    }

    @Override
    protected void castOnE(Enemy enemy) {

        if (color) {
            if (!enemy.getPresentBuff()) {
                enemy.setPresentBuff(true);
                enemy.setBuffCD(duration);

                switch (stat) {
                    case 0:
                        enemy.setCurrDef(enemy.getCurrDef() + bump);
                        break;
                    case 1:
                        enemy.setCurrAtk(enemy.getCurrAtk() + bump);
                        break;
                    case 2:
                        enemy.setCurrSpd(enemy.getCurrSpd() + bump);
                        break;
                }
            }
            else {
                System.out.println("The previous (de)buff is still on cooldown.");
            }
        }
        else {
            if (!enemy.getPresentBuff()) {
                enemy.setPresentBuff(true);
                enemy.setBuffCD(duration);

                switch (stat) {
                    case 0:
                        enemy.setCurrDef(enemy.getCurrDef() - bump);
                        break;
                    case 1:
                        enemy.setCurrAtk(enemy.getCurrAtk() - bump);
                        break;
                    case 2:
                        enemy.setCurrSpd(enemy.getCurrSpd() - bump);
                        break;
                }
            }
            else {
                System.out.println("The previous (de)buff is still on cooldown.");
            }
        }

    }

    @Override
    protected void castOnPM(PartyMember member) {

        if (color) {
            if (!member.getPresentBuff()) {
                member.setPresentBuff(true);
                member.setBuffCD(duration);

                switch (stat) {
                    case 0:
                        member.setCurrDef(member.getCurrDef() + bump);
                        break;
                    case 1:
                        member.setCurrAtk(member.getCurrAtk() + bump);
                        break;
                    case 2:
                        member.setCurrSpd(member.getCurrSpd() + bump);
                        break;
                }
            }
            else {
                System.out.println("The previous (de)buff is still on cooldown.");
            }
        }
        else {
            if (!member.getPresentBuff()) {
                member.setPresentBuff(true);
                member.setBuffCD(duration);

                switch (stat) {
                    case 0:
                        member.setCurrDef(member.getCurrDef() - bump);
                        break;
                    case 1:
                        member.setCurrAtk(member.getCurrAtk() - bump);
                        break;
                    case 2:
                        member.setCurrSpd(member.getCurrSpd() - bump);
                        break;
                }
            }
            else {
                System.out.println("The previous (de)buff is still on cooldown.");
            }
        }

    }

    @Override
    public String toString() {
        return String.format(
                "SpellAoH[name=%s, minLev=%d, cost=%d, color=%b, duration=%d, stat=%d, bump=%d]",
                name, minLev, cost, color, duration, stat, bump);
    }

}