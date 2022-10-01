package model;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Consumer extends Thread {
    private BlockingQueue<Client> queue;
    private boolean runningThread;


    public Consumer(boolean runningThread) {
        this.queue = new LinkedBlockingDeque<>();
        this.runningThread = runningThread;
    }

    public void printQueues() {
        if (!queue.isEmpty())
            for (Client c : queue)
                System.out.print(c + " ");
        else
            System.out.print(" _empty_queue_ ");
    }

    @Override
    public void run() {
        while (runningThread == true) {

            if (!queue.isEmpty()) {
                Client client = queue.element();
                if (client.getTimeService() > 1)
                    client.setTimeService(client.getTimeService() - 1);
                else {
                    try {
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public BlockingQueue<Client> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<Client> queue) {
        this.queue = queue;
    }

    public boolean isRunningThread() {
        return runningThread;
    }

    public void setRunningThread(boolean runningThread) {
        this.runningThread = runningThread;
    }

    public String toString() {
        for (Client c : queue)
            return c + " ";
        return null;
    }
}
