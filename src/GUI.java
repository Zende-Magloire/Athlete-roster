import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class GUI extends JFrame implements ListSelectionListener, ActionListener {
    public final String ATHLETE_DATA = "res\\database.csv";
    private static Map<String, Athlete> MapAthlete = new HashMap<>();
    private static Map<String, Athlete> MapAthlete_N = new HashMap<>();
    private ArrayList<Athlete> aAthletes;
    private DefaultListModel<String> AthleteData;

    //GUI
    private JList<String> mLSTAthletes;
    JLabel mLBLAthleteInfo;
    JTextField LBL_ID;
    JButton BTN_Search;
    JButton BTN_N_Search;
    JTextField LBL_name;

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
        aAthletes = new ArrayList<>();
        AthleteData = new DefaultListModel<>();
        loadAthletes(ATHLETE_DATA);
        initGUI();
    }

    private void initGUI()
    {
        setTitle("Stetson Striders Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);

        //search panel
        JPanel TopPanel = new JPanel();
        LBL_name = new JTextField(10);
        LBL_ID = new JTextField(10);
        BTN_N_Search = new JButton("Search");
        BTN_Search = new JButton("Search");
        TopPanel.add(new JLabel("Search by first name: "));
        TopPanel.add(LBL_name);
        TopPanel.add(BTN_N_Search);
        TopPanel.add(new JLabel("Search by ID: "));
        TopPanel.add(LBL_ID);
        TopPanel.add(BTN_Search);
        BTN_N_Search.addActionListener(this);
        BTN_Search.addActionListener(this);
        add(TopPanel, BorderLayout.PAGE_START);

        //list of athletes
        mLSTAthletes = new JList<String>(AthleteData);
        mLSTAthletes.setModel(AthleteData);
        mLSTAthletes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mLSTAthletes.setLayoutOrientation(JList.VERTICAL);
        mLSTAthletes.setVisibleRowCount(-1);
        mLSTAthletes.addListSelectionListener(this);
        add(mLSTAthletes, BorderLayout.WEST);

        //split pane
        JScrollPane pnlList = new JScrollPane();
        pnlList.setLayout(new ScrollPaneLayout());
        pnlList.setViewportView(mLSTAthletes);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlList, TopPanel);
        splitPane.setDividerLocation(150);
        add(splitPane, BorderLayout.WEST);

        //info panel
        JPanel infoPanel = new JPanel();
        mLBLAthleteInfo = new JLabel("Stetson Striders Athlete Database Search");
        mLBLAthleteInfo.setHorizontalAlignment(JLabel.CENTER);
        infoPanel.add(mLBLAthleteInfo);
        add(infoPanel, BorderLayout.EAST);
       // add(mLBLAthleteInfo, BorderLayout.AFTER_LAST_LINE);

        //add to roster
        JPanel BottomPanel = new JPanel();
        JButton BTN_Add = new JButton("Add new athlete");
        BottomPanel.add(BTN_Add);
        add(BottomPanel, BorderLayout.PAGE_END);

        setLocationRelativeTo(null);
        setVisible(true);
     }

    private void loadAthletes(String ATHLETE_DATA)
    {
        File file = new File("res/database.csv");
        Scanner reader;

        try
        {
            reader = new Scanner(file);

            ArrayList<Athlete> aAthletes = new ArrayList<>();

            reader.nextLine();
            while (reader.hasNext()) {
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
                AthleteData.addElement(ID + ": " + FirstName + " " + LastName);
                MapAthlete.put(a.getID(), a);
                String firstname = a.getFirstName().toUpperCase(Locale.ROOT);
                MapAthlete_N.put(firstname, a);
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "ERROR! Couldn't load athlete file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;

            if (button == BTN_Search) {

                String id = LBL_ID.getText();
                Athlete athlete = MapAthlete.get(id);

                if (!MapAthlete.containsKey(id)) {
                    mLBLAthleteInfo.setOpaque(true);
                    mLBLAthleteInfo.setBackground(Color.RED);
                    mLBLAthleteInfo.setText(id + " is not an ID number of any of our athletes! Please enter student's first name only.");
                }
                else
                {
                    mLBLAthleteInfo.setOpaque(true);
                    mLBLAthleteInfo.setBackground(Color.YELLOW);
                    athlete = MapAthlete.get(id);
                    mLBLAthleteInfo.setText(String.valueOf(athlete));
                }
            }
            else if (button == BTN_N_Search) {
                String name = LBL_name.getText();
                String Name = name.toUpperCase(Locale.ROOT);
                Athlete nAthlete = MapAthlete_N.get(Name);

                if (!MapAthlete_N.containsKey(Name)) {
                    mLBLAthleteInfo.setOpaque(true);
                    mLBLAthleteInfo.setBackground(Color.RED);
                    mLBLAthleteInfo.setText(Name + " is not one of our athletes! Try again.");
                } else
                {
                    mLBLAthleteInfo.setOpaque(true);
                    mLBLAthleteInfo.setBackground(Color.YELLOW);
                    nAthlete = MapAthlete_N.get(Name);
                    mLBLAthleteInfo.setText(String.valueOf(nAthlete));
                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int idx = mLSTAthletes.getSelectedIndex();

        if (!e.getValueIsAdjusting())
            return;
        if (idx < 0 || idx >= aAthletes.size())
            return;
        mLSTAthletes.ensureIndexIsVisible(idx);

        System.out.println(aAthletes);
        Athlete a = aAthletes.get(idx);
        mLBLAthleteInfo.setText("oop");
    }
}

