/**
 * Singleton that owns the one and only queue number used by every help desk
 * station and by the online monitoring system in the Pag-ibig office.
 */
public class QueueManager {
    private static QueueManager instance;

    private int currentNumber;

    private QueueManager() {
        this.currentNumber = 0;
    }

    /**
     * Returns the single shared instance, creating it on first use.
     * The method is synchronized so that two threads cannot create
     * two different managers at the same time.
     */
    public static synchronized QueueManager getInstance() {
        if (instance == null) {
            instance = new QueueManager();
        }
        return instance;
    }

    /** Issues the next queue number and returns it. */
    public synchronized int issueNumber() {
        currentNumber++;
        return currentNumber;
    }

    /** Reads the number shown on the online monitoring system. */
    public synchronized int getCurrentNumber() {
        return currentNumber;
    }

    /**
     * Lets a help desk station restart the queue at a given number,
     * e.g. when the office opens a new batch of transactions or after
     * a technical problem.
     */
    public synchronized void resetNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Queue number cannot be negative.");
        }
        currentNumber = number;
    }
}
