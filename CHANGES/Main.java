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
            System.out.println();
            System.out.println("Menu:");
            System.out.println(" - Type a key to select a command - ");
            System.out.println("  E to change equipment.");
            System.out.println("  J to change jobs.");
            System.out.println("  I to use an item.");
            System.out.println("  S to use a spell.");
            System.out.println("  M to move one square on the map.");
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
        String selection;

        do {
        System.out.println(" - Choose the party member whose equipment you want to change. - ");
        System.out.println("  0 for " + party.get(0).getName() + " who has " + party.get(1).getCurrJobName() + " as current job.");
        System.out.println("  1 for " + party.get(1).getName() + " who has " + party.get(0).getCurrJobName() + " as current job.");
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
                    break;

                case 1:
                    weaponChange(inv, party, scnr, selection, selectedMember);
                    break;

                default:
                    System.out.println("NO EQUIPMENT PART SELECTED");
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
        System.out.println("This feature is not yet implemented.");
        // WIP
    }

    public static Inventory useItem(Scanner scnr, ArrayList<PartyMember> party, Inventory inv) {
        // WIP
        System.out.println("This feature is not yet implemented.");
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
