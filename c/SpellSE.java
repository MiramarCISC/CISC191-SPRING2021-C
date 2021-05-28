package edu.sdccd.cisc191.c;

import com.opencsv.bean.CsvBindByName;

public class SpellSE extends Spell{

    @CsvBindByName(column = "duration")
    private int duration;
    // Number of turns effect will last

    @CsvBindByName(column = "effect")
    private int effect;
    // 0 for stunned, 1 for burned, 2 for poisoned, 3 for asleep

    // If BoW is true (white), condition is cured. If BoW is false (black), condition is inflicted

    public SpellSE() {

    }

    public SpellSE(String call, int mL, int cst, boolean BoW, int dur, int ef) {
        super(call, mL, cst, BoW);
        duration = dur;
        effect = ef;
    }

    public void setDuration(int dur) {
        duration = dur;
    }

    public void setEffect(int ef) {
        effect = ef;
    }

    public int getDuration() {
        return duration;
    }

    public int getEffect() {
        return effect;
    }

    @Override
    protected void castOnE(Enemy enemy) {

            if (color) {
                if (enemy.getPresentSE()) {
                    switch (effect) {
                        case 0:
                            if (enemy.getStunned()) {
                                enemy.setPresentSE(false);
                                enemy.setDurSE(0);
                                enemy.setStunned(false);
                            }
                            else {
                                System.out.println(enemy.getName() + " is not stunned!");
                            }
                            break;
                        case 1:
                            if (enemy.getStunned()) {
                                enemy.setPresentSE(false);
                                enemy.setDurSE(0);
                                enemy.setBurned(false);
                            }
                            else {
                                System.out.println(enemy.getName() + " is not burned!");
                            }
                            break;
                        case 2:
                            if (enemy.getStunned()) {
                                enemy.setPresentSE(false);
                                enemy.setDurSE(0);
                                enemy.setPoisoned(false);
                            }
                            else {
                                System.out.println(enemy.getName() + " is not poisoned!");
                            }
                            break;
                        case 3:
                            if (enemy.getStunned()) {
                                enemy.setPresentSE(false);
                                enemy.setDurSE(0);
                                enemy.setAsleep(false);
                            }
                            else {
                                System.out.println(enemy.getName() + " is not asleep!");
                            }
                            break;
                    }
                }
                else {
                    System.out.println("There is no status effect to cure.");
                }
            }
            else {
                if (!enemy.getPresentSE()) {
                    enemy.setPresentSE(true);
                    enemy.setDurSE(duration);

                    switch (effect) {
                        case 0:
                            enemy.setStunned(true);
                            break;
                        case 1:
                            enemy.setBurned(true);
                            break;
                        case 2:
                            enemy.setPoisoned(true);
                            break;
                        case 3:
                            enemy.setAsleep(true);
                            break;
                    }
                }
                else {
                    System.out.println("Cannot inflict another status effect.");
                }
            }

    }

    @Override
    protected void castOnPM(PartyMember member) {

        if (color) {
            if (member.getPresentSE()) {

                switch (effect) {
                    case 0:
                        if (member.getStunned()) {
                            member.setPresentSE(false);
                            member.setDurSE(0);
                            member.setStunned(false);
                        }
                        else {
                            System.out.println(member.getName() + " is not stunned!");
                        }
                        break;
                    case 1:
                        if (member.getStunned()) {
                            member.setPresentSE(false);
                            member.setDurSE(0);
                            member.setBurned(false);
                        }
                        else {
                            System.out.println(member.getName() + " is not burned!");
                        }
                        break;
                    case 2:
                        if (member.getStunned()) {
                            member.setPresentSE(false);
                            member.setDurSE(0);
                            member.setPoisoned(false);
                        }
                        else {
                            System.out.println(member.getName() + " is not poisoned!");
                        }
                        break;
                    case 3:
                        if (member.getStunned()) {
                            member.setPresentSE(false);
                            member.setDurSE(0);
                            member.setAsleep(false);
                        }
                        else {
                            System.out.println(member.getName() + " is not asleep!");
                        }
                        break;
                }

            }
            else {
                System.out.println("There is no status effect to cure.");
            }

        }
        else {
            if (!member.getPresentSE()) {
                member.setPresentSE(true);
                member.setDurSE(duration);

                switch (effect) {
                    case 0:
                        member.setStunned(true);
                        break;
                    case 1:
                        member.setBurned(true);
                        break;
                    case 2:
                        member.setPoisoned(true);
                        break;
                    case 3:
                        member.setAsleep(true);
                        break;
                }
            }
            else {
                System.out.println("Cannot inflict another status effect.");
            }
        }


    }

    @Override
    public String toString() {
        return String.format(
                "SpellAoH[name=%s, minLev=%d, cost=%d, color=%b, duration=%d, effect=%d]",
                name, minLev, cost, color, duration, effect);
    }

}