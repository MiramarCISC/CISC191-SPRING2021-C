package edu.sdccd.cisc191.c;

public class CharacterEquipment {

    /** Blank CharacterEquipment() constructor */
    public CharacterEquipment() {
        this.swd1 = null;
        this.swd2 = null;
        this.stf1 = null;
        this.stf2 = null;
        this.bow = null;
        this.trw = null;
        this.head = null;
        this.body = null;
        this.legs = null;
        this.feet = null;
        this.shield = null;
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

    public void setSwd1(Sword sw1Choice) {
        swd1 = sw1Choice;
    }

    public void setSwd2(Sword sw2Choice) {
        swd2 = sw2Choice;
    }

    public void setStf1(Staff st1Choice) { stf1 = st1Choice;}

    public void setStf2(Staff st2Choice) { stf2 = st2Choice;}

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

    /** This method assumes the main slot for sword and staff are swd1 and stf1 */
    public void showEquipment() {
        if (head != null || body != null || legs != null || feet != null || swd1 != null || swd2 != null || stf1 != null
        || stf2 != null || bow != null || trw != null || shield != null) {
            System.out.print("Armor: ");
            if (head != null) {
                System.out.print("Head Armor: " + head.getName() + ", ");
            }
            if (body != null) {
                System.out.println("Body Armor: " + body.getName() + ", ");
            }
            if (legs != null) {
                System.out.println("Legs Armor: " + legs.getName() + ", ");
            }
            if (feet != null) {
                System.out.println("Feet Armor: " + feet.getName());
            }
            if (swd1 != null) {
                System.out.println("Sword 1: " + swd1.getName());
            }
            if (swd2 != null) {
                System.out.println("Sword 2: " + swd2.getName());
            }
            if (stf1 != null) {
                System.out.println("Staff 1: " + stf1.getName());
            }
            if (stf2 != null) {
                System.out.println("Staff 2: " + stf2.getName());
            }
            if (bow != null) {
                System.out.println("Bow: " + bow.getName());
            }
            if (trw != null) {
                System.out.println("Throwable: " + trw.getName());
            }
            if (shield != null) {
                System.out.println("Shield: " + shield.getName());
            }
        }
        else {
            System.out.println("No equipment was equipped on this character.");
        }
    }


}