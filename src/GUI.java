import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class GUI extends JFrame
{
    public final String ATHLETE_DATA = "res\\database.csv";
    List<Athlete> aAthletes  = new LinkedList<Athlete>();
    private DefaultListModel<String> AthleteData;
    //printStuff(aAthletes);

    //Gui
    private JList<String> mLSTAthletes;

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
        aAthletes = new LinkedList<>();
        AthleteData = new DefaultListModel<>();
        loadAthletes(ATHLETE_DATA);
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

        //list of athletes
        mLSTAthletes = new JList<String>(AthleteData);
        //mLSTAthletes.addListSelectionListener(this);

        //split pane
        JScrollPane pnlList = new JScrollPane();
        pnlList.setLayout(new ScrollPaneLayout());
        pnlList.setViewportView(mLSTAthletes);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlList, TopPanel);
        splitPane.setDividerLocation(150);
        add(splitPane, BorderLayout.CENTER);

        //add to roster
        JPanel BottomPanel = new JPanel();
        JButton BTN_Add = new JButton("Add new athlete");
        BottomPanel.add(BTN_Add);
        add(BottomPanel, BorderLayout.PAGE_END);
     }

    private void loadAthletes(String ATHLETE_DATA)
    {
        File file = new File("res/database.csv");
        Scanner reader;

        try
        {
            reader = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Could not find file: " + file.toPath());
            return;
        }

        LinkedList<Athlete> aAthletes = new LinkedList<>();

        reader.nextLine();
        while (reader.hasNext())
        {
            String line = reader.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");

            String ID = String.valueOf(tokenizer.nextToken());
            String FirstName = tokenizer.nextToken();
            String LastName = tokenizer.nextToken();
            int age = Integer.parseInt((tokenizer.nextToken()));
            String race = String.valueOf(tokenizer.nextToken());
            String PB = String.valueOf(tokenizer.nextToken());
            String SB = String.valueOf(tokenizer.nextToken());

            Athlete a = new Athlete(ID, FirstName, LastName, age, race, PB, SB);
            aAthletes.add(a);
        }

     //   return aAthletes;
    }
}

