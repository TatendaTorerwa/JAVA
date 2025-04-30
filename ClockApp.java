import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A shared class to store and retrieve the current date and time.
 * It ensures thread-safe access using synchronized methods.
 * 
 * @author Tatenda
 */
class ClockData {

    /** Stores the current date and time. */
    private Date currentTime;

    /**
     * Updates the current time to the current system time.
     */
    public synchronized void updateTime() {
        currentTime = new Date();
    }

    /**
     * Retrieves the current time as a formatted string.
     * 
     * @return the current time in the format "HH:mm:ss dd-MM-yyyy"
     */
    public synchronized String getCurrentTime() {
        if (currentTime == null) {
            return "Time not set.";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return formatter.format(currentTime);
    }
}

/**
 * A thread that updates the ClockData with the current system time every 0.5 seconds.
 * Lower thread priority to simulate background updating.
 * 
 * @author Tatenda
 */
class TimeUpdater extends Thread {

    private final ClockData clockData;

    /**
     * Constructs the TimeUpdater thread.
     * 
     * @param clockData the shared ClockData object to update
     */
    public TimeUpdater(ClockData clockData) {
        this.clockData = clockData;
        setName("TimeUpdaterThread");
        setPriority(MIN_PRIORITY);  // Background thread
    }

    /**
     * Runs the update loop.
     */
    @Override
    public void run() {
        while (true) {
            clockData.updateTime();
            try {
                Thread.sleep(500); // 0.5 seconds
            } catch (InterruptedException e) {
                System.err.println(getName() + " interrupted.");
                break;
            }
        }
    }
}

/**
 * A thread that prints the current time to the console every second.
 * Given higher priority to ensure timely display.
 * 
 * @author Tatenda
 */
class TimeDisplayer extends Thread {

    private final ClockData clockData;

    /**
     * Constructs the TimeDisplayer thread.
     * 
     * @param clockData the shared ClockData object to read from
     */
    public TimeDisplayer(ClockData clockData) {
        this.clockData = clockData;
        setName("TimeDisplayerThread");
        setPriority(MAX_PRIORITY);  // High priority for better precision
    }

    /**
     * Runs the display loop.
     */
    @Override
    public void run() {
        while (true) {
            System.out.println("Current Time: " + clockData.getCurrentTime());
            try {
                Thread.sleep(1000); // 1 second
            } catch (InterruptedException e) {
                System.err.println(getName() + " interrupted.");
                break;
            }
        }
    }
}

/**
 * The main application class that starts both threads and runs the clock.
 * Demonstrates the use of Java threads, synchronization, and priorities.
 * 
 * @author Tatenda
 */
public class ClockApp {

    /**
     * Entry point of the program.
     * Initializes the shared clock and starts the threads.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ClockData clockData = new ClockData();

        TimeUpdater updater = new TimeUpdater(clockData);
        TimeDisplayer displayer = new TimeDisplayer(clockData);

        updater.start();
        displayer.start();
    }
}
