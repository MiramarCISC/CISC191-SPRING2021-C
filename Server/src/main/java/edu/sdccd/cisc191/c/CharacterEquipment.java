package edu.sdccd.cisc191.c;

public class CharacterEquipment {

    public CharacterEquipment() {
    }

    private Sword swd1;
    private Sword swd2;
    private Staff stf1;
    private Staff stf2;
    private Bow bow;
    private Throwable trw;
    private Head head;
    private Body body;
    private Legs legs;
    private Feet feet;
    private Shield shield;

    public void setSw1(Sword sw1Choice) {
        swd1 = sw1Choice;
    }

    public void setSw2(Sword sw2Choice) {
        swd2 = sw2Choice;
    }

    public void setSt1(Staff st1Choice) { stf1 = st1Choice;}

    public void setSt2(Staff st2Choice) { stf2 = st2Choice;}

    public void setBow(Bow bowChoice) { bow = bowChoice;}

    public void setTrw(Throwable trwChoice) { trw = trwChoice;}

    public void setHead(Head headChoice) {
        head = headChoice;
    }

    public void setBody(Body bodyChoice) {
        body = bodyChoice;
    }

    public void setLegs(Legs legsChoice) {
        legs = legsChoice;
    }

    public void setFeet(Feet feetChoice) {
        feet = feetChoice;
    }

    public void setShield(Shield shChoice) {
        shield = shChoice;
    }

    public Sword getSwd1() { return swd1; }

    public Sword getSwd2() { return swd2; }

    public Staff getStf1() { return stf1; }

    public Staff getStf2() { return stf2; }

    public Bow getBow() { return bow; }

    public Throwable getTrw() { return trw; }

    public Head getHead() { return head; }

    public Body getBody() { return body; }

    public Legs getLegs() { return legs; }

    public Feet getFeet() { return feet; }

    public Shield getShield() { return shield; }

}