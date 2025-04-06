package modelingproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MultiServerTable extends JFrame implements ActionListener {
    protected JLabel titlelabel;
    protected JPanel startPanel;
    protected JButton back;
    protected JLabel averageWaiting, utilization_A, utilization_B, utilization_C;//labels for showing the statistics in 
    protected String aW, uA, uB, uC;//string to store the computed statistics after it convert from double to string
    protected double averageWait, util_A, util_B, util_C;//the computed Statistics
    protected String[] columnNames;//used in the table
    protected String[][] data;//used in the table

    public MultiServerTable(String[] columnNames, String[][] data, double averageWait, double util_A, double util_B, double util_C) {

        this.columnNames = columnNames;//the column header
        this.data = data;//the table data
        //the computed Statistics from simulation:
        this.averageWait = averageWait;
        this.util_A = util_A;
        this.util_B = util_B;
        this.util_C = util_C;

        setSize(1000, 500);
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

        //Modeling Project/Multi Server System/SIM TABLE
        titlelabel = new JLabel("Modeling Project/Multi Server System/SIM TABLE");
        titlelabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        titlelabel.setForeground(Color.white);
        titlelabel.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(titlelabel);

        //center Panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(70, 10, 70, 10));
        centerPanel.setBackground(new Color(50, 111, 168));//blue color

        //Top Center Panel for Table
        JPanel logCenterPanelTop = new JPanel();
        logCenterPanelTop.setBackground(Color.white);
        logCenterPanelTop.setSize(1000, 500);

        // Table 
        JTable simTable = new JTable(data, columnNames);
        JScrollPane jScrollPane = new JScrollPane(simTable);
        
        logCenterPanelTop.add(jScrollPane);

        //Button Center Panel For Statistics
        JPanel logCenterPanelButtom = new JPanel(new GridLayout(2, 2, 10, 10));
        logCenterPanelButtom.setBackground(Color.white);

        //Average wating Label
        aW = "Average Waiting Time = " + averageWait + "";
        averageWaiting = new JLabel(aW);
        averageWaiting.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        averageWaiting.setForeground(Color.BLACK);
        averageWaiting.setHorizontalAlignment(JLabel.LEFT);
        logCenterPanelButtom.add(averageWaiting);

        //utilization_A Label
        uA = "Utilization of the first Server = " + util_A + "%";
        utilization_A = new JLabel(uA);
        utilization_A.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        utilization_A.setForeground(Color.BLACK);
        utilization_A.setHorizontalAlignment(JLabel.LEFT);
        logCenterPanelButtom.add(utilization_A);

        //utilization_B Label
        uB = "Utilization of the second Server = " + util_B + "%";
        utilization_B = new JLabel(uB);
        utilization_B.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        utilization_B.setForeground(Color.BLACK);
        utilization_B.setHorizontalAlignment(JLabel.LEFT);
        logCenterPanelButtom.add(utilization_B);

        //utilization_C Label
        uC = "Utilization of the third Server" + util_C + "%";
        utilization_C = new JLabel(uC);
        utilization_C.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        utilization_C.setForeground(Color.BLACK);
        utilization_C.setHorizontalAlignment(JLabel.LEFT);
        logCenterPanelButtom.add(utilization_C);

        //buttom panel
        JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttomPanel.setBackground(Color.white);
        
        //back button
        back = new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(new Color(135, 34, 155));
        back.setActionCommand("Back");
        back.addActionListener(this);
        buttomPanel.add(back);
        centerPanel.add(logCenterPanelTop);
        centerPanel.add(logCenterPanelButtom);
        //adding all panel to the main panel
        startPanel.add(labelPanel, BorderLayout.NORTH);
        startPanel.add(centerPanel, BorderLayout.CENTER);
        startPanel.add(buttomPanel, BorderLayout.SOUTH);
        //adding the main panel to the jframe
        add(startPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {//action happened after clicking the button
        //If we click Back
        if (e.getActionCommand().equals("Back")) {
            dispose();
            MultiServerSystem s = new MultiServerSystem();
        }
    }
}
