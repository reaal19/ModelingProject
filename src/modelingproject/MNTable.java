package modelingproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class MNTable extends JFrame implements ActionListener {

    protected JButton back;
    protected JLabel titlelabel, ShortageLabel;
    protected String Shortage;
    protected JPanel startPanel;
    protected String[] columnNames;
    protected String[][] data;
    protected double shortage;

    public MNTable(String[] columnNames, String[][] data, double shortage) {

        this.columnNames = columnNames;
        this.data = data;
        this.shortage = shortage;

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

        //Modeling Project/ClassicalInventory/ClassicalInventoryTable
        titlelabel = new JLabel("Modeling Project/MNInventory/MNTable");
        titlelabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        titlelabel.setForeground(Color.white);
        titlelabel.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(titlelabel);

        //center Panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(70, 10, 70, 10));
        centerPanel.setBackground(new Color(50, 111, 168));//blue color

        JPanel logCenterPanelTop = new JPanel();
        logCenterPanelTop.setBackground(Color.white);

        JTable simTable = new JTable(data, columnNames);

        simTable.setPreferredSize(new Dimension(300, 300));
        JScrollPane jScrollPane = new JScrollPane(simTable);

        logCenterPanelTop.add(jScrollPane);

        JPanel logCenterPanelButtom = new JPanel();
        logCenterPanelButtom.setBackground(Color.white);

        //Total Profit Label
        Shortage = "Shortage Proportion = " + shortage + "%";
        ShortageLabel = new JLabel(Shortage);
        ShortageLabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        ShortageLabel.setForeground(Color.BLACK);
        ShortageLabel.setHorizontalAlignment(JLabel.LEFT);
        logCenterPanelButtom.add(ShortageLabel);

        //buttom panel
        JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttomPanel.setBackground(Color.white);
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
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            dispose();
            new MNSystem();

        }
    }
}
