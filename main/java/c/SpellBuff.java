package c;

public class SpellBuff extends Spell{

    private int duration;
    private int stat;
    private int bump;

    public SpellBuff(String call, int mL, int cst, boolean BoW, int dur, int st, int bmp) {
        super(call, mL, cst, BoW);
        duration = dur;
        stat = st;
        bump = bmp;
    }

    @Override
    protected void cast(Battler participant) {

    }

}
