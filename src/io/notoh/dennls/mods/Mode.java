package io.notoh.dennls.mods;

/**
 * Corresponds to the
 */
public enum Mode {

    GHOST(3.05f,1.2,32.0),BLATANT(10f,1.42,360.0), SAFE(3.15f,1.33,90.0);


    private float reach;
    private double speed;
    private double fov;

    Mode(float reach, double speed, double fov) {
        this.reach = reach;
        this.speed = speed;
        this.fov = fov;
    }

    public float getReach() {
        return reach;
    }

    public double getSpeed() {
        return speed;
    }

    public double getFov() {
        return fov;
    }

}
