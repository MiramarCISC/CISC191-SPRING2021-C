package edu.sdccd.cisc191.c;

public class Knight extends Job {

    private boolean Handedness;

    public void setHand(boolean hand) {
        Handedness = hand;
    }

    public boolean getHand() {
        return Handedness;
    }

    @Override
    public int expToLevel(int exp) {
        return 0;
    }

}
