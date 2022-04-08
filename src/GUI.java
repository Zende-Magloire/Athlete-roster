import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame
{
    public static void main (String[] args)
    {
        EventQueue.invokeLater(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        new GUI();
                    }
                }
        );
    }

    public GUI()
    {
        initGUI();
    }

    private void initGUI()
    {
        setTitle("Stetson Striders Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        //search panel
        JPanel TopPanel = new JPanel();
        JTextField LBL_name = new JTextField(10);
        JTextField LBL_ID = new JTextField(10);
        JButton BTN_N_Search = new JButton("Search");
        JButton BTN_Search = new JButton("Search");
        TopPanel.add(new JLabel("Search by name: "));
        TopPanel.add(LBL_name);
        TopPanel.add(BTN_N_Search);
        TopPanel.add(new JLabel("Search by ID: "));
        TopPanel.add(LBL_ID);
        TopPanel.add(BTN_Search);
//        BTN_N_Search.addActionListener(this);
//        BTN_Search.addActionListener(this);
        add(TopPanel, BorderLayout.NORTH);

        //add to roster
        JPanel BottomPanel = new JPanel();
        JButton BTN_Add = new JButton("Add new athlete");
        BottomPanel.add(BTN_Add);
        add(BottomPanel, BorderLayout.PAGE_END);

        //split pane
        JScrollPane pnlList = new JScrollPane();
        pnlList.setLayout(new ScrollPaneLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlList, TopPanel);
        splitPane.setDividerLocation(150);
        add(splitPane, BorderLayout.CENTER);
     }
}

