package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Random;

public class Fight {

    private ArrayList<PartyMember> party;
    private ArrayList<Enemy> opponents;

    private int fighters;
    private int[] turnOrder;
    private int[] speeds;

    private ArrayList<ArrayList<Integer>> allCommands;

    private boolean activeFight;
    private boolean partyDead;
    private boolean opponentsDead;

    public Fight(ArrayList<PartyMember> good, ArrayList<Enemy> bad) {

        party = good;
        opponents = bad;
        fighters = party.size() + opponents.size();

        turnOrder = new int[fighters];
        speeds = new int[fighters];

        for (int i = 0; i < fighters; ++i) {
            turnOrder[i] = i;
            if (i < 4) {
                speeds[i] = party.get(i).getCurrSpd();
            }
            else {
                speeds[i] = opponents.get(i - 4).getCurrSpd();
            }
        }

        activeFight = true;
        partyDead = false;
        opponentsDead = false;

        battle();

    }

    public void speedSort() {

        int tempS;
        int tempT;

        for (int i = 0; i < fighters - 1; ++i) {
            tempS = speeds[i];
            tempT = turnOrder[i];
            for (int j = i + 1; j < fighters; ++j) {
                if (speeds[j] > speeds[i]) {
                    tempS = speeds[i];
                    speeds[i] = speeds[j];
                    speeds[j] = tempS;

                    tempT = turnOrder[i];
                    turnOrder[i] = turnOrder[j];
                    turnOrder[j] = tempT;
                }
            }
        }
    }

    public int calcDmg(int def, int atk) {
        int dmgTaken;

        dmgTaken = (int)Math.round((1 - (def/100.0))*atk);

        return dmgTaken;
    }

    public void attack(int user, int target) {
        int offense;
        int defense;
        int result;

        if (user < 4) {
            offense = (party.get(user).getCurrAtk() + party.get(user).getWeaponAtk());
        }
        else {
            offense = opponents.get(user - 4).getCurrAtk();
        }

        if (target < 4) {
            defense = (party.get(target).getCurrDef() + party.get(target).getArmorDef());
        }
        else {
            defense = opponents.get(target - 4).getCurrDef();
        }

        result = calcDmg(defense, offense);

        if (target < 4) {
            party.get(target).setCurrHP(party.get(target).getCurrHP() - result);
        }
        else {
            opponents.get(target - 4).setCurrHP(opponents.get(target - 4).getCurrHP() - result);
        }
    }

    public void useItem(int user, int target, int type, int level) {
        // WIP
    }

    public void castSpell(int user, int target, int type, int index) {
        // WIP
    }

    public void escapeBattle(int user) {
        int speed;

        if (user < 4) {
            speed = party.get(user).getCurrSpd();
        }
        else {
            speed = opponents.get(user - 4).getCurrSpd();
        }

        int escNum;
        escNum = (int)Math.round((1.0/speed)*100);

        Random rand = new Random();
        activeFight = (rand.nextInt(escNum) <= 0);
    }

    public void rewardExp () {
        // WIP
    }

    public void battle() {
        while (activeFight) {
            /*
            Through GUI, get commands for each party member, unless they're dead. Then,
            automatically generate actions for each enemy. These commands will be stored as Integers
            in ArrayLists, which will then be stored in an ArrayList of ArrayList<Integer>.
            index 0 = Type of move (0 = skip, 1 = attack, 2 = use item, 3 = cast spell, 4 = escape)
            index 1 = User of the move (0-3 for party members, 4-7 for enemies)
            index 2 = Target of the move (0-3 for party members, 4-7 for enemies)
            index 3 = Type of item or spell
            index 4 = Specific item in category or index of spell in book
            */
            // Get turn order
            speedSort();
            // Start a turn cycle
            for (int b : turnOrder) {
                boolean dead;
                if (b < 4) {
                    dead = party.get(b).getDead();
                }
                else {
                    dead = opponents.get(b - 4).getDead();
                }
                // Skip turn if dead. If not, determine and carry out move via command list
                if (!dead) {
                    ArrayList<Integer> commands = allCommands.get(b);

                    int moveType;
                    moveType = commands.get(0);

                    switch (moveType) {
                        case 0:
                            System.out.println("moveType: case 0");
                            System.out.println("Skipped because they were dead at start of turn");
                            break;
                        case 1:
                            System.out.println("moveType: case 1");
                            attack(commands.get(1), commands.get(2));
                            break;
                        case 2:
                            System.out.println("moveType: case 2");
                            useItem(commands.get(1), commands.get(2), commands.get(3), commands.get(4));
                            break;
                        case 3:
                            System.out.println("moveType: case 3");
                            castSpell(commands.get(1), commands.get(2), commands.get(3), commands.get(4));
                            break;
                        case 4:
                            System.out.println("moveType: case 44");
                            escapeBattle(commands.get(1));
                            break;
                    }

                }
                // WIP: Take damage from status ailments here
                // Kill battler if their HP drops below zero
                int HP;
                if (b < 4) {
                    HP = party.get(b).getCurrHP();
                    if (HP <= 0) {
                        party.get(b).setCurrHP(0);
                        party.get(b).setDead(true);
                    }
                }
                else {
                    HP = opponents.get(b - 4).getCurrHP();
                    if (HP <= 0) {
                        opponents.get(b - 4).setCurrHP(0);
                        opponents.get(b - 4).setDead(true);
                    }
                }
                // Check if all party members are dead
                boolean allDead = true;
                for (PartyMember p : party) {
                    if (!p.getDead()) {
                        allDead = false;
                        break;
                    }
                }
                if (allDead) {
                    partyDead = true;
                    activeFight = false;
                }
                // Check if all enemies are dead
                allDead = true;
                for (Enemy e : opponents) {
                    if (!e.getDead()) {
                        allDead = false;
                        break;
                    }
                }
                if (allDead) {
                    opponentsDead = true;
                    activeFight = false;
                }
                // Break if fight has ended
                if (!activeFight) {
                    break;
                }
            }
        }
        if (partyDead) {
            System.out.println("The party was defeated...restart from your last save point.");
        }
        else if (opponentsDead) {
            System.out.println("Victory!");
            rewardExp();
        }
        else {
            System.out.println("Escaped safely! All status conditions are gone.");
        }

    }

}
