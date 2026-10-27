/**
 * Remote display that shows the current queue number in real time.
 * It is not a help desk station, but it reads the same shared QueueManager.
 */
public class OnlineMonitor implements Runnable {
    private final QueueManager queueManager;
    private final long refreshMillis;

    private volatile boolean running = true;
    private int lastShown = -1;

    public OnlineMonitor(long refreshMillis) {
        this.queueManager = QueueManager.getInstance();
        this.refreshMillis = refreshMillis;
    }

    @Override
    public void run() {
        while (running) {
            int current = queueManager.getCurrentNumber();
            if (current != lastShown) {
                System.out.println("  [Online Monitoring] Current queue number: " + current);
                lastShown = current;
            }
            try {
                Thread.sleep(refreshMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void stop() {
        running = false;
    }
}
