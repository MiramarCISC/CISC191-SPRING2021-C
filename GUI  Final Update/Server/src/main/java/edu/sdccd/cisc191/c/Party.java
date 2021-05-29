package edu.sdccd.cisc191.c;

import java.util.ArrayList;

public class Party {

    private ArrayList<PartyMember> members = new ArrayList<>();

    public void setPartyMember(PartyMember member) {
        this.members.add(member);
    }

    public void showPartyMembers() {
        System.out.println("**Current Party:  ***************");
        int i = 0;
        for(PartyMember member: members) {
            System.out.println("////////////////////////////// #" + ++i);
            System.out.println("Name : " + member.getName()    + "\n" +
                    "Level: " + member.getLevel()   + "\n" +
                    "JobID: " + member.getCurrJob() + "\n" +
                    " - - - - - - - - - - - - - - - - ");
            System.out.printf( "Atk: %-7d", member.getAtkStat());
            System.out.printf( "Def: %-7d", member.getDefStat());
            System.out.printf( "Spd: %-7d", member.getSpdStat());
            System.out.println();
            System.out.printf( "HP : %-7d", member.getMaxHP()  );
            System.out.printf( "Exp: %-7d", member.getExp()    );
            System.out.println();
        }
        System.out.println("*********************************");
    }

    public ArrayList<PartyMember> getPartyMembers() {
        return members;
        //for (PartyMember m: members);

    }

}