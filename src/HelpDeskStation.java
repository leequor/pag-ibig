/**
 * One of the three help desk stations. Every station talks to the same
 * QueueManager instance, so the numbers they issue come from one queue.
 */
public class HelpDeskStation {
    private final String stationName;
    private final QueueManager queueManager;

    public HelpDeskStation(String stationName) {
        this.stationName = stationName;
        this.queueManager = QueueManager.getInstance();
    }

    /** Serves the next visitor by taking the next number from the shared queue. */
    public void serveNextCustomer() {
        int number = queueManager.issueNumber();
        System.out.println("[" + stationName + "] Now serving queue number " + number + ".");
    }

    /** Resets the shared queue number, e.g. to continue from 500 after a batch. */
    public void resetQueue(int number) {
        queueManager.resetNumber(number);
        System.out.println("[" + stationName + "] Queue reset. Next number to serve is "
                + queueManager.getCurrentNumber() + ".");
    }

    public String getStationName() {
        return stationName;
    }
}
