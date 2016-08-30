package com.advance.android.sdk.trace;

import java.util.concurrent.TimeUnit;

/**
 * Created by huangfu on 2016/8/30 16:32
 */
public class TimeWatcher {

    private long startTime;
    private long endTime;
    private long elapsedTime;

    public TimeWatcher() {
        super();
    }


    public void reset() {
        startTime = 0;
        endTime = 0;
        elapsedTime = 0;
    }

    public void star() {
        reset();
        startTime = System.nanoTime();
    }

    public void end() {
        if (startTime != 0) {
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
        }else {
            reset();
        }
    }

    public long getTotalTimeMillis() {
        return (elapsedTime != 0) ? TimeUnit.NANOSECONDS.toMillis(endTime - startTime) : 0;
    }

}
