package Utilities;

/**
 * Created by Kraaijeveld on 31-5-2016.
 */

public class TimeMeasurer
{
    private static long startTime;
    private static long endTime;

    public static void startTimer()
    {
        System.out.println("TimeMeasurer: Started the timer.");
        startTime = System.currentTimeMillis();
    }

    public static void printElapsedTimeInSeconds()
    {
        endTime = System.currentTimeMillis();
        long elapsedTime = (endTime - startTime) / 1000;
        System.out.println("TimeMeasurer: Elapsed time: " + elapsedTime + " seconds.");
    }
}
