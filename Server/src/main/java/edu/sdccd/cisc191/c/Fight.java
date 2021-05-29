package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Fight {

    private ArrayList<PartyMember> party;
    private ArrayList<Enemy> opponents;
    private Inventory inv;

    private int fighters;
    private int[] turnOrder;
    private int[] speeds;

    private ArrayList<ArrayList<Integer>> allCommands;

    private boolean activeFight;
    private boolean partyDead;
    private boolean opponentsDead;

    public Fight(ArrayList<PartyMember> good, ArrayList<Enemy> bad, Inventory stuff) {
        party = good;
        opponents = bad;
        inv = stuff;

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

    public void useItem(int user, int target, int type, int level, Inventory inv) {
        if (user < 4) {
            if (target < 4) {
                switch (type) {
                    case 0:
                        switch (level) {
                            case 0:
                                inv.getSmallHealingPotion().useOnPM(party.get(target));
                                break;
                            case 1:
                                inv.getMediumHealingPotion().useOnPM(party.get(target));
                                break;
                            case 2:
                                inv.getLargeHealingPotion().useOnPM(party.get(target));
                                break;
                        }
                        break;
                    case 1:
                        switch (level) {
                            case 0:
                                inv.getSmallAttackPotion().useOnPM(party.get(target));
                                break;
                            case 1:
                                inv.getMediumAttackPotion().useOnPM(party.get(target));
                                break;
                            case 2:
                                inv.getLargeAttackPotion().useOnPM(party.get(target));
                                break;
                        }
                        break;
                    case 2:
                        switch (level) {
                            case 0:
                                inv.getSmallDefensePotion().useOnPM(party.get(target));
                                break;
                            case 1:
                                inv.getMediumDefensePotion().useOnPM(party.get(target));
                                break;
                            case 2:
                                inv.getLargeDefensePotion().useOnPM(party.get(target));
                                break;
                        }
                        break;
                }
            }
            else {
                switch (type) {
                    case 0:
                        switch (level) {
                            case 0:
                                inv.getSmallHealingPotion().useOnEnemy(opponents.get(target - 4));
                                break;
                            case 1:
                                inv.getMediumHealingPotion().useOnEnemy(opponents.get(target - 4));
                                break;
                            case 2:
                                inv.getLargeHealingPotion().useOnEnemy(opponents.get(target - 4));
                                break;
                        }
                        break;
                    case 1:
                        switch (level) {
                            case 0:
                                inv.getSmallAttackPotion().useOnEnemy(opponents.get(target - 4));
                                break;
                            case 1:
                                inv.getMediumAttackPotion().useOnEnemy(opponents.get(target - 4));
                                break;
                            case 2:
                                inv.getLargeAttackPotion().useOnEnemy(opponents.get(target - 4));
                                break;
                        }
                        break;
                    case 2:
                        switch (level) {
                            case 0:
                                inv.getSmallDefensePotion().useOnEnemy(opponents.get(target - 4));
                                break;
                            case 1:
                                inv.getMediumDefensePotion().useOnEnemy(opponents.get(target - 4));
                                break;
                            case 2:
                                inv.getLargeDefensePotion().useOnEnemy(opponents.get(target - 4));
                                break;
                        }
                        break;
                }
            }
        }
        else {
            System.out.println("ERROR: Enemies don't have items!");
        }
    }

    public void castSpell(int user, int target, int type, int index) {
        SpellBook sb = new SpellBook();

        if (user < 4) {
            int mageType = party.get(user).getCurrJob();
            if (mageType > 3) {
                switch (mageType) {
                    case 4:
                        sb = party.get(user).getWhiteMage().getSpellBook();
                        break;
                    case 5:
                        sb = party.get(user).getBlackMage().getSpellBook();
                        break;
                    case 6:
                        sb = party.get(user).getRedMage().getSpellBook();
                        break;
                }

                switch (type) {
                    case 0:
                        // Attack
                        if ((!sb.getListAtk().isEmpty()) && (index < sb.getListAtk().size())) {
                            if (sb.getListAtk().get(index).getCost() < party.get(user).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListAtk().get(index).castOnPM(party.get(target));
                                }
                                else {
                                    sb.getListAtk().get(index).castOnE(opponents.get(target - 4));
                                }
                                party.get(user).setCurrMP(party.get(user).getCurrMP() - sb.getListAtk().get(index).getCost());
                            }
                            else {
                                System.out.println(party.get(user).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(party.get(user).getName() + " has not learned this spell!");
                        }
                        break;
                    case 1:
                        // Heal
                        if ((!sb.getListHeal().isEmpty()) && (index < sb.getListHeal().size())) {
                            if (sb.getListHeal().get(index).getCost() < party.get(user).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListHeal().get(index).castOnPM(party.get(target));
                                } else {
                                    sb.getListHeal().get(index).castOnE(opponents.get(target - 4));
                                }
                                party.get(user).setCurrMP(party.get(user).getCurrMP() - sb.getListHeal().get(index).getCost());
                            } else {
                                System.out.println(party.get(user).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(party.get(user).getName() + " has not learned this spell!");
                        }
                        break;
                    case 2:
                        // Buff
                        if ((!sb.getListInc().isEmpty()) && (index < sb.getListInc().size())) {
                            if (sb.getListInc().get(index).getCost() < party.get(user).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListInc().get(index).castOnPM(party.get(target));
                                } else {
                                    sb.getListInc().get(index).castOnE(opponents.get(target - 4));
                                }
                                party.get(user).setCurrMP(party.get(user).getCurrMP() - sb.getListInc().get(index).getCost());
                            } else {
                                System.out.println(party.get(user).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(party.get(user).getName() + " has not learned this spell!");
                        }
                        break;
                    case 3:
                        // De-Buff
                        if ((!sb.getListDec().isEmpty()) && (index < sb.getListDec().size())) {
                            if (sb.getListDec().get(index).getCost() < party.get(user).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListDec().get(index).castOnPM(party.get(target));
                                } else {
                                    sb.getListDec().get(index).castOnE(opponents.get(target - 4));
                                }
                                party.get(user).setCurrMP(party.get(user).getCurrMP() - sb.getListDec().get(index).getCost());
                            } else {
                                System.out.println(party.get(user).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(party.get(user).getName() + " has not learned this spell!");
                        }
                        break;
                    case 4:
                        // Inflict SE
                        if ((!sb.getListInf().isEmpty()) && (index < sb.getListInf().size())) {
                            if (sb.getListInf().get(index).getCost() < party.get(user).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListInf().get(index).castOnPM(party.get(target));
                                } else {
                                    sb.getListInf().get(index).castOnE(opponents.get(target - 4));
                                }
                                party.get(user).setCurrMP(party.get(user).getCurrMP() - sb.getListInf().get(index).getCost());
                            } else {
                                System.out.println(party.get(user).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(party.get(user).getName() + " has not learned this spell!");
                        }
                        break;
                    case 5:
                        // Cure SE
                        if ((!sb.getListCure().isEmpty()) && (index < sb.getListCure().size())) {
                            if (sb.getListCure().get(index).getCost() < party.get(user).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListCure().get(index).castOnPM(party.get(target));
                                } else {
                                    sb.getListCure().get(index).castOnE(opponents.get(target - 4));
                                }
                                party.get(user).setCurrMP(party.get(user).getCurrMP() - sb.getListCure().get(index).getCost());
                            } else {
                                System.out.println(party.get(user).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(party.get(user).getName() + " has not learned this spell!");
                        }
                        break;
                }
            }
            else {
                System.out.println(party.get(user).getName() + " cannot cast a spell!");
            }
        }
        else {
            if (opponents.get(user - 4).getMagic()) {
                sb = opponents.get(user - 4).getMagicMon().getSpellBook();

                switch (type) {
                    case 0:
                        // Attack
                        if ((!sb.getListAtk().isEmpty()) && (index < sb.getListAtk().size())) {
                            if (sb.getListAtk().get(index).getCost() < opponents.get(user - 4).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListAtk().get(index).castOnPM(party.get(target));
                                } else {
                                    sb.getListAtk().get(index).castOnE(opponents.get(target - 4));
                                }
                                opponents.get(user - 4).setCurrMP(opponents.get(user - 4).getCurrMP() - sb.getListAtk().get(index).getCost());
                            } else {
                                System.out.println(opponents.get(user - 4).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(opponents.get(user - 4).getName() + " has not learned this spell!");
                        }
                        break;
                    case 1:
                        // Heal
                        if ((!sb.getListHeal().isEmpty()) && (index < sb.getListHeal().size())) {
                            if (sb.getListHeal().get(index).getCost() < opponents.get(user - 4).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListHeal().get(index).castOnPM(party.get(target));
                                } else {
                                    sb.getListHeal().get(index).castOnE(opponents.get(target - 4));
                                }
                                opponents.get(user - 4).setCurrMP(opponents.get(user - 4).getCurrMP() - sb.getListHeal().get(index).getCost());
                            } else {
                                System.out.println(opponents.get(user - 4).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(opponents.get(user - 4).getName() + " has not learned this spell!");
                        }
                        break;
                    case 2:
                        // Buff
                        if ((!sb.getListInc().isEmpty()) && (index < sb.getListInc().size())) {
                            if (sb.getListInc().get(index).getCost() < opponents.get(user - 4).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListInc().get(index).castOnPM(party.get(target));
                                } else {
                                    sb.getListInc().get(index).castOnE(opponents.get(target - 4));
                                }
                                opponents.get(user - 4).setCurrMP(opponents.get(user - 4).getCurrMP() - sb.getListInc().get(index).getCost());
                            } else {
                                System.out.println(opponents.get(user - 4).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(opponents.get(user - 4).getName() + " has not learned this spell!");
                        }
                        break;
                    case 3:
                        // De-Buff
                        if ((!sb.getListDec().isEmpty()) && (index < sb.getListDec().size())) {
                            if (sb.getListDec().get(index).getCost() < opponents.get(user - 4).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListDec().get(index).castOnPM(party.get(target));
                                } else {
                                    sb.getListDec().get(index).castOnE(opponents.get(target - 4));
                                }
                                opponents.get(user - 4).setCurrMP(opponents.get(user - 4).getCurrMP() - sb.getListDec().get(index).getCost());
                            } else {
                                System.out.println(opponents.get(user - 4).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(opponents.get(user - 4).getName() + " has not learned this spell!");
                        }
                        break;
                    case 4:
                        // Inflict SE
                        if ((!sb.getListInf().isEmpty()) && (index < sb.getListInf().size())) {
                            if (sb.getListInf().get(index).getCost() < opponents.get(user - 4).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListInf().get(index).castOnPM(party.get(target));
                                } else {
                                    sb.getListInf().get(index).castOnE(opponents.get(target - 4));
                                }
                                opponents.get(user - 4).setCurrMP(opponents.get(user - 4).getCurrMP() - sb.getListInf().get(index).getCost());
                            } else {
                                System.out.println(opponents.get(user - 4).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(opponents.get(user - 4).getName() + " has not learned this spell!");
                        }
                        break;
                    case 5:
                        // Cure SE
                        if ((!sb.getListCure().isEmpty()) && (index < sb.getListCure().size())) {
                            if (sb.getListCure().get(index).getCost() < opponents.get(user - 4).getCurrMP()) {
                                if (target < 4) {
                                    sb.getListCure().get(index).castOnPM(party.get(target));
                                } else {
                                    sb.getListCure().get(index).castOnE(opponents.get(target - 4));
                                }
                                opponents.get(user - 4).setCurrMP(opponents.get(user - 4).getCurrMP() - sb.getListCure().get(index).getCost());
                            } else {
                                System.out.println(opponents.get(user - 4).getName() + " does not have enough MP!");
                            }
                        }
                        else {
                            System.out.println(opponents.get(user - 4).getName() + " has not learned this spell!");
                        }
                        break;
                }
            }
            else {
                System.out.println(opponents.get(user - 4).getName() + " cannot cast a spell!");
            }
        }
    }

    public void eliminateSEs() {
        for (PartyMember member : party) {
            member.setStunned(false);
            member.setBurned(false);
            member.setPoisoned(false);
            member.setAsleep(false);
            member.setPresentSE(false);
            member.setDurSE(0);

            member.setCurrDef(member.getDefStat());
            member.setCurrAtk(member.getAtkStat());
            member.setCurrSpd(member.getSpdStat());
            member.setPresentBuff(false);
            member.setBuffCD(0);
        }
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
        // Earning: base times the level difference if the enemy is higher level
        // If equal level, give base xp instead, if lower level, 0.8 for each level lower
        for (PartyMember member : party) {
            if (!member.getDead()) {
                int sum = 0;

                for (Enemy enemy : opponents) {
                    int levelDiff = enemy.getLevel() - member.getLevel();
                    int base = 25 * enemy.getLevel();
                    if (levelDiff < 0) {
                        base = (int) Math.round(base * (Math.pow(0.8, levelDiff)));
                    }
                    else if (levelDiff == 0) {
                        base = base;
                    }
                    else {
                        base = base * levelDiff;
                    }

                    sum += base;
                }

                member.setExp(member.getExp() + sum);
                System.out.println(member.getName() + " earned " + sum + " experience points!");
            }
        }
    }

    public Inventory battle() {
        while (activeFight) {

            // Display HP of all fighters
            for (PartyMember member : party) {
                System.out.println(member.getName() + " has " + member.getCurrHP() + " HP.");
            }
            for (Enemy enemy: opponents) {
                System.out.println(enemy.getName() + " has " + enemy.getCurrHP() + " HP.");
            }

            /*
            Through GUI, get commands for each party member, unless they're dead. Then,
            automatically generate actions for each enemy. These commands will be stored as Integers
            in ArrayLists, which will then be stored in an ArrayList of ArrayList<Integer>.
            index 0 = Type of move (0 = skip, 1 = attack, 2 = use item, 3 = cast spell, 4 = escape)
            index 1 = User of the move (0-3 for party members, 4-7 for enemies)
            index 2 = Target of the move (0-3 for party members, 4-7 for enemies)
            index 3 = Type of item or spell
                 Item: 0 for Healing, 1 for Attack Buff, 2 for Defense Buff
                 Spell: 0 for Attack, 1 for Heal, 2 for Buff, 3 for Debuff, 4 for SE Inflict, 5 for SE Cure
            index 4 = Specific item in category or index of spell in book
                 Item: 0 for Small, 1 for Medium, 2 for Large
                 Spell: Index for the spell category list
            */

            // Have user select party member moves
            allCommands = new ArrayList<ArrayList<Integer>>();

            for (int i = 0; i < party.size(); ++i) {
                Scanner scnr = new Scanner(System.in);
                System.out.println("Select the commands for " + party.get(i).getName() + ".");
                ArrayList<Integer> move = new ArrayList<Integer>();

                for (int j = 0; j < 5; ++j) {
                    int type = -1;
                    switch (j) {
                        case 0:
                            do {
                                System.out.println("Select the type of command: (0 = skip, 1 = attack, 2 = use item, 3 = cast spell, 4 = escape)");
                                type = scnr.nextInt();
                            } while ((type < 0) || (type > 4));
                            break;
                        case 1:
                            type = i;
                            break;
                        case 2:
                            do {
                                System.out.println("Select the target of the move (0-3 for party members, 4-7 for enemies)");
                                type = scnr.nextInt();
                            } while ((type < 0) || (type > (3 + opponents.size())));
                            break;
                        case 3:
                            switch (move.get(0)) {
                                case 2:
                                    do {
                                        System.out.println("Select the type of item: 0 for Healing, 1 for Attack Buff, 2 for Defense Buff");
                                        type = scnr.nextInt();
                                    } while ((type < 0) || (type > 2));
                                    break;
                                case 3:
                                    do {
                                        System.out.println("Select the type of spell: 0 for Attack, 1 for Heal, 2 for Buff, 3 for Debuff, 4 for SE Inflict, 5 for SE Cure");
                                        type = scnr.nextInt();
                                    } while ((type < 0) || (type > 5));
                                    break;
                                default:
                                    type = 0;
                                    break;
                            }

                            break;
                        case 4:
                            switch (move.get(0)) {
                                case 2:
                                    do {
                                        System.out.println("Select the specific item: 0 for Small, 1 for Medium, 2 for Large");
                                        type = scnr.nextInt();
                                    } while ((type < 0) || (type > 2));
                                    break;
                                case 3:
                                    do {
                                        System.out.println("Select the specific spell: Index for the spell category list, 0 to 4");
                                        type = scnr.nextInt();
                                    } while ((type < 0) || (type > 5));
                                    break;
                                default:
                                    type = 0;
                                    break;
                            }
                            break;
                    }
                    move.add(type);
                }

                allCommands.add(move);
            }

            // Randomly select enemy attack targets
            for (int i = 4; i < opponents.size() + 4; ++i) {
                Random rand = new Random();
                ArrayList<Integer> move = new ArrayList<Integer>();

                move.add(1);
                move.add(i);
                move.add(rand.nextInt(4));
                move.add(0);
                move.add(0);
                allCommands.add(move);
            }

            // Get turn order
            speedSort();
            // Start a turn cycle
            for (int b : turnOrder) {
                // Determine whether paralysis or sleep will prevent move from occurring
                Random rand = new Random();
                boolean dead = false;
                boolean stunned = false;
                boolean asleep = false;
                if (b < 4) {
                    dead = party.get(b).getDead();
                    stunned = party.get(b).getStunned();
                    asleep = party.get(b).getAsleep();

                    if ((asleep) && (rand.nextInt(2) == 0)) {
                        party.get(b).setAsleep(false);
                        party.get(b).setPresentSE(false);
                        party.get(b).setDurSE(0);
                        System.out.println(party.get(b).getName() + " woke up!");
                    }
                }
                else {
                    dead = opponents.get(b - 4).getDead();
                    stunned = opponents.get(b - 4).getStunned();
                    asleep = opponents.get(b - 4).getAsleep();

                    if ((asleep) && (rand.nextInt(2) == 0)) {
                        opponents.get(b - 4).setAsleep(false);
                        opponents.get(b - 4).setPresentSE(false);
                        opponents.get(b - 4).setDurSE(0);
                        System.out.println(opponents.get(b - 4).getName() + " woke up!");
                    }
                }

                int tgt = 0;
                // Skip turn if dead. If not, determine and carry out move via command list
                if (!dead) {
                    ArrayList<Integer> commands = allCommands.get(b);
                    int moveType;
                    moveType = commands.get(0);

                    /* DEBUG
                    System.out.println("" + allCommands);
                    System.out.println("" + commands);
                    System.out.println("" + moveType);
                    DEBUG */

                    tgt = commands.get(2);
                    // Skip turn if asleep or if paralyzed (50%)
                    if ((stunned) && (rand.nextInt(2) == 0)) {
                        System.out.println("Couldn't move due to paralysis!");
                    }
                    else if (asleep) {
                        System.out.println("Sleeping...couldn't perform move...");
                    }
                    else {
                        switch (moveType) {
                            case 0:
                                System.out.println("Skipped because they were dead at start of turn.");
                                break;
                            case 1:
                                attack(commands.get(1), commands.get(2));
                                break;
                            case 2:
                                useItem(commands.get(1), commands.get(2), commands.get(3), commands.get(4), inv);
                                break;
                            case 3:
                                castSpell(commands.get(1), commands.get(2), commands.get(3), commands.get(4));
                                break;
                            case 4:
                                escapeBattle(commands.get(1));
                                break;
                        }
                    }

                }
                // WIP: Deal with buff/SE cooldowns
                if (b < 4) {
                    if (party.get(b).getPresentBuff()) {
                        party.get(b).setBuffCD(party.get(b).getBuffCD() - 1);
                    }

                    if (party.get(b).getBuffCD() <= 0) {
                        party.get(b).setCurrDef(party.get(b).getDefStat());
                        party.get(b).setCurrAtk(party.get(b).getAtkStat());
                        party.get(b).setCurrSpd(party.get(b).getSpdStat());
                        party.get(b).setPresentBuff(false);
                    }
                }
                else {
                    if (opponents.get(b - 4).getPresentBuff()) {
                        opponents.get(b - 4).setBuffCD(opponents.get(b - 4).getBuffCD() - 1);
                    }

                    if (opponents.get(b - 4).getBuffCD() <= 0) {
                        opponents.get(b - 4).setCurrDef(opponents.get(b - 4).getDefStat());
                        opponents.get(b - 4).setCurrAtk(opponents.get(b - 4).getAtkStat());
                        opponents.get(b - 4).setCurrSpd(opponents.get(b - 4).getSpdStat());
                        opponents.get(b - 4).setPresentBuff(false);
                    }
                }
                // WIP: Take damage from status effects
                if (b < 4) {
                    if (party.get(b).getPresentSE()) {
                        if (party.get(b).getPoisoned()) {
                            party.get(b).setCurrHP(party.get(b).getCurrHP() - 7);
                        }
                        else if (party.get(b).getBurned()) {
                            party.get(b).setCurrHP(party.get(b).getCurrHP() - 5);
                        }
                    }

                    party.get(b).setDurSE(party.get(b).getDurSE() - 1);

                    if (party.get(b).getDurSE() <= 0) {
                        party.get(b).setStunned(false);
                        party.get(b).setBurned(false);
                        party.get(b).setPoisoned(false);
                        party.get(b).setAsleep(false);
                        party.get(b).setPresentSE(false);
                    }
                }
                else {
                    if (opponents.get(b - 4).getPresentSE()) {
                        if (opponents.get(b - 4).getPoisoned()) {
                            opponents.get(b - 4).setCurrHP(opponents.get(b - 4).getCurrHP() - 7);
                        }
                        else if (opponents.get(b - 4).getBurned()) {
                            opponents.get(b - 4).setCurrHP(opponents.get(b - 4).getCurrHP() - 5);
                        }
                    }

                    opponents.get(b - 4).setDurSE(opponents.get(b - 4).getDurSE() - 1);

                    if (opponents.get(b - 4).getDurSE() <= 0) {
                        opponents.get(b - 4).setStunned(false);
                        opponents.get(b - 4).setBurned(false);
                        opponents.get(b - 4).setPoisoned(false);
                        opponents.get(b - 4).setAsleep(false);
                        opponents.get(b - 4).setPresentSE(false);
                    }
                }
                // Kill target if their HP drops below zero
                if (tgt < 4) {
                    if (party.get(tgt).getCurrHP() <= 0) {
                        party.get(tgt).setCurrHP(0);
                        party.get(tgt).setDead(true);
                    }
                }
                else {
                    if ( opponents.get(tgt - 4).getCurrHP() <= 0) {
                        opponents.get(tgt - 4).setCurrHP(0);
                        opponents.get(tgt - 4).setDead(true);
                    }
                }
                // Kill user if their HP drops below zero
                if (b < 4) {
                    if (party.get(b).getCurrHP() <= 0) {
                        party.get(b).setCurrHP(0);
                        party.get(b).setDead(true);
                    }
                }
                else {
                    if ( opponents.get(b - 4).getCurrHP() <= 0) {
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
                /* DEBUG
                System.out.println("" + partyDead);
                System.out.println("" + opponentsDead);
                System.out.println("" + activeFight);
                DEBUG */
            }
        }

        if (partyDead) {
            System.out.println("The party was defeated...restart from your last save point.");
        }
        else if (opponentsDead) {
            System.out.println("Victory!");
            eliminateSEs();
            rewardExp();
        }
        else {
            System.out.println("Escaped safely! All status conditions are gone.");
            eliminateSEs();
        }

        inv.setGo(!partyDead);
        return inv;

    }

}