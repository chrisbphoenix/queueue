package conrtolview;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import queuemodel.ServiceQueue;
import queuemodel.ServiceQueueManager;

public class ViewControl extends JFrame implements ActionListener, Runnable
{
    //Components
    
    private JButton theButton;
    private JLabel avWtTmLabel, avSvTmLabel, avIdTmLabel, csh1Label, csh2Label, csh3Label, csh4Label, csh5Label;
    private JLabel numQueueLabel, serviceDelayLabel, cashierDelayLabel, numCustLabel, custDelayLabel, blankLabel;
    private JComboBox<Integer> numQueue, serviceDelay, cashierDelay, numCust, custDelay;
    private JProgressBar totalBar;
    private JPanel center, north, south, centerCenter, centerSouth;
    private Integer[] numQueuesList = {1,2,3,4,5,10}, delayList = {10,100,500,1000}, custList = {10,100,500,1000,10000,1000000};
    private HashMap<ServiceQueue, JProgressBar> progressMap;
    private boolean isRunning;
    private Thread myThread;
    
    //Backend Components
    ServiceQueueManager sqm;
    
    
    public ViewControl()
    {
        //Initializers
        progressMap = new HashMap<ServiceQueue, JProgressBar>();
        myThread = new Thread(this);
        //Window Properties
        this.setTitle("Grocery Store Simulator 2014");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Configure Content Pane
        Container contentBorder = this.getContentPane();
        contentBorder.setLayout(new BorderLayout());
        
        //Center Panels - Progress Bars
        center = new JPanel(new BorderLayout());
        centerCenter = new JPanel(new GridLayout(1,5));
        centerSouth = new JPanel();
        
        center.add(centerCenter, BorderLayout.CENTER);
        center.add(centerSouth, BorderLayout.SOUTH);
        contentBorder.add(center);
        
        //North Panel - Statistics
        north = new JPanel(new GridLayout(1,8, 4, 4));
        contentBorder.add(north, BorderLayout.NORTH);
        
        //South Panel - User Controls
        south = new JPanel(new GridLayout(2,6));
        numQueueLabel = new JLabel("# of Queues");
        serviceDelayLabel = new JLabel("Max Service Delay ms");
        cashierDelayLabel = new JLabel("Cashier Polling Rate ms");
        numCustLabel = new JLabel("# of Customers");
        custDelayLabel = new JLabel("Max Customer Delay ms");
        blankLabel = new JLabel();
        south.add(numQueueLabel);
        south.add(serviceDelayLabel);
        south.add(cashierDelayLabel);
        south.add(numCustLabel);
        south.add(custDelayLabel);
        south.add(blankLabel);
        
        numQueue = new JComboBox<Integer>(numQueuesList);
        south.add(numQueue);
        serviceDelay = new JComboBox<Integer>(delayList);
        serviceDelay.setEditable(true);
        south.add(serviceDelay);
        cashierDelay = new JComboBox<Integer>(delayList);
        cashierDelay.setEditable(true);
        south.add(cashierDelay);
        numCust = new JComboBox<Integer>(custList);
        numCust.setEditable(true);
        south.add(numCust);
        custDelay = new JComboBox<Integer>(delayList);
        custDelay.setEditable(true);
        south.add(custDelay);
        
        theButton = new JButton("Goooo!");
        theButton.addActionListener(this);
        south.add(theButton);
        
        contentBorder.add(south, BorderLayout.SOUTH);
        
    }
    /**
     * Halts the simulation.
     */
    private void stop()
    {
        theButton.setText("Again!");
        isRunning = false;
        sqm.stopProgram();
        myThread = new Thread(this);
    }
    /**
     * Updates the GUI to reflect rolling progress.
     */
    private void updateGui()
    {
        for(ServiceQueue sq : sqm.getAllServiceQueues())
        {
            progressMap.get(sq).setValue(sq.getTotalCustomersInLine());
            progressMap.get(sq).setString(sq.getTotalCustomersInLine() + " In Line; " + sq.getNumberCustomersServedSoFar() + " Served"); 
        }
        
        totalBar.setValue(sqm.totalServedSoFar());
        totalBar.setString("Progress: " + sqm.totalServedSoFar() + "/" + sqm.getCustomerGenerator().getMaxNumberCustomers());
        
        north.removeAll();
        avWtTmLabel = new JLabel("Average Wait: " + sqm.averageWaitTime(), JLabel.LEFT);
        avSvTmLabel = new JLabel("Average Service: " + sqm.averageServiceTime(), JLabel.CENTER);
        avIdTmLabel = new JLabel("Average Idle: " + sqm.averageWaitTime(), JLabel.RIGHT);
        north.add(avWtTmLabel);
        north.add(avSvTmLabel);
        north.add(avIdTmLabel);
        
        this.setVisible(true);
    }
    /**
     * Constructs the remainder of the GUI using the user's preferences.
     */
    private void rebuildGui()
    {
        centerCenter.removeAll();
        centerSouth.removeAll();
        for(ServiceQueue sq : sqm.getAllServiceQueues())
        {
            progressMap.put(sq, new JProgressBar(SwingConstants.VERTICAL, 0, sqm.getCustomerGenerator().getMaxNumberCustomers() / sqm.getNumberServiceLines()));
            progressMap.get(sq).setStringPainted(true);
            centerCenter.add(progressMap.get(sq));
        }
        totalBar = new JProgressBar(0, sqm.getCustomerGenerator().getMaxNumberCustomers());
        totalBar.setStringPainted(true);
        centerSouth.add(totalBar);
        
        
        
        this.setVisible(true);
    }
    
    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args)
    {
        ViewControl win = new ViewControl();
        win.setVisible(true);
    }
    /**
     * Begins the simulation.
     */
    private void start()
    {
        theButton.setText("Stop!!");
        sqm = new ServiceQueueManager((int)numQueue.getSelectedItem(), (int)numCust.getSelectedItem(), (int)custDelay.getSelectedItem(), (int)serviceDelay.getSelectedItem(), (int)cashierDelay.getSelectedItem());
        this.rebuildGui();
        sqm.startProgram();
        myThread.start();
    }
    
    @Override
    public void run()
    {
        isRunning = true;
        while (isRunning)
        {
            this.updateGui();
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent ev)
    {
        if(ev.getSource().equals(theButton))
        {
            if(isRunning)
            {
                this.stop();
            }
            else
            {
                this.start();
            }
        }
    }


    

}
