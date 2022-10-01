package model;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writter {


    public static void writeCustomers(ArrayList<Client> bigQueue, ArrayList<Consumer> queues, int seconds) {
        File f;
        FileWriter fstream;
        BufferedWriter buf;
        f = new File("LogOfEvents.txt");
        try {
            fstream = new FileWriter(f, true);
            buf = new BufferedWriter(fstream);
            buf.newLine();
            buf.write("time: " + seconds);
            buf.newLine();
            buf.write("BIG QUEUE: ");
            for (Client c : bigQueue)
                buf.write(c.toString());
            buf.newLine();


            for (int i = 0; i < queues.size(); i++) {
                int cont = 0;
                String s;
                buf.write("queue " + (i + 1) + ":");
                if (queues.get(i).getQueue().isEmpty())
                    s = new String("__empty__");
                else {
                    s = new String("");
                    for (Client c : queues.get(i).getQueue())
                        s += "(" + c.getId() + "," + c.getTimeArrival() + "," + c.getTimeService() + ")    ";
                }
                buf.write(s);
                buf.newLine();
                buf.newLine();
            }
            buf.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAverageResults(int peakHour, float averageWaitingTime, float averageServiceTime) {
        File f;
        FileWriter fstream;
        BufferedWriter buf;
        f = new File("LogOfEvents.txt");
        try {
            fstream = new FileWriter(f, true);
            buf = new BufferedWriter(fstream);
            buf.newLine();
            buf.write("FINAL RESULTS:");
            buf.newLine();
            buf.write("Peak Hour: " + peakHour
                    + "\nAverage Waiting Time:" + averageWaitingTime
                    + "\nAverage Service Time:" + averageServiceTime);
            buf.close();
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}