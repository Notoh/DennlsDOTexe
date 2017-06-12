package io.notoh.dennls.util;

/**
 * Created by alexa on 5/21/2017.
 */
public interface CanRender {

    default void render(float red, float green, float blue, double x, double y, double z) {
        //do nothing, if the child wants to implement it, it can
    }

    default void render(float red, float green, float blue, double x, double y, double z, float width, float height) {
        //do nothing, if the child wants to implement it, it can
    }
}
