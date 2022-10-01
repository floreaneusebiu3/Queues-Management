package controller;
import model.Producer;
import view.View;
import model.Client;
import model.Consumer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private Producer p;
    private View view = new View();


    public Controller() {
    }


    public void startSimulation() {
        view.init();
        view.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setNrQueuesInt(Integer.parseInt(view.getNrQueuesButton().getText()));
                view.getFrame().getContentPane().removeAll();
                view.getFrame().pack();
                view.showScreen();
                ArrayList<Client> clients = new ArrayList<>();
                ArrayList<Consumer> threads = new ArrayList<>();
                Producer p = new Producer(view, Integer.parseInt(view.getNrCostomersText().getText()), Integer.parseInt(view.getMinArrivalTimeText().getText()),
                        Integer.parseInt(view.getMaxArrivalTimeText().getText()), Integer.parseInt(view.getMinServiceTimeText().getText()),
                        Integer.parseInt(view.getMaxServiceTimeText().getText()),
                        0, 0, 0, clients, threads,
                        Integer.parseInt(view.getNrQueuesButton().getText()), Integer.parseInt(view.getSimulationTimeText().getText()),
                        0);
                p.generateRandomClients();
                p.generateThreads();
                p.start();
            }
        });
    }
}
