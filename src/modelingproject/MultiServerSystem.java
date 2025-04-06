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
public class MultiServerSystem extends JFrame implements ActionListener {

    public static int Simulation_Length;
    protected JButton back, start;
    protected JLabel titlelabel, simulationLengthLabel;
    protected JTextField simulationLength;
    protected JPanel startPanel;
    protected Scanner sc;

    public MultiServerSystem() {
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

        //Modeling Project/Multi Server System
        titlelabel = new JLabel("Modeling Project/Multi Server System");
        titlelabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        titlelabel.setForeground(Color.white);
        titlelabel.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(titlelabel);

        //center Panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 2));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(70, 10, 70, 10));
        centerPanel.setBackground(new Color(50, 111, 168));//blue color

        //SimulationLengthLabel and simulationLength (input to take the simualtion)
        simulationLengthLabel = new JLabel("Enter the Simulation Lenght :");
        simulationLengthLabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        simulationLengthLabel.setForeground(Color.white);
        simulationLengthLabel.setBackground(new Color(50, 111, 168));
        simulationLength = new JTextField();

        centerPanel.add(simulationLengthLabel);
        centerPanel.add(simulationLength);

        //buttom panel
        JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttomPanel.setBackground(Color.white);
 
        //Back Button
        back = new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(new Color(135, 34, 155));
        back.setActionCommand("Back");
        back.addActionListener(this);
        
        //Start Simulation Button
        start = new JButton("Start Simulation and Statistics");
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
    public void actionPerformed(ActionEvent e) {//What will Happen when we click one of the buttons ?
        //If we click Start Simulation and Statistics
        if (e.getActionCommand().equals("start")) {
            //this frame will be closed
            dispose();
            //take the input from the user
            Simulation_Length = Integer.parseInt(simulationLength.getText());
            //run the simulation and calculate the statistics
            MultiServerCode x = new MultiServerCode(Simulation_Length);
            x.Simulate();
            String[][] data = x.getTable();
            String[] columnNames = {"CustomerID", "IAT", "Arrival", "A_ServiceTime", "A_ServiceBegin", "A_ServiceEnd", "B_ServiceTime", "B_ServiceBegin", "B_ServiceEnd", "C_ServiceTime", "C_ServiceBegin", "C_ServiceEnd", "Wait"};
            x.Compute_Statestics();
            
            //open the frame that will show the table and its statistics
            new MultiServerTable(columnNames, data, x.averageWaiting, x.A_util, x.B_util, x.C_util);

        } else {//If we click Back
              //this frame will be closed
            dispose();
            //open the Start Frame
            Start s = new Start("Modeling  Project");
        }
    }
}
