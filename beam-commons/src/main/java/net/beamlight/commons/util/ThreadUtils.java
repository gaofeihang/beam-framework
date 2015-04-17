package net.beamlight.commons.util;

/**
 * Created on Feb 5, 2015
 * 
 * @author gaofeihang
 * @since 1.0.0
 */
public class ThreadUtils {
    
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
