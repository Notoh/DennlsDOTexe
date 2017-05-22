package io.notoh.dennls.mods;

/**
 * Corresponds to the
 */
public enum Mode {

    GHOST(3.05f),BLATANT(10f),BYPASS(3.15f);


    private float reach;
    private Mode(float reach) {
        this.reach = reach;
    }

    public float getReach() {
        return reach;
    }

}
