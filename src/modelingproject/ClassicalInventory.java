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

public class ClassicalInventory extends JFrame implements ActionListener {

    protected JButton back, start;
    protected JPanel startPanel;
    protected JLabel titlelabel, numberOFDaysLabel, QuantityLabel, costOfBuyingLabel, costOfSellingLabel, costOfScrapLabel, CostOfLostInProfitLabel;
    protected JTextField numberOFDays, Quantity, costOfBuying, costOfSelling, costOfScrap, CostOfLostInProfit;
    int nDays, Quant, buying, selling, scrap, lostInProfit;

    public ClassicalInventory() {
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

        //Modeling Project/Classical Inventory
        titlelabel = new JLabel("Modeling Project/Classical Inventory");
        titlelabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        titlelabel.setForeground(Color.white);
        titlelabel.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(titlelabel);

        //center Panel
        JPanel centerPanel = new JPanel(new GridLayout(6, 1));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(70, 10, 70, 10));
        centerPanel.setBackground(new Color(50, 111, 168));//blue color

        //numberOFDaysLabel and numberOFDays (input to take the simualtion)
        numberOFDaysLabel = new JLabel("Number of days : ");
        numberOFDaysLabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        numberOFDaysLabel.setForeground(Color.white);
        numberOFDaysLabel.setBackground(new Color(50, 111, 168));
        numberOFDays = new JTextField();
        centerPanel.add(numberOFDaysLabel);
        centerPanel.add(numberOFDays);

        //QuantityLabel and Quantity (input to take the simualtion)
        QuantityLabel = new JLabel("Number Purchased quantity : ");
        QuantityLabel.setFont(new Font("Sans_Serif", Font.BOLD, 20));
        QuantityLabel.setForeground(Color.white);
        QuantityLabel.setBackground(new Color(50, 111, 168));
        Quantity = new JTextField();
        centerPanel.add(QuantityLabel);
        centerPanel.add(Quantity);

        //costOfBuyingLabel and costOfBuying (input to take the simualtion)
        costOfBuyingLabel = new JLabel("Cost of buying of quantity in cents : ");
        costOfBuyingLabel.setFont(new Font("Sans_Serif", Font.BOLD, 16));
        costOfBuyingLabel.setForeground(Color.white);
        costOfBuyingLabel.setBackground(new Color(50, 111, 168));
        costOfBuying = new JTextField();
        centerPanel.add(costOfBuyingLabel);
        centerPanel.add(costOfBuying);

        //costOfSellingLabel and costOfSelling (input to take the simualtion)
        costOfSellingLabel = new JLabel("Cost of selling of quantity in cents : ");
        costOfSellingLabel.setFont(new Font("Sans_Serif", Font.BOLD, 16));
        costOfSellingLabel.setForeground(Color.white);
        costOfSellingLabel.setBackground(new Color(50, 111, 168));
        costOfSelling = new JTextField();
        centerPanel.add(costOfSellingLabel);
        centerPanel.add(costOfSelling);

        //costOfScrapLabel and costOfScrap (input to take the simualtion)
        costOfScrapLabel = new JLabel("Cost of selling of each scrape in cents : ");
        costOfScrapLabel.setFont(new Font("Sans_Serif", Font.BOLD, 16));
        costOfScrapLabel.setForeground(Color.white);
        costOfScrapLabel.setBackground(new Color(50, 111, 168));
        costOfScrap = new JTextField();
        centerPanel.add(costOfScrapLabel);
        centerPanel.add(costOfScrap);

        //CostOfLostInProfitLabel and CostOfLostInProfit (input to take the simualtion)
        CostOfLostInProfitLabel = new JLabel("Cost of lost in profit in cents : ");
        CostOfLostInProfitLabel.setFont(new Font("Sans_Serif", Font.BOLD, 16));
        CostOfLostInProfitLabel.setForeground(Color.white);
        CostOfLostInProfitLabel.setBackground(new Color(50, 111, 168));
        CostOfLostInProfit = new JTextField();
        centerPanel.add(CostOfLostInProfitLabel);
        centerPanel.add(CostOfLostInProfit);

        //buttom panel
        JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttomPanel.setBackground(Color.white);

        //Back Button
        back = new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(new Color(135, 34, 155));
        back.setActionCommand("Back");
        back.addActionListener(this);

        //Start Simulation and Statistics Button
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
            nDays = Integer.parseInt(numberOFDays.getText());
            Quant = Integer.parseInt(Quantity.getText());
            buying = Integer.parseInt(costOfBuying.getText());
            selling = Integer.parseInt(costOfSelling.getText());
            scrap = Integer.parseInt(costOfScrap.getText());
            lostInProfit = Integer.parseInt(CostOfLostInProfit.getText());
            
            //run the simulation and calculate the statistics
            ClassicalInventoryCode x = new ClassicalInventoryCode(nDays, Quant, buying, selling, scrap, lostInProfit);
            x.Simulate();
            String[][] data = x.getTable();
            String[] columnNames = {"Days", "Randon Digit of Type", "Type of Newsday", "Random Digit of demand", "Demand", "Revenue", "Lost Profit from excess demand", "Salvage from sale of scrap", "Daily Profit"};
            x.Compute_Statestics();
            
            //open the frame that will show the table and its statistics
            new ClassicalInventoryTable(columnNames, data, x.total);

        } else {//If we click Back
            //this frame will be closed
            dispose();
            //open the Start Frame
            Start s = new Start("Modeling  Project");
        }
    }
}
