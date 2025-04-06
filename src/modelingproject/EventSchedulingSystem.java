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
import javax.swing.JTextField;

public class EventSchedulingSystem extends JFrame implements ActionListener {

    static int num1, num2;// the two random number of the interarrival time and the service time
    public static int Simulation_Length;
    protected JButton back, start;
    protected JLabel titlelabel, simulationLengthLabel;
    protected JTextField simulationLength;
    protected JPanel startPanel;

    public EventSchedulingSystem() {
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

        //Modeling Project/Event Scheduling
        titlelabel = new JLabel("Modeling Project/Event Scheduling");
        titlelabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        titlelabel.setForeground(Color.white);
        titlelabel.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(titlelabel);

        //center Panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 2));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(70, 10, 70, 10));
        centerPanel.setBackground(new Color(50, 111, 168));

        //SimulationLengthLabel and simulationLength (input to take the simualtion)
        simulationLengthLabel = new JLabel("Enter the Simulation Length :");
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

        //Start Simulation
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
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("start")) {//What will Happen when we click one of the buttons ?
        //If we click Start Simulation and Statistics
             //this frame will be closed
            dispose();
            //take the input from the user
            Simulation_Length = Integer.parseInt(simulationLength.getText());
            //run the simulation and calculate the statistics
            EventSchedulingCode x = new EventSchedulingCode(Simulation_Length);
            String[][] data = x.getData();
            String[] columnNames = {"Event", "CLOCK", "LQ", "LS", "Checkout List", "FEL"};
            
             //open the frame that will show the table and its statistics
            new EventSchedulingTable(columnNames, data);

        } else {//If we click Back
              //this frame will be closed
            dispose();
            //open the Start Frame
            Start s = new Start("Modeling  Project");
        }
    }
}
