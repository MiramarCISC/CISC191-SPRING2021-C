package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static org.apache.commons.lang3.ArrayUtils.contains;

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
        ArrayList<PartyMember> party = new ArrayList<PartyMember>();

        for (int i = 0; i < 4; ++i) {
            System.out.println("Enter the name of Party Member #" + (i + 1) + ":");
            names.add(scnr.nextLine());

            System.out.println("Job values: 0 (1H-Knight), 1 (2H-Knight), 2 (Throw), 3 (Archer), 4 (White Mage), 5 (Black Mage), or 6 (Red Mage)");
            System.out.println("Enter their job value: ");
            int jobType = scnr.nextInt();
            scnr.nextLine();

            PartyMember temp = new PartyMember(names.get(i), 1, 0, 1, false, jobType, 100);
            party.add(temp);
            party.get(i).calculateStats(masterListAtk, masterListHeal, masterListDec, masterListInc, masterListInf,
                    masterListCure);
            party.get(i).newStats();
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

        System.out.print("Loading a game is not yet implemented.");

    }

    public static void levelUpCheck(ArrayList<PartyMember> party) {
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
                    if(position > 10){
                        position -= 10;
                        finished = true;
                        break;
                    }

                    else{
                        System.out.println("You cannot move up.");
                        break;
                    }
                case "s":
                    if(position < 91){
                        position += 10;
                        finished = true;
                        break;
                    }

                    else{
                        System.out.println("You cannot move down.");
                        break;
                    }
                case "a":
                    if(!contains(new int[]{1, 11, 21, 31, 41, 51, 61, 71, 81, 91}, position)){
                        position -= 1;
                        finished = true;
                        break;
                    }

                    else{
                        System.out.println("You cannot move left.");
                        break;
                    }
                case "d":
                    if(position > 10 && position % 10 != 0){
                        position += 1;
                        finished = true;
                        break;
                    }

                    else{
                        System.out.println("You cannot move right.");
                        break;
                    }

                default:
                    System.out.println("Invalid input.");
                    break;
            }
        }
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
        System.out.println("Changing equipment is not yet implemented.");
        // WIP
    }

    public static void jobChange(Scanner scnr, ArrayList<PartyMember> party) {
        System.out.println("Changing jobs is not yet implemented.");
        // WIP
    }

    public static Inventory useItem(Scanner scnr, ArrayList<PartyMember> party, Inventory inv) {
        // WIP
        System.out.println("Using items is not yet implemented.");
        return inv;
    }

    public static void useSpell(Scanner scnr, ArrayList<PartyMember> party) {
        System.out.println("Using spells is not yet implemented.");
        // WIP
    }

    public static void playGame(Scanner scnr, Grid grid, ArrayList<PartyMember> party, Inventory inv) {
        int position = 45;
        boolean activeGame = true;
        boolean moved = false;
        String choice;

        while (activeGame) {
            System.out.println("The party is currently on position " + position + ".");
            System.out.println("");
            grid.displayGrid();
            System.out.println("");
            if (moved) {
                inv = grid.getGridSquare(position - 1).action(party, inv);
                activeGame = inv.getGo();
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