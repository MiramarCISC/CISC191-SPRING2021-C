package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static org.apache.commons.lang3.ArrayUtils.contains;

public class Main {

    public static void mainMenu(Scanner scnr) {
        boolean run = true;
        String input = "";

        while (run) {
            System.out.println("Group C RPG Project by:");
            System.out.println("An Truong, Andres Cadena-Perales, Kenton Reiss, Mohamed Muse, Terry Tran");
            System.out.println("CISC 191: Intermediate Java Programming, Professor Andrew Huang, May 28th, 2021");
            System.out.println("");
            System.out.println("Rules:");
            System.out.println("All status effects and buffs are nullified after every battle.");
            System.out.println("However, buffs can be placed on party members before a battle starts.");
            System.out.println("Dead party members can only be revived at Inns (which also restore HP/MP).");
            System.out.println("The game is over when all party members are dead. Progress from the last save is lost.");
            System.out.println("");
            System.out.println("***WARNING: TYPING NON-INTS WHEN ASKED FOR INTS MAY CAUSE GAME TO CRASH***");
            System.out.println("");
            System.out.println("Type N to start a new game, L to load a game, or Q to quit.");

            input = scnr.nextLine().toLowerCase(Locale.ROOT);

            switch (input) {
                case "n":
                    newGame(scnr);
                    break;
                case "l":
                    loadGame();
                    break;
                case "q":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid input.");
            }

        }
    }

    public static void newGame(Scanner scnr) {

        //Create master spellbooks
        ArrayList<SpellAoH> masterListAtk = new ArrayList<SpellAoH>();

        masterListAtk.add(new SpellAoH("Fire Ball", 1, 3, false, 1, 10));
        masterListAtk.add(new SpellAoH("Fire Plume", 3, 5, false, 1, 20));
        masterListAtk.add(new SpellAoH("Flame Riser", 7, 7, false, 1, 30));
        masterListAtk.add(new SpellAoH("Flame Blast", 13, 9, false, 1, 40));
        masterListAtk.add(new SpellAoH("Heat Sink", 20, 11, false, 1, 50));

        masterListAtk.add(new SpellAoH("Water Stream", 1, 3, false, 2, 10));
        masterListAtk.add(new SpellAoH("Water Cutter", 3, 5, false, 2, 20));
        masterListAtk.add(new SpellAoH("Rapid Stream", 7, 7, false, 2, 30));
        masterListAtk.add(new SpellAoH("Liquid Bullets", 13, 9, false, 2, 40));
        masterListAtk.add(new SpellAoH("Glacier Crusher", 20, 11, false, 2, 50));

        masterListAtk.add(new SpellAoH("Small Shock", 1, 3, false, 2, 10));
        masterListAtk.add(new SpellAoH("Large Shock", 3, 5, false, 2, 20));
        masterListAtk.add(new SpellAoH("Thunder Coup", 7, 7, false, 2, 30));
        masterListAtk.add(new SpellAoH("Thunder Reign", 13, 9, false, 2, 40));
        masterListAtk.add(new SpellAoH("Lightning Bolt", 20, 11, false, 2, 50));

        ArrayList<SpellAoH> masterListHeal = new ArrayList<SpellAoH>();

        masterListHeal.add(new SpellAoH("Small Heal", 1, 3, true, 0, 12));
        masterListHeal.add(new SpellAoH("Moderate Heal", 5, 7, true, 0, 24));
        masterListHeal.add(new SpellAoH("Large Heal", 9, 11, true, 0, 36));
        masterListHeal.add(new SpellAoH("Half Heal", 17, 15, true, 0, 50));
        masterListHeal.add(new SpellAoH("Full Heal", 20, 25, true, 0, 100));

        ArrayList<SpellBuff> masterListInc = new ArrayList<SpellBuff>();

        masterListInc.add(new SpellBuff("Fortify", 1, 7, true, 2, 0, 10));
        masterListInc.add(new SpellBuff("Strengthen", 1, 7, true, 2, 1, 10));
        masterListInc.add(new SpellBuff("Haste", 1, 7, true, 2, 2, 10));

        ArrayList<SpellBuff> masterListDec = new ArrayList<SpellBuff>();

        masterListDec.add(new SpellBuff("Disrupt", 1, 7, false, 2, 0, 10));
        masterListDec.add(new SpellBuff("Weaken", 1, 7, false, 2, 1, 10));
        masterListDec.add(new SpellBuff("Slow", 1, 7, false, 2, 2, 10));

        ArrayList<SpellSE> masterListInf = new ArrayList<SpellSE>();

        masterListInf.add(new SpellSE("Paralyze", 1, 5, false, 2, 0));
        masterListInf.add(new SpellSE("Burn", 5, 5, false, 2, 1));
        masterListInf.add(new SpellSE("Poison", 5, 5, false, 2, 2));
        masterListInf.add(new SpellSE("Lull", 5, 5, false, 2, 3));

        ArrayList<SpellSE> masterListCure = new ArrayList<SpellSE>();

        masterListCure.add(new SpellSE("Mobilize", 1, 5, true, 0, 0));
        masterListCure.add(new SpellSE("Cool Down", 5, 5, true, 0, 1));
        masterListCure.add(new SpellSE("Anti-Venom", 5, 5, true, 0, 2));
        masterListCure.add(new SpellSE("Awaken", 5, 5, true, 0, 3));

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<PartyMember> party = new ArrayList<PartyMember>();

        for (int i = 0; i < 4; ++i) {
            System.out.println("Enter the name of Party Member #" + (i + 1) + ":");
            names.add(scnr.nextLine());

            int jobType = 10;
            do {
                System.out.println("Job values: 0 (1H-Knight), 1 (2H-Knight), 2 (Throw), 3 (Archer), 4 (White Mage), 5 (Black Mage), or 6 (Red Mage)");
                System.out.println("Enter their job value: ");
                jobType = scnr.nextInt();
                scnr.nextLine();
            } while ((jobType < 0) || (jobType > 6));

            PartyMember temp = new PartyMember(names.get(i), 1, 0, 1, 1, false, jobType, 100);
            party.add(temp);
            party.get(i).calculateStats(masterListAtk, masterListHeal, masterListDec, masterListInc, masterListInf,
                    masterListCure);
            party.get(i).newStats();
        }

        Inventory newInv = new Inventory(5, 3, 1, 5, 3, 1, 5, 3, 1);

        Grid newGrid = new Grid();

        playGame(scnr, newGrid, 45, party, newInv);

    }

