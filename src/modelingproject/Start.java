package modelingproject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Start extends JFrame implements ActionListener {

    //Buttons
    protected JButton multiServer;
    protected JButton classicalInventory;
    protected JButton M_N_Inventory;
    protected JButton eventScheduling;
    
    //Labels
    protected JLabel Main_Title ;
    protected JLabel DS;
    
    //Panels
    protected JPanel startPanel ;
    protected JPanel centerPanel ;
    
    public Start(String title) {
        //size and title of the Jframe
        setTitle(title);
        setSize(500, 500);

        //main panel of the JFrame
        startPanel = new JPanel(new BorderLayout(10, 10));
        startPanel.setBackground(Color.white);
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init() {

        //Panel of labels:
        JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        labelPanel.setBackground(new Color(135, 34, 155));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Title Label (DHL Simulation Project)
        Main_Title = new JLabel("DHL Project");
        Main_Title.setFont(new Font("Sans_Serif", Font.BOLD, 22));
        Main_Title.setForeground(Color.YELLOW);
        Main_Title.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.setBackground(new Color(50,50,50));
        labelPanel.add(Main_Title);

        // Description Label
        DS = new JLabel("This project is for simulation of different methods for solving  differnt problems");
        DS.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        DS.setForeground(Color.RED);
        DS.setHorizontalAlignment(JLabel.LEFT);
        labelPanel.add(DS);

        //Panels of buttons:
        
        //logCenterPanelTop has multiServer button and classicalInventory button
        JPanel logCenterPanelTop = new JPanel(new GridLayout(1, 2, 10, 10));
        logCenterPanelTop.setBackground(Color.white);

        //logCenterPanelButtom  has (M,N) Inventory button and eventScheduling button
        JPanel logCenterPanelButtom = new JPanel(new GridLayout(1, 2, 10, 10));
        logCenterPanelButtom.setBackground(Color.white);

        //Multi Server-Single Queuing System button:
        multiServer = new JButton("Multi-Server");
        multiServer.setPreferredSize(new Dimension(250, 150));
        multiServer.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        multiServer.setBackground(new Color(56, 172, 157));
        multiServer.setForeground(Color.white);
        multiServer.setActionCommand("Multi-Server");
        multiServer.addActionListener(this);
        logCenterPanelTop.add(multiServer);

        //Classical Inventory Simulation button:
        classicalInventory = new JButton("Classical Inv");
        classicalInventory.setPreferredSize(new Dimension(250, 150));
        classicalInventory.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        classicalInventory.setBackground(new Color(56, 172, 157));
        classicalInventory.setForeground(Color.white);
        classicalInventory.setActionCommand("Classical Inventory");
        classicalInventory.addActionListener(this);
        logCenterPanelTop.add(classicalInventory);

        //(M,N) Inventory Simulation button:
        M_N_Inventory = new JButton("(M,N)Inv");
        M_N_Inventory.setPreferredSize(new Dimension(250, 150));
        M_N_Inventory.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        M_N_Inventory.setBackground(new Color(56, 172, 157));
        M_N_Inventory.setForeground(Color.white);
        M_N_Inventory.setActionCommand("M_N_Inventory");
        M_N_Inventory.addActionListener(this);
        logCenterPanelButtom.add(M_N_Inventory);

        //Event Scheduling button:
        eventScheduling = new JButton("Event");
        eventScheduling.setPreferredSize(new Dimension(250, 150));
        eventScheduling.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        eventScheduling.setBackground(new Color(56, 172, 157));
        eventScheduling.setForeground(Color.white);
        eventScheduling.setActionCommand("Event Scheduling");
        eventScheduling.addActionListener(this);
        logCenterPanelButtom.add(eventScheduling);

        //Adding buttons Panels to the center panel
        centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setBackground(Color.white);
        centerPanel.add(logCenterPanelTop);
        centerPanel.add(logCenterPanelButtom);

        //Adding to panel to main panel:
        startPanel.add(labelPanel, BorderLayout.NORTH);
        startPanel.add(centerPanel, BorderLayout.CENTER);
        add(startPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Multi-Server")) { //action on the Multi-Server
            //Close the Start frame
            dispose();
            //new frame of Multi-Server
            MultiServerSystem o = new MultiServerSystem();

        } else if (e.getActionCommand().equals("Classical Inventory")) {//action on Classical Inventory
            //Close the Start frame
            dispose();
            //new frame of login
            ClassicalInventory o = new ClassicalInventory();

        } else if (e.getActionCommand().equals("M_N_Inventory")) { //action on the M_N_Inventory
            //Close the first frame
            dispose();
            MNSystem o = new MNSystem();

        } else { //action on the Event Scheduling

            //Close the Start frame
            dispose();
            EventSchedulingSystem o = new EventSchedulingSystem();

        }
    }
}
