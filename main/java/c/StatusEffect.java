package c;

public abstract class StatusEffect {

    protected int duration;
    protected int Strength;
    protected String effectName;

    protected abstract void inflictEffect();
    protected abstract void removeEffect();

}
