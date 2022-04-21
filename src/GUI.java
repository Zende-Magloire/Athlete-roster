import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

public class GUI extends JFrame implements ListSelectionListener, ActionListener {
    public final String ATHLETE_DATA = "res\\database.csv";
    private static Map<String, Athlete> MapAthlete = new HashMap<>();
    private static Map<String, Athlete> MapAthlete_N = new HashMap<>();
    private MyList<Athlete> aAthletes;
    private DefaultListModel<String> AthleteData;

    //GUI
    private JList<String> mLSTAthletes;
    JLabel mLBLAthleteInfo;
    JTextField LBL_ID;
    JButton BTN_Search;
    JButton BTN_N_Search;
    JTextField LBL_name;
    JButton BTNreview;

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
        aAthletes = new MyList<>();
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
        LBL_ID = new JTextField(4);
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
        add(TopPanel, BorderLayout.EAST);

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
        BTNreview = new JButton("Last viewed athlete");
        BTNreview.addActionListener(this);
        infoPanel.add(mLBLAthleteInfo);
        infoPanel.add(BTNreview);
        add(infoPanel, BorderLayout.AFTER_LAST_LINE);
       // add(mLBLAthleteInfo, BorderLayout.EAST);

        //add to roster
        JPanel BottomPanel = new JPanel();
        JButton BTN_Add = new JButton("Add new athlete");
        BottomPanel.add(BTN_Add);
      //  add(BottomPanel, BorderLayout.PAGE_END);

        setLocationRelativeTo(null);
        setVisible(true);
     }

    private void loadAthletes(String ATHLETE_DATA)
    {
        //loading
        File file = new File("res/database.csv");
        Scanner reader;

        try
        {
            reader = new Scanner(file);

            MyList<Athlete> aAthletes = new MyList<>();

            reader.nextLine();
            while (reader.hasNext()) {
                String line = reader.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line, ",");

                int ID = Integer.parseInt(tokenizer.nextToken());
                String FirstName = tokenizer.nextToken();
                String LastName = tokenizer.nextToken();
                int age = Integer.parseInt((tokenizer.nextToken()));
                String race = String.valueOf(tokenizer.nextToken());
                String PB = String.valueOf(tokenizer.nextToken());
                String SB = String.valueOf(tokenizer.nextToken());

                //adding
                Athlete a = new Athlete(ID, FirstName, LastName, age, race, PB, SB);
                aAthletes.append(a);
                MapAthlete.put(String.valueOf(a.getID()), a);
                String firstname = a.getFirstName().toUpperCase(Locale.ROOT);
                MapAthlete_N.put(firstname, a);
                //sorting
                insertionSort(aAthletes);
                }
            for (int i = 0; i < aAthletes.getSize(); i++)
            {
                Athlete ab = aAthletes.get(i);
              //  System.out.println(ab);
                String a = ab.getID() + ": " + ab.getFirstName() + " " + ab.getLastName();
                AthleteData.addElement(a);
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "ERROR! Couldn't load athlete file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void insertionSort(MyList<Athlete> data)
        {
        int n = data.getSize();
        int numSorted = 1;
        int index;
        while (numSorted < n){
            Athlete temp = data.get(numSorted);
            for (index = numSorted; index > 0; index--){
                if (temp.getID() < (data.get(index-1)).getID()){
                    data.set(index, data.get(index-1));
                } else {
                    break;
                }
            }
            data.set(index, temp);
            numSorted++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Stack<Athlete> mStack = new Stack<>();
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;

            if (button == BTN_Search) {

                String id = LBL_ID.getText();
                Athlete athlete = MapAthlete.get(id);
                mStack.push(athlete);
                System.out.println(mStack);

                if (!MapAthlete.containsKey(id)) {
                    mLBLAthleteInfo.setOpaque(true);
                    mLBLAthleteInfo.setBackground(Color.RED);
                    mLBLAthleteInfo.setText(id + " is not an ID number of any of our athletes! Try again!");
                }
                else
                {
                    mLBLAthleteInfo.setOpaque(true);
                    mLBLAthleteInfo.setBackground(Color.YELLOW);
                    athlete = MapAthlete.get(id);
                    mLBLAthleteInfo.setText(String.valueOf(athlete));
                }
                LBL_ID.setText("");
            }
            else if (button == BTN_N_Search) {
                String name = LBL_name.getText();
                String Name = name.toUpperCase(Locale.ROOT);
                Athlete nAthlete = MapAthlete_N.get(Name);
                mStack.push(nAthlete);

                if (!MapAthlete_N.containsKey(Name)) {
                    mLBLAthleteInfo.setOpaque(true);
                    mLBLAthleteInfo.setBackground(Color.RED);
                    mLBLAthleteInfo.setText(Name + " is not one of our athletes! Please enter student's first name only..");
                } else
                {
                    mLBLAthleteInfo.setOpaque(true);
                    mLBLAthleteInfo.setBackground(Color.YELLOW);
                    nAthlete = MapAthlete_N.get(Name);
                    mLBLAthleteInfo.setText(String.valueOf(nAthlete));
                }
                LBL_name.setText("");
            }
            else if (button == BTNreview)
            {
               // if (!mStack.isEmpty())
                //{
                    System.out.println(mStack);
                   // System.out.println(mStack.pop());
                    //Athlete last = mStack.pop();
                    //mLBLAthleteInfo.setText(String.valueOf(last));
//                }
//                else
//                {
//                    mLBLAthleteInfo.setBackground(Color.red);
//                    mLBLAthleteInfo.setText("This is your first search!");
//                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int idx = mLSTAthletes.getSelectedIndex();

        if (e.getValueIsAdjusting())
            return;
        if (idx < 0 || idx >= aAthletes.getSize())
            return;

        mLSTAthletes.ensureIndexIsVisible(idx);

        Athlete a = aAthletes.get(idx);
        mLBLAthleteInfo.setText(String.valueOf(a));
    }
}

