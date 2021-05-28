package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    /*
    public static void Battle(Party theParty) {

    }
    */

    public static void mainMenu(Scanner scnr) {
        boolean run = true;
        String input = "";

        while (run) {
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

        masterListHeal.add(new SpellAoH("Small Heal", 3, 3, true, 0, 12));
        masterListHeal.add(new SpellAoH("Moderate Heal", 5, 7, true, 0, 24));
        masterListHeal.add(new SpellAoH("Large Heal", 9, 11, true, 0, 36));
        masterListHeal.add(new SpellAoH("Half Heal", 17, 15, true, 0, 50));
        masterListHeal.add(new SpellAoH("Full Heal", 20, 25, true, 0, 100));

        ArrayList<SpellBuff> masterListInc = new ArrayList<SpellBuff>();

        masterListInc.add(new SpellBuff("Fortify", 7, 7, true, 2, 0, 10));
        masterListInc.add(new SpellBuff("Strengthen", 7, 7, true, 2, 1, 10));
        masterListInc.add(new SpellBuff("Haste", 7, 7, true, 2, 2, 10));

        ArrayList<SpellBuff> masterListDec = new ArrayList<SpellBuff>();

        masterListDec.add(new SpellBuff("Disrupt", 10, 7, false, 2, 0, 10));
        masterListDec.add(new SpellBuff("Weaken", 10, 7, false, 2, 1, 10));
        masterListDec.add(new SpellBuff("Slow", 10, 7, false, 2, 2, 10));

        ArrayList<SpellSE> masterListInf = new ArrayList<SpellSE>();

        masterListInf.add(new SpellSE("Paralyze", 5, 5, false, 2, 0));
        masterListInf.add(new SpellSE("Burn", 5, 5, false, 2, 1));
        masterListInf.add(new SpellSE("Poison", 5, 5, false, 2, 2));
        masterListInf.add(new SpellSE("Lull", 5, 5, false, 2, 3));

        ArrayList<SpellSE> masterListCure = new ArrayList<SpellSE>();

        masterListCure.add(new SpellSE("Mobilize", 5, 5, true, 0, 0));
        masterListCure.add(new SpellSE("Cool Down", 5, 5, true, 0, 1));
        masterListCure.add(new SpellSE("Anti-Venom", 5, 5, true, 0, 2));
        masterListCure.add(new SpellSE("Awaken", 5, 5, true, 0, 3));

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> jobs = new ArrayList<Integer>();
        ArrayList<PartyMember> party = new ArrayList<PartyMember>();

        for (int i = 0; i < 4; ++i) {
            System.out.println("Enter the name of Party Member #" + (i + 1) + ":");
            names.add(scnr.nextLine());

            System.out.println("Job values: 0 (1H-Knight), 1 (2H-Knight), 2 (Throw), 3 (Archer), 4 (White Mage), 5 (Black Mage), or 6 (Red Mage)");
            System.out.println("Enter their job value: ");
            jobs.add(scnr.nextInt());
            scnr.nextLine();

            PartyMember temp = new PartyMember(names.get(i), 1, 0, 1, false, jobs.get(i), 100);
            party.add(temp);
            party.get(i).calculateStats(masterListAtk, masterListHeal, masterListDec, masterListInc, masterListInf,
                    masterListCure);
        }

        Inventory newInv = new Inventory(5, 3, 1, 5, 3, 1, 5, 3, 1);

        Grid newGrid = new Grid();

        playGame(scnr, newGrid, party, newInv);

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

        masterListHeal.add(new SpellAoH("Small Heal", 3, 3, true, 0, 12));
        masterListHeal.add(new SpellAoH("Moderate Heal", 5, 7, true, 0, 24));
        masterListHeal.add(new SpellAoH("Large Heal", 9, 11, true, 0, 36));
        masterListHeal.add(new SpellAoH("Half Heal", 17, 15, true, 0, 50));
        masterListHeal.add(new SpellAoH("Full Heal", 20, 25, true, 0, 100));

        ArrayList<SpellBuff> masterListInc = new ArrayList<SpellBuff>();

        masterListInc.add(new SpellBuff("Fortify", 7, 7, true, 2, 0, 10));
        masterListInc.add(new SpellBuff("Strengthen", 7, 7, true, 2, 1, 10));
        masterListInc.add(new SpellBuff("Haste", 7, 7, true, 2, 2, 10));

        ArrayList<SpellBuff> masterListDec = new ArrayList<SpellBuff>();

        masterListDec.add(new SpellBuff("Disrupt", 10, 7, false, 2, 0, 10));
        masterListDec.add(new SpellBuff("Weaken", 10, 7, false, 2, 1, 10));
        masterListDec.add(new SpellBuff("Slow", 10, 7, false, 2, 2, 10));

        ArrayList<SpellSE> masterListInf = new ArrayList<SpellSE>();

        masterListInf.add(new SpellSE("Paralyze", 5, 5, false, 2, 0));
        masterListInf.add(new SpellSE("Burn", 5, 5, false, 2, 1));
        masterListInf.add(new SpellSE("Poison", 5, 5, false, 2, 2));
        masterListInf.add(new SpellSE("Lull", 5, 5, false, 2, 3));

        ArrayList<SpellSE> masterListCure = new ArrayList<SpellSE>();

        masterListCure.add(new SpellSE("Mobilize", 5, 5, true, 0, 0));
        masterListCure.add(new SpellSE("Cool Down", 5, 5, true, 0, 1));
        masterListCure.add(new SpellSE("Anti-Venom", 5, 5, true, 0, 2));
        masterListCure.add(new SpellSE("Awaken", 5, 5, true, 0, 3));

        System.out.print("Not yet implemented.");

    }

    public static void saveGame() {

    }

    public static String inGameMenu(Scanner scnr) {
        String letter = "";

        while ((!letter.equals("e")) && (!letter.equals("i")) && (!letter.equals("j")) && (!letter.equals("m")) && (!letter.equals("s"))) {
            System.out.println("Menu:");
            System.out.println("Type E to change equipment. Type j to change jobs.");
            System.out.println("Type I to use an item. Type S to use a spell.");
            System.out.println("Type M to move one square on the map.");
            letter = scnr.nextLine().toLowerCase();
        }

        return letter;
    }

    public static byte movePos(Scanner scnr, byte pos) {
        // WIP
        System.out.println("This feature is not yet implemented.");
        return 1;
    }

    public static void equipChange(Scanner scnr, ArrayList<PartyMember> party, Inventory inv) {
        System.out.println("This feature is not yet implemented.");
        // WIP
    }

    public static void jobChange(Scanner scnr, ArrayList<PartyMember> party) {
        System.out.println("This feature is not yet implemented.");
        // WIP
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

        return inv;
    }

    public static void useSpell(Scanner scnr, ArrayList<PartyMember> party) {
        System.out.println("This feature is not yet implemented.");
        // WIP
    }

    public static void playGame(Scanner scnr, Grid grid, ArrayList<PartyMember> party, Inventory inv) {
        byte position = 45;
        boolean activeGame = true;
        boolean moved = false;
        String choice;

        while (activeGame) {
            System.out.println("The party is currently on position " + position + ".");
            grid.displayGrid();
            if (moved) {
                inv = grid.getGridSquare(position).action(party, inv);
                activeGame = inv.getGo();
                moved = false;
            }
            if (activeGame) {
                choice = inGameMenu(scnr);
                switch (choice) {
                    case "e":
                        equipChange(scnr, party, inv);
                        moved = false;
                        //break;
                    case "i":
                        inv = useItem(scnr, party, inv);
                        moved = false;
                        //break;
                    case "j":
                        jobChange(scnr, party);
                        moved = false;
                        //break;
                    case "m":
                        position = movePos(scnr, position);
                        moved = true;
                        //break;
                    case "s":
                        useSpell(scnr, party);
                        moved = false;
                        //break;
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