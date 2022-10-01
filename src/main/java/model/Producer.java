package model;
import view.View;
import sun.misc.Cleaner;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

public class Producer extends Thread {
    private int nrClients;
    private int timeMinArrival;
    private int timeMaxArrival;
    private int timeMinService;
    private int timeMaxService;
    private int peakHour = 0;
    private int maxNrClients = Integer.MIN_VALUE;
    private int AverageWaitingTime = 0;
    private int AverageServiceTime = 0;
    private ArrayList<Client> bigQueue;
    private ArrayList<Consumer> queues; //threadurile
    private int nrQueues;
    private int seconds;
    private int secondsPassed;
    private View view;
    private float averageWaitingTime = 0;
    private int nrServiceClients = 0;
    private float averageServiceTime = 0;

    public Producer() {
    }

    public Producer(View view, int nrClients, int timeMinArrival, int timeMaxArrival, int timeMinService, int timeMaxService,
                    int peakHour, int averageWaitingTime, int averageServiceTime, ArrayList<Client> bigQueue,
                    ArrayList<Consumer> queues, int nrQueues, int seconds, int secondsPassed) {
        this.view = view;
        this.nrClients = nrClients;
        this.timeMinArrival = timeMinArrival;
        this.timeMaxArrival = timeMaxArrival;
        this.timeMinService = timeMinService;
        this.timeMaxService = timeMaxService;
        this.peakHour = peakHour;
        AverageWaitingTime = averageWaitingTime;
        AverageServiceTime = averageServiceTime;
        this.bigQueue = bigQueue;
        this.queues = queues;
        this.nrQueues = nrQueues;
        this.seconds = seconds;
        this.secondsPassed = secondsPassed;

    }

    public void generateRandomClients() {
        Random rand = new Random();
        for (int i = 1; i <= nrClients; i++) {
            int t1 = rand.nextInt(timeMaxArrival) + timeMinArrival;
            int t2 = rand.nextInt(timeMaxService) + timeMinService;
            Client e = new Client(i, t1, t2);
            bigQueue.add(e);
            AverageServiceTime += e.getTimeService();
        }
        AverageServiceTime /= nrClients;
        Collections.sort(bigQueue, Comparator.comparing(Client::getTimeArrival));
        System.out.print("Clientii: ");
        for (Client c : bigQueue)
            System.out.print(c + " ");
    }

    public void generateThreads() {
        for (int i = 0; i < nrQueues; i++) {
            Consumer c = new Consumer(true);
            queues.add(c);
        }
        for (Consumer c : queues)
            c.start();
    }

    public void calculatePeakHour() {
        int nrClients = 0;
        for (Consumer c : queues)
            nrClients += c.getQueue().size();
        if (nrClients > maxNrClients) {
            maxNrClients = nrClients;
            peakHour = secondsPassed;
        }
    }

    public int getMinWaitingTimeQueue(int x) {
        int mi = Integer.MAX_VALUE;
        int aux;
        int indx = -1;
        int minindx = 0;
        for (Consumer c : queues) {
            indx++;
            aux = 0;
            for (Client client : c.getQueue())
                aux += client.getTimeService();
            if (aux < mi) {
                mi = aux;
                minindx = indx;
            }
        }
        if (x == 1) //daca avem nevoie de indice
            return minindx;
        else
            return mi; //altfel returneaza valoarea minima
    }

    public int getMinSizeQueue() {
        int mi = 0;
        int minValue = Integer.MAX_VALUE;
        int indx = -1;
        for (Consumer c : queues) {
            indx++;
            if (c.getQueue().size() < minValue) {
                minValue = c.getQueue().size();
                mi = indx;
            }
        }
        return mi;
    }


    public boolean checkAllQueuesEmpty() {
        for (Consumer c : queues)
            if (!c.getQueue().isEmpty())
                return false;
        return true;
    }

    public void printConsumers() {
        int i = -1;
        for (Consumer c : queues) {
            System.out.print("queue nr (" + (++i) + "): ");
            c.printQueues();
            System.out.println();
        }
    }

    private void showAllClients() {
        String s = new String();
        for (Client c : bigQueue)
            s += "(" + c.getId() + "," + c.getTimeArrival() + "," +
                    c.getTimeService() + ")\n";
        view.getPrincipalQueue().setText(s);
    }