    public static void loadGame() {
        //Create master spellbooks
        ArrayList<SpellAoH> masterListAtk = new ArrayList<SpellAoH>();

        masterListAtk.add(new SpellAoH("Fire Ball", 1, 3, false, 1, 10));
        masterListAtk.add(new SpellAoH("Fire Plume", 3, 5, false, 1, 20));
        masterListAtk.add(new SpellAoH("Flame Riser", 7, 7, false, 1, 30));
        masterListAtk.add(new SpellAoH("Flame Blast", 13, 9, false, 1, 40));
        masterListAtk.add(new SpellAoH("Heat Sink", 20, 11, false, 1, 50));

        masterListAtk.add(new SpellAoH("Water Stream", 1, 3, false, 2, 10));
        masterListAtk.add(new SpellAoH("Water Cutter", 3, 5, false, 2, 20));
        masterListAtk.add(new SpellAoH("Rapid Stream", 7, 7, false, 2, 30));
        masterListAtk.add(new SpellAoH("Liquid Bullets", 13, 9, false, 2, 40));
        masterListAtk.add(new SpellAoH("Glacier Crusher", 20, 11, false, 2, 50));

        masterListAtk.add(new SpellAoH("Small Shock", 1, 3, false, 2, 10));
        masterListAtk.add(new SpellAoH("Large Shock", 3, 5, false, 2, 20));
        masterListAtk.add(new SpellAoH("Thunder Coup", 7, 7, false, 2, 30));
        masterListAtk.add(new SpellAoH("Thunder Reign", 13, 9, false, 2, 40));
        masterListAtk.add(new SpellAoH("Lightning Bolt", 20, 11, false, 2, 50));

        ArrayList<SpellAoH> masterListHeal = new ArrayList<SpellAoH>();

        masterListHeal.add(new SpellAoH("Small Heal", 1, 3, true, 0, 12));
        masterListHeal.add(new SpellAoH("Moderate Heal", 5, 7, true, 0, 24));
        masterListHeal.add(new SpellAoH("Large Heal", 9, 11, true, 0, 36));
        masterListHeal.add(new SpellAoH("Half Heal", 17, 15, true, 0, 50));
        masterListHeal.add(new SpellAoH("Full Heal", 20, 25, true, 0, 100));

        ArrayList<SpellBuff> masterListInc = new ArrayList<SpellBuff>();

        masterListInc.add(new SpellBuff("Fortify", 1, 7, true, 2, 0, 10));
        masterListInc.add(new SpellBuff("Strengthen", 1, 7, true, 2, 1, 10));
        masterListInc.add(new SpellBuff("Haste", 1, 7, true, 2, 2, 10));

        ArrayList<SpellBuff> masterListDec = new ArrayList<SpellBuff>();

        masterListDec.add(new SpellBuff("Disrupt", 1, 7, false, 2, 0, 10));
        masterListDec.add(new SpellBuff("Weaken", 1, 7, false, 2, 1, 10));
        masterListDec.add(new SpellBuff("Slow", 1, 7, false, 2, 2, 10));

        ArrayList<SpellSE> masterListInf = new ArrayList<SpellSE>();

        masterListInf.add(new SpellSE("Paralyze", 1, 5, false, 2, 0));
        masterListInf.add(new SpellSE("Burn", 5, 5, false, 2, 1));
        masterListInf.add(new SpellSE("Poison", 5, 5, false, 2, 2));
        masterListInf.add(new SpellSE("Lull", 5, 5, false, 2, 3));

        ArrayList<SpellSE> masterListCure = new ArrayList<SpellSE>();

        masterListCure.add(new SpellSE("Mobilize", 1, 5, true, 0, 0));
        masterListCure.add(new SpellSE("Cool Down", 5, 5, true, 0, 1));
        masterListCure.add(new SpellSE("Anti-Venom", 5, 5, true, 0, 2));
        masterListCure.add(new SpellSE("Awaken", 5, 5, true, 0, 3));

        System.out.print("Loading a game is not yet implemented.");

    }

