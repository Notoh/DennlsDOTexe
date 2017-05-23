package io.notoh.dennls.mods;

/**
 * Corresponds to the
 */
public enum Mode {

    GHOST(3.05f,1.2),BLATANT(10f,1.42), SAFE(3.15f,1.33);


    private float reach;
    private double speed;
    Mode(float reach, double speed) {
        this.reach = reach;
        this.speed = speed;
    }

    public float getReach() {
        return reach;
    }

    public double getSpeed() {
        return speed;
    }
}
