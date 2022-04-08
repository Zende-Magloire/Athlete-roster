import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame
{
    //search
    private JPanel TopPanel;
    private JPanel BottomPanel;
    private JTextField LBL_name;
    private JTextField LBL_ID;
    private JButton BTN_Search;
    private JButton BTN_N_Search;
    private JSplitPane splitPane;

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
        LBL_name = new JTextField(10);
        LBL_ID = new JTextField(10);
        BTN_N_Search = new JButton("Search");
        BTN_Search = new JButton("Search");
        TopPanel.add(new JLabel("Search by name: "));
        TopPanel.add(LBL_name);
        TopPanel.add(BTN_N_Search);
        TopPanel.add(new JLabel("Search by ID: "));
        TopPanel.add(LBL_ID);
        TopPanel.add(BTN_Search);
//        BTN_N_Search.addActionListener(this);
//        BTN_Search.addActionListener(this);

        add(TopPanel, BorderLayout.NORTH);

        //split pane
        JScrollPane pnlList = new JScrollPane();
        pnlList.setLayout(new ScrollPaneLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlList, TopPanel);
        splitPane.setDividerLocation(150);
        add(splitPane, BorderLayout.CENTER);
     }
}