    public static void levelUpCheck(ArrayList<PartyMember> party) {
        //Create master spellbooks
        ArrayList<SpellAoH> masterListAtk = new ArrayList<SpellAoH>();

        masterListAtk.add(new SpellAoH("Fire Ball", 1, 3, false, 1, 10));
        masterListAtk.add(new SpellAoH("Fire Plume", 3, 5, false, 1, 20));
        masterListAtk.add(new SpellAoH("Flame Riser", 7, 7, false, 1, 30));
        masterListAtk.add(new SpellAoH("Flame Blast", 13, 9, false, 1, 40));
        masterListAtk.add(new SpellAoH("Heat Sink", 20, 11, false, 1, 50));

        masterListAtk.add(new SpellAoH("Water Stream", 1, 3, false, 2, 10));
        masterListAtk.add(new SpellAoH("Water Cutter", 3, 5, false, 2, 20));
        masterListAtk.add(new SpellAoH("Rapid Stream", 7, 7, false, 2, 30));
        masterListAtk.add(new SpellAoH("Liquid Bullets", 13, 9, false, 2, 40));
        masterListAtk.add(new SpellAoH("Glacier Crusher", 20, 11, false, 2, 50));

        masterListAtk.add(new SpellAoH("Small Shock", 1, 3, false, 2, 10));
        masterListAtk.add(new SpellAoH("Large Shock", 3, 5, false, 2, 20));
        masterListAtk.add(new SpellAoH("Thunder Coup", 7, 7, false, 2, 30));
        masterListAtk.add(new SpellAoH("Thunder Reign", 13, 9, false, 2, 40));
        masterListAtk.add(new SpellAoH("Lightning Bolt", 20, 11, false, 2, 50));

        ArrayList<SpellAoH> masterListHeal = new ArrayList<SpellAoH>();

        masterListHeal.add(new SpellAoH("Small Heal", 1, 3, true, 0, 12));
        masterListHeal.add(new SpellAoH("Moderate Heal", 5, 7, true, 0, 24));
        masterListHeal.add(new SpellAoH("Large Heal", 9, 11, true, 0, 36));
        masterListHeal.add(new SpellAoH("Half Heal", 17, 15, true, 0, 50));
        masterListHeal.add(new SpellAoH("Full Heal", 20, 25, true, 0, 100));

        ArrayList<SpellBuff> masterListInc = new ArrayList<SpellBuff>();

        masterListInc.add(new SpellBuff("Fortify", 1, 7, true, 2, 0, 10));
        masterListInc.add(new SpellBuff("Strengthen", 1, 7, true, 2, 1, 10));
        masterListInc.add(new SpellBuff("Haste", 1, 7, true, 2, 2, 10));

        ArrayList<SpellBuff> masterListDec = new ArrayList<SpellBuff>();

        masterListDec.add(new SpellBuff("Disrupt", 1, 7, false, 2, 0, 10));
        masterListDec.add(new SpellBuff("Weaken", 1, 7, false, 2, 1, 10));
        masterListDec.add(new SpellBuff("Slow", 1, 7, false, 2, 2, 10));

        ArrayList<SpellSE> masterListInf = new ArrayList<SpellSE>();

        masterListInf.add(new SpellSE("Paralyze", 1, 5, false, 2, 0));
        masterListInf.add(new SpellSE("Burn", 5, 5, false, 2, 1));
        masterListInf.add(new SpellSE("Poison", 5, 5, false, 2, 2));
        masterListInf.add(new SpellSE("Lull", 5, 5, false, 2, 3));

        ArrayList<SpellSE> masterListCure = new ArrayList<SpellSE>();

        masterListCure.add(new SpellSE("Mobilize", 1, 5, true, 0, 0));
        masterListCure.add(new SpellSE("Cool Down", 5, 5, true, 0, 1));
        masterListCure.add(new SpellSE("Anti-Venom", 5, 5, true, 0, 2));
        masterListCure.add(new SpellSE("Awaken", 5, 5, true, 0, 3));

        for (PartyMember member : party) {
            if (member.getExp() >= member.getMaxXP()) {
                member.levelUp(masterListAtk, masterListHeal, masterListDec, masterListInc, masterListInf,
                        masterListCure);
            }
        }
    }

    public static void saveGame(Grid grid, ArrayList<PartyMember> party, Inventory inv) {
        System.out.println("Saving a game is not yet implemented.");
    }

    public static String inGameMenu(Scanner scnr) {
        String letter = "";

        while ((!letter.equals("e")) && (!letter.equals("i")) && (!letter.equals("j")) && (!letter.equals("m")) &&
                (!letter.equals("s")) && (!letter.equals("p")) && (!letter.equals("q"))) {
            System.out.println("Menu:");
            System.out.println("Type E to change equipment. Type J to change jobs.");
            System.out.println("Type I to use an item. Type S to use a spell.");
            System.out.println("Type P to view Party stats. Type M to move one square on the map.");
            System.out.println("Type Q to save and quit.");
            letter = scnr.nextLine().toLowerCase();
        }

        return letter;
    }

