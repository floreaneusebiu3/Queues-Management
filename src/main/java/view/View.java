package view;
import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class View extends JFrame {
    private int nrQueuesInt;
    private int nrCostomers;
    private int timeMaxSimulation;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private JPanel panel1 = new JPanel(null);
    private JFrame frame = new JFrame();
    private JLabel title = new JLabel("QUEUES MANAGEMENT");
    private Font titleFont = new Font("Verdana", Font.BOLD, 15);
    private TextArea principalQueue;
    private JScrollPane scroll;
    private JButton startButton;
    private JLabel titleLabel;
    private JLabel nrQueuesLabel;
    private JTextField nrQueuesButton;
    private JLabel photoLabel = new JLabel();
    private ImageIcon icon = new ImageIcon("photo.png");

    private JLabel timeMaxSimulationLabel;
    private JLabel arrivalTimeLabel;
    private JLabel nrCostomersLabel;
    private JLabel serviceTimeLabel;

    private JTextField timeMaxSimulationText;
    private JTextField minArrivalTimeText;
    private JTextField maxArrivalTimeText;
    private JTextField minServiceTimeText;
    private JTextField maxServiceTimeText;
    private JTextField nrCostomersText;

    private JTextField timerBox;
    private JLabel simulationTimeLabel;
    private JTextField simulationTimeText;
    private JTextArea[] myQueue = new JTextArea[100];
    private JPanel panel = new JPanel();


    public View() {

    }

    public void init() {

        frame.getContentPane().setBackground(Color.decode("#CEE5F6"));
        frame.setTitle("QUEUES MANAGEMENT");
        frame.setBounds(300, 200, 1200, 700);
        titleLabel = new JLabel("QUEUES MANAGEMENT");
        titleLabel.setBounds(350, 120, 500, 50);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 35));

        startButton = new JButton("START");
        startButton.setBounds(500, 530, 100, 40);
        startButton.setFont(new Font("Verdana", Font.BOLD, 18));

        photoLabel.setBounds(400, 250, 300, 300);
        photoLabel.setIcon(icon);

        nrQueuesLabel = new JLabel("NR QUEUES:");
        nrQueuesLabel.setBounds(375, 180, 150, 40);
        nrQueuesLabel.setFont(new Font("Verdana", Font.BOLD, 18));

        nrQueuesButton = new JTextField("3");
        nrQueuesButton.setBounds(500, 186, 40, 30);
        nrQueuesButton.setFont(new Font("Verdana", Font.BOLD, 18));

        simulationTimeLabel = new JLabel("<html>&nbsp&nbsp&nbsp&nbsp&nbsp TIME SIMULATION:</html>"); //&nbsp-spatiu in html
        simulationTimeLabel.setBounds(360, 220, 150, 80);
        simulationTimeLabel.setFont(new Font("Verdana", Font.BOLD, 18));


        simulationTimeText = new JTextField("10");
        simulationTimeText.setBounds(500, 240, 40, 40);
        simulationTimeText.setFont(new Font("Verdana", Font.BOLD, 18));

        nrCostomersLabel = new JLabel();
        nrCostomersLabel.setBounds(570, 185, 200, 30);
        nrCostomersLabel.setText("NR. COSTOMERS:");
        nrCostomersLabel.setFont(new Font("Verdana", Font.BOLD, 18));

        nrCostomersText = new JTextField("10");
        nrCostomersText.setBounds(745, 188, 40, 30);
        nrCostomersText.setFont(new Font("Verdana", Font.BOLD, 18));

        serviceTimeLabel = new JLabel();
        serviceTimeLabel.setBounds(570, 230, 200, 30);
        serviceTimeLabel.setText("SERVICE TIME:");
        serviceTimeLabel.setFont(new Font("Verdana", Font.BOLD, 18));

        minServiceTimeText = new JTextField("1");
        minServiceTimeText.setBounds(740, 235, 40, 30);
        minServiceTimeText.setFont(new Font("Verdana", Font.BOLD, 18));

        maxServiceTimeText = new JTextField("3");
        maxServiceTimeText.setBounds(790, 235, 40, 30);
        maxServiceTimeText.setFont(new Font("Verdana", Font.BOLD, 18));

        minArrivalTimeText = new JTextField("1");
        minArrivalTimeText.setBounds(740, 275, 40, 30);
        minArrivalTimeText.setFont(new Font("Verdana", Font.BOLD, 18));

        maxArrivalTimeText = new JTextField("4");
        maxArrivalTimeText.setBounds(790, 275, 40, 30);
        maxArrivalTimeText.setFont(new Font("Verdana", Font.BOLD, 18));


        arrivalTimeLabel = new JLabel();
        arrivalTimeLabel.setBounds(570, 270, 200, 30);
        arrivalTimeLabel.setText("ARRIVAL TIME:");
        arrivalTimeLabel.setFont(new Font("Verdana", Font.BOLD, 18));

        panel1.add(simulationTimeText);
        panel1.add(simulationTimeLabel);
        panel1.add(arrivalTimeLabel);
        panel1.add(maxArrivalTimeText);
        panel1.add(minArrivalTimeText);
        panel1.add(minServiceTimeText);
        panel1.add(maxServiceTimeText);
        panel1.add(serviceTimeLabel);
        panel1.add(nrCostomersText);
        panel1.add(nrCostomersLabel);
        panel1.add(titleLabel);
        panel1.add(nrQueuesLabel);
        panel1.add(nrQueuesButton);
        panel1.add(startButton);
        panel1.add(photoLabel);


        frame.add(panel1);
        frame.setVisible(true);
    }

    public void setText(ArrayList<String> mesaje) {
        for (int i = 0; i < nrQueuesInt; i++)
            myQueue[i].setText(mesaje.get(i));
    }

    public void showScreen() {

        title = new JLabel();
        // title.setText("Seconds: "+ p.getSecondsPassed());
        title.setBounds(500, 10, 200, 20);
        title.setFont(new Font("Verdana", Font.BOLD, 20));
        panel.add(title);

        int x = 1, y = 1;
        for (int i = 0; i < nrQueuesInt; i++) {
            if (i >= 10) {
                x = 10 + 210;
                y = (i % 10) * 60 + 40;
            } else {
                x = 10;
                y = 60 * i + 40;
            }
            myQueue[i] = new JTextArea();
            myQueue[i].setLineWrap(true);
            myQueue[i].setWrapStyleWord(true);
            myQueue[i].setText(Integer.toString(i + 1));
            myQueue[i].setBounds(x, y, 200, 50);
            myQueue[i].setFont(new Font("Verdana", Font.BOLD, 18));
            myQueue[i].setEditable(false);

            panel.add(myQueue[i]);
        }

        frame.getContentPane().setBackground(Color.decode("#CEE5F6"));
        frame.setBounds(300, 200, 1200, 700);
        panel.setLayout(null);
        principalQueue = new TextArea();
        principalQueue.setBounds(800, 45, 300, 550);
        principalQueue.setEditable(false);
        principalQueue.setFont(new Font("Verdana", Font.BOLD, 18));
        scroll = new JScrollPane(principalQueue);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(principalQueue);
        frame.add(panel);
        frame.setVisible(true);
    }

    public int getNrQueuesInt() {
        return nrQueuesInt;
    }

    public void setNrQueuesInt(int nrQueuesInt) {
        this.nrQueuesInt = nrQueuesInt;
    }

    public int getNrCostomers() {
        return nrCostomers;
    }

    public void setNrCostomers(int nrCostomers) {
        this.nrCostomers = nrCostomers;
    }

    public int getTimeMaxSimulation() {
        return timeMaxSimulation;
    }

    public void setTimeMaxSimulation(int timeMaxSimulation) {
        this.timeMaxSimulation = timeMaxSimulation;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public void setMinServiceTime(int minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getTitlee() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public Font getTitleFont() {
        return titleFont;
    }

    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
    }

    public TextArea getPrincipalQueue() {
        return principalQueue;
    }

    public void setPrincipalQueue(TextArea principalQueue) {
        this.principalQueue = principalQueue;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JLabel getNrQueuesLabel() {
        return nrQueuesLabel;
    }

    public void setNrQueuesLabel(JLabel nrQueuesLabel) {
        this.nrQueuesLabel = nrQueuesLabel;
    }

    public JTextField getNrQueuesButton() {
        return nrQueuesButton;
    }

    public void setNrQueuesButton(JTextField nrQueuesButton) {
        this.nrQueuesButton = nrQueuesButton;
    }

    public JLabel getPhotoLabel() {
        return photoLabel;
    }

    public void setPhotoLabel(JLabel photoLabel) {
        this.photoLabel = photoLabel;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public JLabel getTimeMaxSimulationLabel() {
        return timeMaxSimulationLabel;
    }

    public void setTimeMaxSimulationLabel(JLabel timeMaxSimulationLabel) {
        this.timeMaxSimulationLabel = timeMaxSimulationLabel;
    }

    public JLabel getArrivalTimeLabel() {
        return arrivalTimeLabel;
    }

    public void setArrivalTimeLabel(JLabel arrivalTimeLabel) {
        this.arrivalTimeLabel = arrivalTimeLabel;
    }

    public JLabel getNrCostomersLabel() {
        return nrCostomersLabel;
    }

    public void setNrCostomersLabel(JLabel nrCostomersLabel) {
        this.nrCostomersLabel = nrCostomersLabel;
    }

    public JLabel getServiceTimeLabel() {
        return serviceTimeLabel;
    }

    public void setServiceTimeLabel(JLabel serviceTimeLabel) {
        this.serviceTimeLabel = serviceTimeLabel;
    }

    public JTextField getTimeMaxSimulationText() {
        return timeMaxSimulationText;
    }

    public void setTimeMaxSimulationText(JTextField timeMaxSimulationText) {
        this.timeMaxSimulationText = timeMaxSimulationText;
    }

    public JTextField getMinArrivalTimeText() {
        return minArrivalTimeText;
    }

    public void setMinArrivalTimeText(JTextField minArrivalTimeText) {
        this.minArrivalTimeText = minArrivalTimeText;
    }

    public JTextField getMaxArrivalTimeText() {
        return maxArrivalTimeText;
    }

    public void setMaxArrivalTimeText(JTextField maxArrivalTimeText) {
        this.maxArrivalTimeText = maxArrivalTimeText;
    }

    public JTextField getMinServiceTimeText() {
        return minServiceTimeText;
    }

    public void setMinServiceTimeText(JTextField minServiceTimeText) {
        this.minServiceTimeText = minServiceTimeText;
    }

    public JTextField getMaxServiceTimeText() {
        return maxServiceTimeText;
    }

    public void setMaxServiceTimeText(JTextField maxServiceTimeText) {
        this.maxServiceTimeText = maxServiceTimeText;
    }

    public JTextField getNrCostomersText() {
        return nrCostomersText;
    }

    public void setNrCostomersText(JTextField nrCostomersText) {
        this.nrCostomersText = nrCostomersText;
    }

    public JTextField getTimerBox() {
        return timerBox;
    }

    public void setTimerBox(JTextField timerBox) {
        this.timerBox = timerBox;
    }

    public JLabel getSimulationTimeLabel() {
        return simulationTimeLabel;
    }

    public void setSimulationTimeLabel(JLabel simulationTimeLabel) {
        this.simulationTimeLabel = simulationTimeLabel;
    }

    public JTextField getSimulationTimeText() {
        return simulationTimeText;
    }

    public void setSimulationTimeText(JTextField simulationTimeText) {
        this.simulationTimeText = simulationTimeText;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
