package conrtolview;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import queuemodel.Cashier;
import queuemodel.ServiceQueue;
import queuemodel.ServiceQueueManager;

public class ViewControl extends JFrame implements ActionListener, Runnable
{
    //Components
    
    JButton startButton, resetButton;
    JLabel avWtTm, avSvTm, avIdTm, totCusInfo, csh1, csh2, csh3, csh4, csh5;
    JComboBox<Integer> numQueue, queueDelay, numCust, custDelay;
    JProgressBar totalBar, qu1Bar, qu2Bar, qu3Bar, qu4Bar, qu5Bar;
    JPanel center, east, south, centerCenter, centerSouth;
    HashMap<ServiceQueue, JProgressBar> progressMap;
    
    //Backend Components
    ServiceQueueManager sqm;
    
    
    public ViewControl()
    {
        //Initializers
        
        //Window Properties
        this.setTitle("Grocery Store Simulator 2013");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Configure Content Pane
        Container contentBorder = this.getContentPane();
        contentBorder.setLayout(new BorderLayout());
        
        //Center Panel - Progress Bars
        center = new JPanel(new BorderLayout());
        centerCenter = new JPanel(new GridLayout(2,5));
        
        
        
        
    }
    
    public static void main(String[] args)
    {
        ViewControl win = new ViewControl();
        win.setVisible(true);
    }
    
    public void start()
    {
        
    }
    
    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        
    }


    

}