    public static int movePos(Scanner scnr, int position) {
        boolean finished = false;
        String dir = "";
        while (!finished) {
            System.out.println("Type W to move up, S to move down, A to move left, or D to move right.");
            dir = scnr.nextLine().toLowerCase();

            switch (dir) {
                case "w":
                    if (position > 10){
                        position -= 10;
                        finished = true;
                        break;
                    }

                    else {
                        System.out.println("You cannot move up.");
                        break;
                    }
                case "s":
                    if (position < 91){
                        position += 10;
                        finished = true;
                        break;
                    }

                    else {
                        System.out.println("You cannot move down.");
                        break;
                    }
                case "a":
                    if (!contains(new int[]{1, 11, 21, 31, 41, 51, 61, 71, 81, 91}, position)){
                        position -= 1;
                        finished = true;
                        break;
                    }

                    else {
                        System.out.println("You cannot move left.");
                        break;
                    }
                case "d":
                    if (position > 10 && position % 10 != 0){
                        position += 1;
                        finished = true;
                        break;
                    }
                    else if (position < 10) {
                        position += 1;
                        finished = true;
                        break;
                    }
                    else {
                        System.out.println("You cannot move right.");
                        break;
                    }

                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
        System.out.println("");
        return position;
    }

    public static void displayStats(ArrayList<PartyMember> party) {
        for (PartyMember member : party) {
            String dOa = "";
            if (member.getDead()) {
                dOa = "Dead";
            }
            else {
                dOa = "Alive";
            }
            System.out.println(member.getName() + ", " + dOa);
            System.out.println("Level " + member.getLevel() + ", " + member.getExp() + " / " + member.getMaxXP() + " EXP to next level");
            System.out.println("" + member.getCurrHP() + " / " + member.getMaxHP() + " HP");
            if (member.getCurrJob() > 3) {
                System.out.println("" + member.getCurrMP() + " / " + member.getMaxMP() + " MP");
            }
            System.out.println("Atk: " + member.getCurrAtk() + ", Def: " + member.getCurrDef() + ", Spd: " + member.getCurrSpd());
            System.out.println("Weapon Atk: " + member.getWeaponAtk() + ", Armor Def: " + member.getArmorDef());
            System.out.println("");
        }
    }

    public static void equipChange(Scanner scnr, ArrayList<PartyMember> party, Inventory inv) {
        String selection;

        do {
            System.out.println(" - Choose the party member whose equipment you want to change. - ");
            System.out.println("  0 for " + party.get(0).getName() + " who has " + party.get(0).getCurrJobName() + " as current job.");
            System.out.println("  1 for " + party.get(1).getName() + " who has " + party.get(1).getCurrJobName() + " as current job.");
            System.out.println("  2 for " + party.get(2).getName() + " who has " + party.get(2).getCurrJobName() + " as current job.");
            System.out.println("  3 for " + party.get(3).getName() + " who has " + party.get(3).getCurrJobName() + " as current job.");
            selection = scnr.nextLine();

            System.out.println();

        } while ((!selection.equals("1")) && (!selection.equals("2")) && (!selection.equals("3")) && (!selection.equals("0")));

        // Convert String to int
        int selectedMember = Integer.parseInt(selection);

/*
        // Gets Big Chests to have equipment to test equipping
        inv.obtainedBigChest();
        inv.obtainedBigChest();
        inv.obtainedBigChest();
        inv.showInventory();
*/

        System.out.println("  Selected Party Member: " + party.get(selectedMember).getName());
        System.out.println("  - CURRENT EQUIPMENT - ");

        if (party.get(selectedMember).getCurrEquip() == null) {
            System.out.println("   No Current Equipment.");
        }
        else {

            party.get(selectedMember).getCurrEquip().showEquipment();
            System.out.println();

            do {
                System.out.println(" - Choose the Equipment you want to change. - ");
                System.out.println("  0 for Armor.");
                System.out.println("  1 for Weapons.");
                selection = scnr.nextLine();

                System.out.println();

            } while ((!selection.equals("1")) && (!selection.equals("0")));

            // Convert String to int
            int selectedEquipment = Integer.parseInt(selection);

            switch (selectedEquipment) {
                case 0:
                    armorChange(inv, party, scnr, selection, selectedMember);
                    System.out.println("");
                    break;

                case 1:
                    weaponChange(inv, party, scnr, selection, selectedMember);
                    System.out.println("");
                    break;

                default:
                    System.out.println("NO EQUIPMENT PART SELECTED");
                    System.out.println("");
                    break;
            }
        }
    }

    public static void armorChange(Inventory inv, ArrayList<PartyMember> party, Scanner scnr, String selection, int selectedMember) {
        do {
            System.out.println(" - Choose the Armor you want to change. - ");
            System.out.println("  0 for Head.");
            System.out.println("  1 for Body.");
            System.out.println("  2 for Legs.");
            System.out.println("  3 for Feet.");
            selection = scnr.nextLine();

            System.out.println();

        } while ((!selection.equals("1")) && (!selection.equals("2")) && (!selection.equals("3")) && (!selection.equals("0")));

        // Convert String to int
        int selectedArmor = Integer.parseInt(selection);

        switch(selectedArmor) {
            case 0:
                if (inv.getHeads().size() > 0) {
                    do {
                        System.out.println(" - Select New Head Armor - ");

                        for(int i = 0; i < inv.getHeads().size(); ++i) {
                            System.out.println( i + " for "+ inv.getHeads().get(i).toString());
                        }

                        selection = scnr.nextLine();
                        selectedArmor = Integer.parseInt(selection);

                    } while( selectedArmor > inv.getHeads().size() || selectedArmor < 0);

                    party.get(selectedMember).changeHead(
                            inv.getHeads().get(selectedArmor)
                    );
                } else {
                    System.out.println("No head armor in inventory.");
                    System.out.println();
                }
                break;

            case 1:
                if (inv.getBodies().size() > 0) {
                    do {
                        System.out.println(" - Select New Body Armor - ");
                        for(int i = 0; i < inv.getBodies().size(); ++i) {
                            System.out.println( i + " for "+ inv.getBodies().get(i).toString());
                        }

                        selection = scnr.nextLine();
                        selectedArmor = Integer.parseInt(selection);

                    } while( selectedArmor > inv.getBodies().size() || selectedArmor < 0);

                    party.get(selectedMember).changeBody(
                            inv.getBodies().get(selectedArmor)
                    );
                } else {
                    System.out.println("No body armor in inventory.");
                    System.out.println();
                }
                break;

            case 2:
                if (inv.getLegs().size() > 0) {
                    do {
                        System.out.println(" - Select New Leg Armor - ");
                        for(int i = 0; i < inv.getLegs().size(); ++i) {
                            System.out.println( i + " for "+ inv.getLegs().get(i).toString());
                        }

                        selection = scnr.nextLine();
                        selectedArmor = Integer.parseInt(selection);

                    } while( selectedArmor > inv.getLegs().size() || selectedArmor < 0);

                    party.get(selectedMember).changeLegs(
                            inv.getLegs().get(selectedArmor)
                    );
                } else {
                    System.out.println("No leg armor in inventory.");
                    System.out.println();
                }
                break;

            case 3:
                if (inv.getFeet().size() > 0) {
                    do {
                        System.out.println(" - Select New Feet Armor - ");
                        for(int i = 0; i < inv.getFeet().size(); ++i) {
                            System.out.println( i + " for "+ inv.getFeet().get(i).toString());
                        }

                        selection = scnr.nextLine();
                        selectedArmor = Integer.parseInt(selection);

                    } while( selectedArmor > inv.getFeet().size() || selectedArmor < 0);

                    party.get(selectedMember).changeFeet(
                            inv.getFeet().get(selectedArmor)
                    );
                } else {
                    System.out.println("No feet armor in inventory.");
                    System.out.println();
                }
                break;

            default:
                System.out.println("NO ARMOR PART SELECTED");
                break;
        }

    }

    public static void weaponChange(Inventory inv, ArrayList<PartyMember> party, Scanner scnr, String selection, int selectedMember) {
        do {
            System.out.println(" - Choose the Weapon you want to use. - ");
            System.out.println("  0 for Sword.");
            System.out.println("  1 for Bow.");
            System.out.println("  2 for Staff.");
            System.out.println("  3 for Throwable.");
            System.out.println("  4 for Shield.");
            selection = scnr.nextLine();

            System.out.println();

        } while ((!selection.equals("1")) && (!selection.equals("2")) && (!selection.equals("3")) && (!selection.equals("0")) && (!selection.equals("4")));

        // Convert String to int
        int selectedWeapon = Integer.parseInt(selection);

        switch(selectedWeapon) {
            case 0:
                if (inv.getSwords().size() > 0) {
                    do {
                        System.out.println(" - Select A New Sword - ");
                        for (int i = 0; i < inv.getSwords().size(); ++i) {
                            System.out.println(i + " for " + inv.getSwords().get(i).toString());
                        }

                        selection = scnr.nextLine();
                        selectedWeapon = Integer.parseInt(selection);

                    } while (selectedWeapon > inv.getSwords().size() || selectedWeapon < 0);

                    party.get(selectedMember).changeSwd1(
                            inv.getSwords().get(selectedWeapon)
                    );
                } else {
                    System.out.println("No swords in inventory.");
                    System.out.println();
                }

                break;

            case 1:
                if (inv.getBows().size() > 0) {
                    do {
                        System.out.println(" - Select A New Bow - ");
                        for (int i = 0; i < inv.getBows().size(); ++i) {
                            System.out.println(i + " for " + inv.getBows().get(i).toString());
                        }

                        selection = scnr.nextLine();
                        selectedWeapon = Integer.parseInt(selection);

                    } while (selectedWeapon > inv.getBows().size() || selectedWeapon < 0);

                    party.get(selectedMember).changeBow(
                            inv.getBows().get(selectedWeapon)
                    );
                } else {
                    System.out.println("No bows in inventory.");
                    System.out.println();
                }

                break;

            case 2:
                if (inv.getStaffs().size() > 0) {
                    do {
                        System.out.println(" - Select A New Staff - ");
                        for (int i = 0; i < inv.getStaffs().size(); ++i) {
                            System.out.println(i + " for " + inv.getStaffs().get(i).toString());
                        }

                        selection = scnr.nextLine();
                        selectedWeapon = Integer.parseInt(selection);

                    } while (selectedWeapon > inv.getStaffs().size() || selectedWeapon < 0);

                    party.get(selectedMember).changeStf1(
                            inv.getStaffs().get(selectedWeapon)
                    );
                } else {
                    System.out.println("No staffs in inventory.");
                    System.out.println();
                }
                break;

            case 3:
                if (inv.getThrowable().size() > 0) {
                    do {
                        System.out.println(" - Select A New Throwable - ");
                        for (int i = 0; i < inv.getThrowable().size(); ++i) {
                            System.out.println(i + " for " + inv.getThrowable().get(i).toString());
                        }

                        selection = scnr.nextLine();
                        selectedWeapon = Integer.parseInt(selection);

                    } while (selectedWeapon > inv.getThrowable().size() || selectedWeapon < 0);

                    party.get(selectedMember).changeThrow(
                            inv.getThrowable().get(selectedWeapon)
                    );
                } else {
                    System.out.println("No throwables in inventory.");
                    System.out.println();
                }
                break;

            case 4:
                if (inv.getShields().size() > 0) {
                    do {
                        System.out.println(" - Select A New Shield - ");
                        for (int i = 0; i < inv.getShields().size(); ++i) {
                            System.out.println(i + " for " + inv.getShields().get(i).toString());
                        }

                        selection = scnr.nextLine();
                        selectedWeapon = Integer.parseInt(selection);

                    } while (selectedWeapon > inv.getShields().size() || selectedWeapon < 0);

                    party.get(selectedMember).changeShield(
                            inv.getShields().get(selectedWeapon)
                    );
                } else {
                    System.out.println("No shields in inventory.");
                    System.out.println();
                }
                break;

            default:
                System.out.println("NO WEAPON SELECTED");
                break;

        }
    }

    public static void jobChange(Scanner scnr, ArrayList<PartyMember> party) {
        //Create master spellbooks
        ArrayList<SpellAoH> masterListAtk = new ArrayList<SpellAoH>();

        masterListAtk.add(new SpellAoH("Fire Ball", 1, 3, false, 1, 10));
        masterListAtk.add(new SpellAoH("Fire Plume", 3, 5, false, 1, 20));
        masterListAtk.add(new SpellAoH("Flame Riser", 7, 7, false, 1, 30));
        masterListAtk.add(new SpellAoH("Flame Blast", 13, 9, false, 1, 40));
        masterListAtk.add(new SpellAoH("Heat Sink", 20, 11, false, 1, 50));

        masterListAtk.add(new SpellAoH("Water Stream", 1, 3, false, 2, 10));
        masterListAtk.add(new SpellAoH("Water Cutter", 3, 5, false, 2, 20));
        masterListAtk.add(new SpellAoH("Rapid Stream", 7, 7, false, 2, 30));
        masterListAtk.add(new SpellAoH("Liquid Bullets", 13, 9, false, 2, 40));
        masterListAtk.add(new SpellAoH("Glacier Crusher", 20, 11, false, 2, 50));

        masterListAtk.add(new SpellAoH("Small Shock", 1, 3, false, 2, 10));
        masterListAtk.add(new SpellAoH("Large Shock", 3, 5, false, 2, 20));
        masterListAtk.add(new SpellAoH("Thunder Coup", 7, 7, false, 2, 30));
        masterListAtk.add(new SpellAoH("Thunder Reign", 13, 9, false, 2, 40));
        masterListAtk.add(new SpellAoH("Lightning Bolt", 20, 11, false, 2, 50));

        ArrayList<SpellAoH> masterListHeal = new ArrayList<SpellAoH>();

        masterListHeal.add(new SpellAoH("Small Heal", 1, 3, true, 0, 12));
        masterListHeal.add(new SpellAoH("Moderate Heal", 5, 7, true, 0, 24));
        masterListHeal.add(new SpellAoH("Large Heal", 9, 11, true, 0, 36));
        masterListHeal.add(new SpellAoH("Half Heal", 17, 15, true, 0, 50));
        masterListHeal.add(new SpellAoH("Full Heal", 20, 25, true, 0, 100));

        ArrayList<SpellBuff> masterListInc = new ArrayList<SpellBuff>();

        masterListInc.add(new SpellBuff("Fortify", 1, 7, true, 2, 0, 10));
        masterListInc.add(new SpellBuff("Strengthen", 1, 7, true, 2, 1, 10));
        masterListInc.add(new SpellBuff("Haste", 1, 7, true, 2, 2, 10));

        ArrayList<SpellBuff> masterListDec = new ArrayList<SpellBuff>();

        masterListDec.add(new SpellBuff("Disrupt", 1, 7, false, 2, 0, 10));
        masterListDec.add(new SpellBuff("Weaken", 1, 7, false, 2, 1, 10));
        masterListDec.add(new SpellBuff("Slow", 1, 7, false, 2, 2, 10));

        ArrayList<SpellSE> masterListInf = new ArrayList<SpellSE>();

        masterListInf.add(new SpellSE("Paralyze", 1, 5, false, 2, 0));
        masterListInf.add(new SpellSE("Burn", 5, 5, false, 2, 1));
        masterListInf.add(new SpellSE("Poison", 5, 5, false, 2, 2));
        masterListInf.add(new SpellSE("Lull", 5, 5, false, 2, 3));

        ArrayList<SpellSE> masterListCure = new ArrayList<SpellSE>();

        masterListCure.add(new SpellSE("Mobilize", 1, 5, true, 0, 0));
        masterListCure.add(new SpellSE("Cool Down", 5, 5, true, 0, 1));
        masterListCure.add(new SpellSE("Anti-Venom", 5, 5, true, 0, 2));
        masterListCure.add(new SpellSE("Awaken", 5, 5, true, 0, 3));

        String selection;

        do {
            System.out.println(" - Choose the party member whose job you want to change. - ");
            System.out.println("  0 for " + party.get(0).getName() + " who has " + party.get(0).getCurrJobName() + " as current job.");
            System.out.println("  1 for " + party.get(1).getName() + " who has " + party.get(1).getCurrJobName() + " as current job.");
            System.out.println("  2 for " + party.get(2).getName() + " who has " + party.get(2).getCurrJobName() + " as current job.");
            System.out.println("  3 for " + party.get(3).getName() + " who has " + party.get(3).getCurrJobName() + " as current job.");
            selection = scnr.nextLine();

            System.out.println();

        } while ((!selection.equals("1")) && (!selection.equals("2")) && (!selection.equals("3")) && (!selection.equals("0")));

        // Convert String to int
        int m = Integer.parseInt(selection);

        do {
            System.out.println("Select the new job: 0 (1H-Knight), 1 (2H-Knight), 2 (Throw), 3 (Archer), 4 (White Mage), 5 (Black Mage), or 6 (Red Mage) ");
            selection = scnr.nextLine();

            System.out.println();

        } while ((!selection.equals("1")) && (!selection.equals("2")) && (!selection.equals("3"))&&
                (!selection.equals("4")) && (!selection.equals("5")) && (!selection.equals("6")) && (!selection.equals("0")));

        // Convert String to int
        int j = Integer.parseInt(selection);

        party.get(m).changeJob(j, masterListAtk, masterListHeal, masterListDec, masterListInc, masterListInf,
                masterListCure);

        System.out.println("");
        System.out.println(party.get(m).getName() + " is now a " + party.get(m).getCurrJobName() + ".");
        System.out.println("All of " + party.get(m).getName() + "'s armor and weapons have been de-equipped.");
        System.out.println("");
    }

    public static Inventory useItem(Scanner scnr, ArrayList<PartyMember> party, Inventory inv) {
        System.out.println("Type 1 to use small healing potion.");
        System.out.println("     2 to use medium healing potion.");
        System.out.println("     3 to use large healing potion.");
        System.out.println("     4 to use small attack potion.");
        System.out.println("     5 to use medium attack potion.");
        System.out.println("     6 to use large attack potion.");
        System.out.println("     7 to use small defense potion.");
        System.out.println("     8 to use medium defense potion.");
        System.out.println("     9 to use large defense potion.");

        String input = scnr.nextLine();
        switch (input) {
            case "1":
                party.forEach(inv::useSmallHealOnPM);
                System.out.println("Used small healing potion");
                break;
            case "2":
                party.forEach(inv::useMediumHealOnPM);
                System.out.println("Used medium healing potion");
                break;
            case "3":
                party.forEach(inv::useLargeHealOnPM);
                System.out.println("Used large healing potion");
                break;
            case "4":
                party.forEach(inv::useSmallAttackOnPM);
                System.out.println("Used small attack potion");
                break;
            case "5":
                party.forEach(inv::useMediumAttackOnPM);
                System.out.println("Used medium attack potion");
                break;
            case "6":
                party.forEach(inv::useLargeAttackOnPM);
                System.out.println("Used large attack potion");
                break;
            case "7":
                party.forEach(inv::useSmallDefenseOnPM);
                System.out.println("Used small defense potion");
                break;
            case "8":
                party.forEach(inv::useMediumDefenseOnPM);
                System.out.println("Used medium defense potion");
                break;
            case "9":
                party.forEach(inv::useLargeDefenseOnPM);
                System.out.println("Used large defense potion");
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }

        System.out.println("");

        return inv;
    }

    public static void useSpell(Scanner scnr, ArrayList<PartyMember> party) {
        String selection;

        do {
            System.out.println(" - Choose the party member who you want to cast a spell. - ");
            System.out.println("  0 for " + party.get(0).getName() + " who is a " + party.get(0).getCurrJobName() + " and has " + party.get(0).getCurrMP() + " MP.");
            System.out.println("  1 for " + party.get(1).getName() + " who is a " + party.get(1).getCurrJobName() + " and has " + party.get(1).getCurrMP() + " MP.");
            System.out.println("  2 for " + party.get(2).getName() + " who is a " + party.get(2).getCurrJobName() + " and has " + party.get(2).getCurrMP() + " MP.");
            System.out.println("  3 for " + party.get(3).getName() + " who is a " + party.get(3).getCurrJobName() + " and has " + party.get(3).getCurrMP() + " MP.");

            selection = scnr.nextLine();

            System.out.println();

        } while ((!selection.equals("1")) && (!selection.equals("2")) && (!selection.equals("3")) && (!selection.equals("0")));

        int u = Integer.parseInt(selection);

        do {
            System.out.println(" - Choose the party member who you want to cast the spell on. - ");
            System.out.println("  0 for " + party.get(0).getName() + " who has " + party.get(0).getCurrHP() + " HP.");
            System.out.println("  1 for " + party.get(1).getName() + " who has " + party.get(1).getCurrHP() + " HP.");
            System.out.println("  2 for " + party.get(2).getName() + " who has " + party.get(2).getCurrHP() + " HP.");
            System.out.println("  3 for " + party.get(3).getName() + " who has " + party.get(3).getCurrHP() + " HP.");

            selection = scnr.nextLine();

            System.out.println();

        } while ((!selection.equals("1")) && (!selection.equals("2")) && (!selection.equals("3")) && (!selection.equals("0")));

        int t = Integer.parseInt(selection);

        do {
            System.out.println("Do you want to heal them (type 0) or buff them (type 1)?");

            selection = scnr.nextLine();

            System.out.println();

        } while ((!selection.equals("0")) && (!selection.equals("1")));

        int purpose = Integer.parseInt(selection);

        int mageType = party.get(u).getCurrJob();
        SpellBook sb;

        if ((mageType == 4) || (mageType == 6)) {
            switch (mageType) {
                case 4:
                    sb = party.get(u).getWhiteMage().getSpellBook();
                    break;
                case 6:
                    sb = party.get(u).getRedMage().getSpellBook();
                    break;
                default:
                    sb = party.get(u).getWhiteMage().getSpellBook();
                    System.out.println("Debug: ERROR!");
                    break;
            }

            int index = -1;
            switch (purpose) {
                case 0:
                    System.out.println("Choose from this list of spells (index starts at 0):");
                    System.out.println(sb.getListHeal());
                    do {
                        index = scnr.nextInt();
                    } while ((index < 0) || (index > (sb.getListHeal().size() - 1)));

                    SpellAoH temp = sb.getListHeal().get(index);

                    if (temp.getCost() < party.get(u).getCurrMP()) {
                        temp.castOnPM(party.get(t));
                        party.get(u).setCurrMP(party.get(u).getCurrMP() - temp.getCost());
                        System.out.println("Healed " + party.get(t).getName() + " for " + temp.getDamage() + " HP!");
                    }
                    else {
                        System.out.println(party.get(u).getName() + " does not have enough MP!");
                    }
                    break;
                case 1:
                    System.out.println("Choose from this list of spells (index starts at 0):");
                    System.out.println(sb.getListInc());
                    do {
                        index = scnr.nextInt();
                    } while ((index < 0) || (index > (sb.getListInc().size() - 1)));

                    SpellBuff buff = sb.getListInc().get(index);
                    if (buff.getCost() < party.get(u).getCurrMP()) {
                        buff.castOnPM(party.get(t));
                        party.get(u).setCurrMP(party.get(u).getCurrMP() - buff.getCost());
                        System.out.println("Increased a stat of " + party.get(t).getName() + " by " + buff.getBump() + "!");
                    }
                    else {
                        System.out.println(party.get(u).getName() + " does not have enough MP!");
                    }
                    break;
                default:
                    System.out.println("Unknown error; couldn't continue.");
                    break;
            }
        }
        else {
            System.out.println("That party member can't cast healing/buff spells!");
        }

        System.out.println("");

    }

    public static void maxCheck(ArrayList<PartyMember> party) {
        for(PartyMember member : party) {
            if ((member.getDead()) && (member.getCurrHP() > 0)) {
                member.setCurrHP(0);
            }
            if (member.getCurrHP() > member.getMaxHP()) {
                member.setCurrHP(member.getMaxHP());
            }
            if (member.getCurrMP() > member.getMaxMP()) {
                member.setCurrMP(member.getMaxMP());
            }
        }
    }

    public static void playGame(Scanner scnr, Grid grid, int pos, ArrayList<PartyMember> party, Inventory inv) {
        int position = pos;
        boolean activeGame = true;
        boolean moved = false;
        String choice;

        while (activeGame) {
            maxCheck(party);
            System.out.println("The party is currently on position " + position + ".");
            System.out.println("");
            grid.displayGrid();
            System.out.println("");
            if (moved) {
                inv = grid.getGridSquare(position - 1).action(party, inv);
                activeGame = inv.getGo();
                levelUpCheck(party);
                moved = false;
            }
            if (activeGame) {
                choice = inGameMenu(scnr);
                switch (choice) {
                    case "e":
                        equipChange(scnr, party, inv);
                        moved = false;
                        break;
                    case "i":
                        inv = useItem(scnr, party, inv);
                        moved = false;
                        break;
                    case "j":
                        jobChange(scnr, party);
                        moved = false;
                        break;
                    case "m":
                        position = movePos(scnr, position);
                        moved = true;
                        break;
                    case "s":
                        useSpell(scnr, party);
                        moved = false;
                        break;
                    case "p":
                        displayStats(party);
                        break;
                    case "q":
                        saveGame(grid, party, inv);
                        activeGame = false;
                        moved = false;
                        break;
                    default:
                        moved = false;
                        System.out.println("Invalid input.");
                        break;
                }
            }

        }
    }

    public static void main(String[] args) {
        // write your code here

        Scanner scnr = new Scanner(System.in);

        mainMenu(scnr);


    }

}