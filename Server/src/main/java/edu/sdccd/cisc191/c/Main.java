package edu.sdccd.cisc191.c;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.opencsv.bean.*;

import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import javax.print.attribute.IntegerSyntax;

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
            System.out.println("***WARNING: THE LOAD GAME FEATURE DOES NOT FUNCTION AND CAUSES GAME TO CRASH***");
            System.out.println("");
            System.out.println("Type N to start a new game, L to load a game, or Q to quit.");

            input = scnr.nextLine().toLowerCase(Locale.ROOT);

            switch (input) {
                case "n":
                    newGame(scnr);
                    break;
                case "l":
                    loadGame(scnr);
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

    public static void loadGame(Scanner scnr) {
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

        // Load from CSV
        ArrayList<DummyInteger> dInt = new ArrayList<DummyInteger>();
        List<DummyInteger> diList = new CsvToBeanBuilder<DummyInteger>(new InputStreamReader(Main.class.getResourceAsStream("/Integers.csv")))
                .withType(DummyInteger.class)
                .build()
                .parse();
        dInt.addAll(diList);
        System.out.println(dInt);

        Inventory newInv = new Inventory(dInt.get(0).getInteger(), dInt.get(1).getInteger(), dInt.get(2).getInteger(),
                dInt.get(3).getInteger(), dInt.get(4).getInteger(), dInt.get(5).getInteger(), dInt.get(6).getInteger(),
                dInt.get(7).getInteger(), dInt.get(8).getInteger());

        int pos = dInt.get(9).getInteger();

        ArrayList<PartyMember> party = new ArrayList<PartyMember>();
        List<PartyMember> pmList = new CsvToBeanBuilder<PartyMember>(new InputStreamReader(Main.class.getResourceAsStream("/PartyMembers.csv")))
                .withType(PartyMember.class)
                .build()
                .parse();
        party.addAll(pmList);
        for (int i = 0; i < 4; ++i) {
            party.get(i).calculateStats(masterListAtk, masterListHeal, masterListDec, masterListInc, masterListInf,
                    masterListCure);
            party.get(i).newStats();
        }

        ArrayList<Sword> swords = new ArrayList<Sword>();
        List<Sword> swordList = new CsvToBeanBuilder<Sword>(new InputStreamReader(Main.class.getResourceAsStream("/Swords.csv")))
                .withType(Sword.class)
                .build()
                .parse();
        swords.addAll(swordList);
        newInv.setSwords(swords);

        ArrayList<Bow> bows = new ArrayList<Bow>();
        List<Bow> bowList = new CsvToBeanBuilder<Bow>(new InputStreamReader(Main.class.getResourceAsStream("/Bows.csv")))
                .withType(Bow.class)
                .build()
                .parse();
        bows.addAll(bowList);
        newInv.setBows(bows);

        ArrayList<Staff> staffs = new ArrayList<Staff>();
        List<Staff> staffList = new CsvToBeanBuilder<Staff>(new InputStreamReader(Main.class.getResourceAsStream("/Staffs.csv")))
                .withType(Staff.class)
                .build()
                .parse();
        staffs.addAll(staffList);
        newInv.setStaffs(staffs);

        ArrayList<Throwable> throwables = new ArrayList<Throwable>();
        List<Throwable> throwableList = new CsvToBeanBuilder<Throwable>(new InputStreamReader(Main.class.getResourceAsStream("/Throws.csv")))
                .withType(Throwable.class)
                .build()
                .parse();
        throwables.addAll(throwableList);
        newInv.setThrowable(throwables);

        ArrayList<Head> heads = new ArrayList<Head>();
        List<Head> headList = new CsvToBeanBuilder<Head>(new InputStreamReader(Main.class.getResourceAsStream("/Heads.csv")))
                .withType(Head.class)
                .build()
                .parse();
        heads.addAll(headList);
        newInv.setHeads(heads);

        ArrayList<Body> bodies = new ArrayList<Body>();
        List<Body> bodyList = new CsvToBeanBuilder<Body>(new InputStreamReader(Main.class.getResourceAsStream("/Bodies.csv")))
                .withType(Body.class)
                .build()
                .parse();
        bodies.addAll(bodyList);
        newInv.setBodies(bodies);

        ArrayList<Legs> legs = new ArrayList<Legs>();
        List<Legs> legsList = new CsvToBeanBuilder<Legs>(new InputStreamReader(Main.class.getResourceAsStream("/Legs.csv")))
                .withType(Legs.class)
                .build()
                .parse();
        legs.addAll(legsList);
        newInv.setLegs(legs);

        ArrayList<Feet> feet = new ArrayList<Feet>();
        List<Feet> feetList = new CsvToBeanBuilder<Feet>(new InputStreamReader(Main.class.getResourceAsStream("/Feet.csv")))
                .withType(Feet.class)
                .build()
                .parse();
        feet.addAll(feetList);
        newInv.setFeet(feet);

        ArrayList<Shield> shields = new ArrayList<Shield>();
        List<Shield> shieldList = new CsvToBeanBuilder<Shield>(new InputStreamReader(Main.class.getResourceAsStream("/Shields.csv")))
                .withType(Shield.class)
                .build()
                .parse();
        shields.addAll(shieldList);
        newInv.setShields(shields);

        ArrayList<DummyString> dStr = new ArrayList<DummyString>();
        List<DummyString> dsList = new CsvToBeanBuilder<DummyString>(new InputStreamReader(Main.class.getResourceAsStream("/Strings.csv")))
                .withType(DummyString.class)
                .build()
                .parse();
        dStr.addAll(dsList);
        Grid grid = new Grid();
        for (int i = 0; i < 100; ++i) {
            String abb = dStr.get(i).getStr();
            switch (abb) {
                case "B":
                    grid.setGridSquare(i, new BossSquare());
                    break;
                case "I":
                    grid.setGridSquare(i, new InnSquare());
                    break;
                case "N":
                    grid.setGridSquare(i, new NormalSquare());
                    break;
                case "T":
                    grid.setGridSquare(i, new TreasureSquare());
                    break;
                default:
                    grid.setGridSquare(i, new NormalSquare());
                    break;
            }
        }

        System.out.print("The game has been loaded.");
        playGame(scnr, grid, pos, party, newInv);

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

    public static void saveGame(Grid grid, int pos, ArrayList<PartyMember> party, Inventory inv) {

        ArrayList<Integer> quantities = new ArrayList<Integer>();
        quantities.add(inv.getSmallHealingPotion().getQuantity());
        quantities.add(inv.getMediumHealingPotion().getQuantity());
        quantities.add(inv.getLargeHealingPotion().getQuantity());
        quantities.add(inv.getSmallAttackPotion().getQuantity());
        quantities.add(inv.getMediumAttackPotion().getQuantity());
        quantities.add(inv.getLargeAttackPotion().getQuantity());
        quantities.add(inv.getSmallDefensePotion().getQuantity());
        quantities.add(inv.getMediumDefensePotion().getQuantity());
        quantities.add(inv.getLargeDefensePotion().getQuantity());
        quantities.add(pos);
        ArrayList<DummyInteger> dInt = new ArrayList<DummyInteger>();
        quantities.forEach((i) -> dInt.add(new DummyInteger(i)));

        ArrayList<String> types = new ArrayList<String>();
        for (GridSquare square : grid.getGrid()) {
            types.add(square.getAbbreviation());
        }
        ArrayList<DummyString> dStr = new ArrayList<DummyString>();
        types.forEach((s) -> dStr.add(new DummyString(s)));

        try {
            FileWriter pmWriter = new FileWriter("Server/src/main/resources/PartyMembers.csv");
            HeaderColumnNameMappingStrategy pmStrategy = new HeaderColumnNameMappingStrategy();
            pmStrategy.setType(PartyMember.class);
            // pmStrategy.setColumnMapping("name", "level", "exp", "currHP", "currMP", "dead", "currJob", "maxXP");
            StatefulBeanToCsvBuilder<PartyMember> builder1 = new StatefulBeanToCsvBuilder(pmWriter);
            StatefulBeanToCsv beanWriter1 = builder1.withMappingStrategy(pmStrategy).build();
            beanWriter1.write(party);
            pmWriter.close();

            FileWriter swordWriter = new FileWriter("Server/src/main/resources/Swords.csv");
            HeaderColumnNameMappingStrategy swordStrategy = new HeaderColumnNameMappingStrategy();
            swordStrategy.setType(Sword.class);
            // swordStrategy.setColumnMapping("name", "attack", "rarity", "applyBleeding", "twoHanded");
            StatefulBeanToCsvBuilder<Sword> builder2 = new StatefulBeanToCsvBuilder(swordWriter);
            StatefulBeanToCsv beanWriter2 = builder2.withMappingStrategy(swordStrategy).build();
            beanWriter2.write(inv.getSwords());
            swordWriter.close();

            FileWriter bowWriter = new FileWriter("Server/src/main/resources/Bows.csv");
            HeaderColumnNameMappingStrategy bowStrategy = new HeaderColumnNameMappingStrategy();
            bowStrategy.setType(Bow.class);
            // bowStrategy.setColumnMapping("name", "attack", "rarity", "applyFire", "applyPoison");
            StatefulBeanToCsvBuilder<Bow> builder3 = new StatefulBeanToCsvBuilder(bowWriter);
            StatefulBeanToCsv beanWriter3 = builder3.withMappingStrategy(bowStrategy).build();
            beanWriter3.write(inv.getBows());
            bowWriter.close();

            FileWriter staffWriter = new FileWriter("Server/src/main/resources/Staffs.csv");
            HeaderColumnNameMappingStrategy staffStrategy = new HeaderColumnNameMappingStrategy();
            staffStrategy.setType(Staff.class);
            // staffStrategy.setColumnMapping("name", "attack", "rarity", "applyFire", "applyElec", "applyIce");
            StatefulBeanToCsvBuilder<Staff> builder4 = new StatefulBeanToCsvBuilder(staffWriter);
            StatefulBeanToCsv beanWriter4 = builder4.withMappingStrategy(staffStrategy).build();
            beanWriter4.write(inv.getStaffs());
            staffWriter.close();

            FileWriter throwWriter = new FileWriter("Server/src/main/resources/Throws.csv");
            HeaderColumnNameMappingStrategy throwStrategy = new HeaderColumnNameMappingStrategy();
            throwStrategy.setType(Throwable.class);
            // throwStrategy.setColumnMapping("name", "attack", "rarity", "applyPoison");
            StatefulBeanToCsvBuilder<Throwable> builder5 = new StatefulBeanToCsvBuilder(throwWriter);
            StatefulBeanToCsv beanWriter5 = builder5.withMappingStrategy(throwStrategy).build();
            beanWriter5.write(inv.getThrowable());
            throwWriter.close();

            FileWriter headWriter = new FileWriter("Server/src/main/resources/Heads.csv");
            HeaderColumnNameMappingStrategy headStrategy = new HeaderColumnNameMappingStrategy();
            headStrategy.setType(Head.class);
            // headStrategy.setColumnMapping("name", "defense", "rarity", "magical");
            StatefulBeanToCsvBuilder<Head> builder6 = new StatefulBeanToCsvBuilder(headWriter);
            StatefulBeanToCsv beanWriter6 = builder6.withMappingStrategy(headStrategy).build();
            beanWriter6.write(inv.getHeads());
            headWriter.close();

            FileWriter bodyWriter = new FileWriter("Server/src/main/resources/Bodies.csv");
            HeaderColumnNameMappingStrategy bodyStrategy = new HeaderColumnNameMappingStrategy();
            bodyStrategy.setType(Body.class);
            // bodyStrategy.setColumnMapping("name", "defense", "rarity", "magical");
            StatefulBeanToCsvBuilder<Body> builder7 = new StatefulBeanToCsvBuilder(bodyWriter);
            StatefulBeanToCsv beanWriter7 = builder7.withMappingStrategy(bodyStrategy).build();
            beanWriter7.write(inv.getBodies());
            bodyWriter.close();

            FileWriter legsWriter = new FileWriter("Server/src/main/resources/Legs.csv");
            HeaderColumnNameMappingStrategy legsStrategy = new HeaderColumnNameMappingStrategy();
            legsStrategy.setType(Legs.class);
            // legsStrategy.setColumnMapping("name", "defense", "rarity", "magical");
            StatefulBeanToCsvBuilder<Legs> builder8 = new StatefulBeanToCsvBuilder(legsWriter);
            StatefulBeanToCsv beanWriter8 = builder8.withMappingStrategy(legsStrategy).build();
            beanWriter8.write(inv.getLegs());
            legsWriter.close();

            FileWriter feetWriter = new FileWriter("Server/src/main/resources/Feet.csv");
            HeaderColumnNameMappingStrategy feetStrategy = new HeaderColumnNameMappingStrategy();
            feetStrategy.setType(Feet.class);
            // feetStrategy.setColumnMapping("name", "defense", "rarity", "magical");
            StatefulBeanToCsvBuilder<Feet> builder9 = new StatefulBeanToCsvBuilder(feetWriter);
            StatefulBeanToCsv beanWriter9 = builder9.withMappingStrategy(feetStrategy).build();
            beanWriter9.write(inv.getFeet());
            feetWriter.close();

            FileWriter shieldWriter = new FileWriter("Server/src/main/resources/Shields.csv");
            HeaderColumnNameMappingStrategy shieldStrategy = new HeaderColumnNameMappingStrategy();
            shieldStrategy.setType(Shield.class);
            // shieldStrategy.setColumnMapping("name", "dmgReduction", "defIncrease", "rarity", "dmgReduction", "defIncrease");
            StatefulBeanToCsvBuilder<Shield> builder10 = new StatefulBeanToCsvBuilder(shieldWriter);
            StatefulBeanToCsv beanWriter10 = builder10.withMappingStrategy(shieldStrategy).build();
            beanWriter10.write(inv.getShields());
            shieldWriter.close();

            FileWriter intWriter = new FileWriter("Server/src/main/resources/Integers.csv");
            HeaderColumnNameMappingStrategy intStrategy = new HeaderColumnNameMappingStrategy();
            intStrategy.setType(DummyInteger.class);
            // intStrategy.setColumnMapping("integer");
            StatefulBeanToCsvBuilder<DummyInteger> builder11 = new StatefulBeanToCsvBuilder(intWriter);
            StatefulBeanToCsv beanWriter11 = builder11.withMappingStrategy(intStrategy).build();
            beanWriter11.write(dInt);
            intWriter.close();

            FileWriter strWriter = new FileWriter("Server/src/main/resources/Strings.csv");
            HeaderColumnNameMappingStrategy strStrategy = new HeaderColumnNameMappingStrategy();
            strStrategy.setType(DummyString.class);
            // strStrategy.setColumnMapping("str");
            StatefulBeanToCsvBuilder<DummyString> builder12 = new StatefulBeanToCsvBuilder(strWriter);
            StatefulBeanToCsv beanWriter12 = builder12.withMappingStrategy(strStrategy).build();
            beanWriter12.write(dStr);
            strWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("The game has been saved.");
        System.out.println("");
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
                        saveGame(grid, position, party, inv);
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