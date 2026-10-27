/**
 * Client class that simulates one day at the Pag-ibig office: three help desk
 * stations serving visitors from one centralized queue while the online
 * monitoring system shows the current number.
 */
public class PagIbigApp {
    public static void main(String[] args) throws InterruptedException {
        HelpDeskStation station1 = new HelpDeskStation("Help Desk 1");
        HelpDeskStation station2 = new HelpDeskStation("Help Desk 2");
        HelpDeskStation station3 = new HelpDeskStation("Help Desk 3");

        // Online monitoring display running in the background.
        OnlineMonitor monitor = new OnlineMonitor(120);
        Thread monitorThread = new Thread(monitor, "online-monitor");
        monitorThread.setDaemon(true);
        monitorThread.start();

        System.out.println("=== Pag-ibig Office: centralized queuing system ===\n");

        System.out.println("--- Visitors are served at the three stations ---");
        station1.serveNextCustomer();
        Thread.sleep(200);
        station2.serveNextCustomer();
        Thread.sleep(200);
        station3.serveNextCustomer();
        Thread.sleep(200);
        station1.serveNextCustomer();
        Thread.sleep(200);
        station2.serveNextCustomer();
        Thread.sleep(300);

        System.out.println("\n--- Reorganization: Help Desk 2 resets the queue to 500 ---");
        station2.resetQueue(500);
        Thread.sleep(200);
        station3.serveNextCustomer();
        Thread.sleep(200);
        station1.serveNextCustomer();
        Thread.sleep(300);

        System.out.println("\n--- Proof that there is only one queue manager ---");
        QueueManager fromApp = QueueManager.getInstance();
        QueueManager fromMonitor = QueueManager.getInstance();
        System.out.println("Same object for the app and the online monitor? " + (fromApp == fromMonitor));
        System.out.println("Current queue number seen by everyone: " + fromApp.getCurrentNumber());

        monitor.stop();
        monitorThread.join(1000);

        System.out.println("\nOffice closed. Final queue number: " + QueueManager.getInstance().getCurrentNumber());
    }
}
