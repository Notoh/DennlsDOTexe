package io.notoh.dennls.util;

import io.notoh.dennls.ClientEntry;
import io.notoh.dennls.mods.Mode;

/**
 * Created by alexa on 5/22/2017.
 */
public final class ModeUtils {

    public static Mode getNext() {
        Mode active = ClientEntry.getActive();
        switch(active) {
            case GHOST: return Mode.BYPASS;
            case BLATANT: return Mode.GHOST;
            case BYPASS: return Mode.BLATANT;
            default: return Mode.BYPASS;
        }
    }

}
