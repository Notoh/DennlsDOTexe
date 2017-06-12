package io.notoh.dennls.util;

/**
 * Created by alexa on 5/23/2017.
 */
public class TimeHelper {

    private long lastMS;

    public boolean isDelayComplete(long delay) {
        return getCurrentMs() - lastMS >= delay;
    }

    public long getCurrentMs() {
        return System.nanoTime() / 1000000L; //slightly more accurate than System#currentTimeMillis
    }

    public void setLastMS(long last) {
        this.lastMS = last;
    }

    public void setLastMS() {
        this.lastMS = System.currentTimeMillis();
    }

    public long convertToMS(int seconds) {
        return 1000 / seconds;
    }

    public void reset() {
        this.lastMS = getCurrentMs();
    }


}
