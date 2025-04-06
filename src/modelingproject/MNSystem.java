package modelingproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MNSystem extends JFrame implements ActionListener {

    static int num1, num2;// the two random number of the interarrival time and the service time
    public static int Simulation_Length;
    protected JButton back, start;
    protected JLabel titlelabel, cycleLengthLabel, noOfCycles, startingInventoryLabel, OrderLabel, conditionLabel;
    protected JTextField  noOfCyclesField, cycleLengthField, startingInventoryField, OrderField, conditionField;
    protected JPanel startPanel;
    protected Scanner sc;

    public MNSystem() {
        setSize(500, 500);
        //Panel of the JFrame
        startPanel = new JPanel(new BorderLayout(10, 10));
        startPanel.setBackground(Color.white);
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init() {
        //Title panel
        JPanel labelPanel = new JPanel();
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        labelPanel.setBackground(new Color(135, 34, 155));

        //ATM Project/Account Services/Cash Deposite
        titlelabel = new JLabel("Modeling Project/MNInventory");
        titlelabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        titlelabel.setForeground(Color.white);
        titlelabel.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(titlelabel);

        //center Panel
        JPanel centerPanel = new JPanel(new GridLayout(5, 2));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(70, 10, 70, 10));
        centerPanel.setBackground(new Color(50, 111, 168));//blue color

        cycleLengthLabel = new JLabel("Enter Cycle Length:(N)  ");
        cycleLengthLabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        cycleLengthLabel.setForeground(Color.white);
        cycleLengthLabel.setBackground(new Color(50, 111, 168));
        cycleLengthField = new JTextField();
        
         startingInventoryLabel = new JLabel("Enter Starting Inventory:  ");
        startingInventoryLabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        startingInventoryLabel.setForeground(Color.white);
        startingInventoryLabel.setBackground(new Color(50, 111, 168));
        startingInventoryField = new JTextField();
        
         noOfCycles = new JLabel("Enter Number Of Cycles: ");
        noOfCycles.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        noOfCycles.setForeground(Color.white);
        noOfCycles.setBackground(new Color(50, 111, 168));
        noOfCyclesField = new JTextField();
        
         OrderLabel = new JLabel("Enter Quantity To Be Ordered: ");
        OrderLabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        OrderLabel.setForeground(Color.white);
        OrderLabel.setBackground(new Color(50, 111, 168));
        OrderField = new JTextField();
        
        conditionLabel = new JLabel("Order When Stock Reaches : ");
        conditionLabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        conditionLabel.setForeground(Color.white);
        conditionLabel.setBackground(new Color(50, 111, 168));
        conditionField = new JTextField();
        

        centerPanel.add(cycleLengthLabel);
        centerPanel.add(cycleLengthField);
        centerPanel.add(noOfCycles);
        centerPanel.add(noOfCyclesField);
        centerPanel.add(conditionLabel);
        centerPanel.add(conditionField);
        centerPanel.add(OrderLabel);
        centerPanel.add(OrderField);
        centerPanel.add(startingInventoryLabel);
        centerPanel.add(startingInventoryField);

        

        

        //buttom panel
        JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttomPanel.setBackground(Color.white);
        back = new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(new Color(135, 34, 155));
        back.setActionCommand("Back");
        back.addActionListener(this);

        start = new JButton("Start Simulation");
        start.setForeground(Color.white);
        start.setBackground(new Color(135, 34, 155));
        start.setActionCommand("start");
        start.addActionListener(this);

        buttomPanel.add(back);
        buttomPanel.add(start);

        //adding all panel to the main panel
        startPanel.add(labelPanel, BorderLayout.NORTH);
        startPanel.add(centerPanel, BorderLayout.CENTER);
        startPanel.add(buttomPanel, BorderLayout.SOUTH);

        //adding the main panel to the jframe
        add(startPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("start")) {
dispose();
            int cycles = Integer.parseInt(noOfCyclesField.getText());
            int cycleLength = Integer.parseInt(cycleLengthField.getText());
            int startInventory = Integer.parseInt(startingInventoryField.getText());
            int condition = Integer.parseInt(conditionField.getText());
            int orderQ = Integer.parseInt(OrderField.getText());
            MNInventoryCode x = new MNInventoryCode(cycleLength,cycles,startInventory,condition,orderQ);  
            x.simulate();
         String[][] data = x.getTable();
        String[] columnNames={"Cycle","Day","Begin","DemandRD","Demand","End","Shortage","Order","LeadTimeRD","LeadTime"};
        double shortageProportion =(Math.round((x.shortageCount/x.simulationLength*100) * 100.0) / 100.0) * 100;
       
        new MNTable(columnNames, data, shortageProportion);
        
        } else {
            dispose();
            Start s = new Start("Modeling  Project");
        }
    }
}