    public void showOnFrame(ArrayList<Consumer> queues, int nrQueues, int seconds) {
        showAllClients();
        ArrayList<String> poze = new ArrayList<>();
        view.getTitlee().setText("TIME: " + seconds);
        for (int i = 0; i < nrQueues; i++) {
            int cont = 0;
            String s;
            if (queues.get(i).getQueue().isEmpty())
                s = new String("__empty__");
            else {
                s = new String("");
                for (Client c : queues.get(i).getQueue())
                    s += "(" + c.getId() + "," + c.getTimeArrival() + "," +
                            c.getTimeService() + ")    ";
            }
            poze.add(s);
        }
        view.setText(poze);

    }
    @Override
    public void run() {
        while (secondsPassed <= seconds) {
            System.out.println("\nsec: (" + secondsPassed + ")");
            while (!bigQueue.isEmpty() && bigQueue.get(0).getTimeArrival() == secondsPassed) {
                Client c = bigQueue.remove(0);//eliminam din lista mare
                averageWaitingTime += getMinWaitingTimeQueue(2);
                averageServiceTime += c.getTimeService();
                nrServiceClients += 1;
                queues.get(getMinWaitingTimeQueue(1)).getQueue().add(c);  //coada cea mai eficienta
                // queues.get(getMinSizeQueue()).getQueue().add(c); //coada cea mai scurta
            }
            showOnFrame(queues, nrQueues, secondsPassed);
            Writter.writeCustomers(bigQueue, queues, secondsPassed);
            calculatePeakHour();
            printConsumers();

            if (checkAllQueuesEmpty() == true && bigQueue.isEmpty() == true) {
                break;
            }
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            secondsPassed++;
        }


        //oprim toate threadurile
        //adica suntem in cazul in care programul de simulare s-a incheiat
        //this.stop();
        averageWaitingTime /= nrServiceClients;
        averageServiceTime /= nrServiceClients;
        JOptionPane.showMessageDialog(view.getFrame(), "Peak Hour: " + peakHour
                + "\nAverage Waiting Time:" + averageWaitingTime
                + "\nAverage Service Time:" + averageServiceTime);
        Writter.writeAverageResults(peakHour, averageWaitingTime, averageServiceTime);
        for (Consumer c : queues)
            c.setRunningThread(false);
        System.exit(0);

    }

    public int getNrClients() {
        return nrClients;
    }

    public void setNrClients(int nrClients) {
        this.nrClients = nrClients;
    }

    public int getTimeMinArrival() {
        return timeMinArrival;
    }

    public void setTimeMinArrival(int timeMinArrival) {
        this.timeMinArrival = timeMinArrival;
    }

    public int getTimeMaxArrival() {
        return timeMaxArrival;
    }

    public void setTimeMaxArrival(int timeMaxArrival) {
        this.timeMaxArrival = timeMaxArrival;
    }

    public int getTimeMinService() {
        return timeMinService;
    }

    public void setTimeMinService(int timeMinService) {
        this.timeMinService = timeMinService;
    }

    public int getTimeMaxService() {
        return timeMaxService;
    }

    public void setTimeMaxService(int timeMaxService) {
        this.timeMaxService = timeMaxService;
    }

    public int getPeakHour() {
        return peakHour;
    }

    public void setPeakHour(int peakHour) {
        this.peakHour = peakHour;
    }

    public int getAverageWaitingTime() {
        return AverageWaitingTime;
    }

    public void setAverageWaitingTime(int averageWaitingTime) {
        AverageWaitingTime = averageWaitingTime;
    }

    public int getAverageServiceTime() {
        return AverageServiceTime;
    }

    public void setAverageServiceTime(int averageServiceTime) {
        AverageServiceTime = averageServiceTime;
    }

    public ArrayList<Client> getBigQueue() {
        return bigQueue;
    }

    public void setBigQueue(ArrayList<Client> bigQueue) {
        this.bigQueue = bigQueue;
    }

    public ArrayList<Consumer> getQueues() {
        return queues;
    }

    public void setQueues(ArrayList<Consumer> queues) {
        this.queues = queues;
    }

    public int getNrQueues() {
        return nrQueues;
    }

    public void setNrQueues(int nrQueues) {
        this.nrQueues = nrQueues;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSecondsPassed() {
        return secondsPassed;
    }

    public void setSecondsPassed(int secondsPassed) {
        this.secondsPassed = secondsPassed;
    }
}
