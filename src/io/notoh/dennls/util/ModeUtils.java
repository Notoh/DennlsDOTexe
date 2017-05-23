package io.notoh.dennls.util;

import io.notoh.dennls.Dennls;
import io.notoh.dennls.mods.Mode;

/**
 * Created by alexa on 5/22/2017.
 */
public final class ModeUtils {

    public static Mode getNext() {
        Mode active = Dennls.getActive();
        switch(active) {
            case GHOST: return Mode.SAFE;
            case BLATANT: return Mode.GHOST;
            case SAFE: return Mode.BLATANT;
            default: return Mode.SAFE;
        }
    }

}
