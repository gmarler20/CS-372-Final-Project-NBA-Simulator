

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import sun.audio.*;

import sun.audio.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * This class creates the user interface for our project.
 * @author Iain Black and Griffen Marler
 * @version 1.00, 23 January 2019
 */
public class GameSimulatorGUI extends JFrame {
    private League NBA = new League();
    private JFrame OpeningFrame;
    private JLayeredPane OpeningPane;
    private JLayeredPane MatchupPane;
    private JFrame SimulationFrame;
    private JLayeredPane SimulationPane;
    private JLabel SimulationPaneBackground;
    private JLabel SimulationTeamLogo1;
    private JLabel SimulationTeamLogo2;
    private JLabel ScoreLabelTeam1;
    private JLabel ScoreLabelTeam2;
    private JLabel MatchupPaneBackground;
    private JLabel OpeningBackground;
    private JLabel OpeningTitle;
    private JLabel LeftLabel;
    private JLabel RightLabel;
    private JButton OpeningStartButton;
    private JButton SimulateButton;
    private JButton ReturnFromSimulation;
    private JList<String> TeamList1;
    private JList<String> TeamList2;
    private JLabel NBALOGO;
    private JLabel TeamLogo1;
    private JLabel TeamLogo2;
    private JTable StatTable1;
    private JTable StatTable2;
    private JTable SimulationTable1;
    private JTable SimulationTable2;
    int framecount = 0;
    private JFrame TeamSelectFrame;
    private JPanel VisualPanel;
    private JPanel LeftListPanel;
    private ArrayList<NBATeamSelect> SelectedTeams = new ArrayList<>();


    GameSimulatorGUI(){
        GenOpeningComponents();             // Generates components to initialize program
        OpeningFrame.setVisible(true);
    }

    /**
     * This function generates all of the Jcomponents for the
     * initial JFrame that is displayed when our project is
     * launched. The frame, layeredpane, labels, and buttons
     * are declared and manipulated before being added to the
     * frame.
     */
    public void GenOpeningComponents(){
        OpeningFrame = new JFrame("NBA Simulator");
        OpeningFrame.setSize(new Dimension(1000,600));
        OpeningFrame.setLayout(new FlowLayout());
        OpeningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        OpeningPane = new JLayeredPane();
        OpeningPane.setPreferredSize(new Dimension(1000,600));
        OpeningFrame.add(OpeningPane);


        OpeningBackground = new JLabel(genImage("openingbackground.png",1000,600));
        OpeningBackground.setBounds(0,0,1000,600);
        OpeningPane.add(OpeningBackground, new Integer(0));

        OpeningTitle = new JLabel("NBA Simulator");
        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);             // Adds border to label
        OpeningTitle.setFont(new Font("Garamond", Font.BOLD, 68));
        OpeningTitle.setBounds(275,100,500,300);
        OpeningTitle.setForeground(Color.white);
        OpeningTitle.setBackground(Color.black);
        OpeningPane.add(OpeningTitle,new Integer(1));

        OpeningStartButton = new JButton("Start");
        OpeningStartButton.setBounds(450,350,100,50);
        OpeningStartButton.setForeground(Color.BLACK);
        OpeningStartButton.setFont(new Font("Garamond", Font.BOLD, 24));
        OpeningPane.add(OpeningStartButton, new Integer(1));
        OpeningStartButton.addActionListener(new ActionListener() {                     // Adds actionlistener to button in order to transition to second JFrame
            @Override
            public void actionPerformed(ActionEvent e) {
                GenTeamSelectComponents();
                OpeningFrame.setVisible(false);
                TeamSelectFrame.setVisible(true);
            }
        });
        OpeningPane.revalidate();
        OpeningPane.repaint();
    //    music("NBA");
    }

    /**
     * This function generates all of the components
     * for the second JFrame that allows the user to select
     * two teams to simulate a game. This function contains many
     * of the same components, additionally it utilizes JTables
     * to display team statistics.
     */
    public void GenTeamSelectComponents(){

        TeamSelectFrame = new JFrame();
        TeamSelectFrame.setSize(new Dimension(1000,620));
        TeamSelectFrame.setLayout(new FlowLayout());
        TeamSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MatchupPane = new JLayeredPane();
        MatchupPane.setPreferredSize(new Dimension(1000, 620));
        MatchupPaneBackground = new JLabel(genImage("MatchupBackground.jpg", 1000, 620));
        MatchupPaneBackground.setBounds(0,0,1000,620);
        MatchupPane.add(MatchupPaneBackground, new Integer(0));



        ListModel LeftList = new DefaultListModel<>();
        for(int i = 0; i < NBA.getNBATeams().size(); i++) {
            ((DefaultListModel) LeftList).addElement(NBA.NBATeams.get(i).getTeamName());            // Adds all the team names stored in League to list

        }

        TeamList1 = new JList(LeftList);                                                    // Creates list and sets parameters to team names.
        TeamList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TeamList1.setLayoutOrientation(JList.VERTICAL);
        TeamList1.setBackground(Color.darkGray);
        TeamList1.setForeground(Color.white);
        TeamList1.setOpaque(true);
        TeamList1.setVisibleRowCount(-1);
        TeamList1.setSelectedIndex(31);                                                     // Sets initial selection to team we want it to



        JScrollPane listscroller = new JScrollPane(TeamList1);
        listscroller.setPreferredSize(new Dimension(125, 620));
        listscroller.setBounds(10,0,100,620);
        listscroller.setVisible(true);
        MatchupPane.add(listscroller, new Integer(10));


        ListModel RightList = new DefaultListModel<>();
        for(int i = 0; i < NBA.getNBATeams().size(); i++) {
            ((DefaultListModel) RightList).addElement(NBA.NBATeams.get(i).getTeamName());      // Adds teams to second list
        }

        TeamList2 = new JList(RightList);
        TeamList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TeamList2.setLayoutOrientation(JList.VERTICAL);
        TeamList2.setBackground(Color.darkGray);
        TeamList2.setForeground(Color.white);
        TeamList2.setOpaque(true);
        TeamList2.setVisibleRowCount(-1);
        TeamList2.setSelectedIndex(30);                                                // Sets initial selection to team we want it to


        JScrollPane listscroller2 = new JScrollPane(TeamList2);
        listscroller2.setPreferredSize(new Dimension(125, 620));
        listscroller2.setBounds(890, 0, 100, 620);
        listscroller2.setVisible(true);
        MatchupPane.add(listscroller2, new Integer(10));

        SimulateButton = new JButton();
        SimulateButton.setText("Simulate");
        SimulateButton.setPreferredSize(new Dimension(125,50));
        SimulateButton.setBounds(450,400,125,50);
        SimulateButton.setForeground(Color.BLACK);
        SimulateButton.setFont(new Font("Garamond", Font.BOLD, 24));
        SimulateButton.setVisible(true);
        MatchupPane.add(SimulateButton, new Integer(9));

        NBALOGO = new JLabel(genImage("nba logo.png", 75, 150));
        NBALOGO.setBounds(473, 0, 75, 150);
        MatchupPane.add(NBALOGO, new Integer(3));

        TeamLogo1 = new JLabel(genImage("West All Stars.png", 200,200));            // Set the default image on the left side to west all stars
        TeamLogo1.setBounds(192, 140, 200, 200);
        MatchupPane.add(TeamLogo1, new Integer(4));

        TeamLogo2 = new JLabel(genImage("East All Stars.png", 220, 220));           // Set the default image on the right side to east all stars
        TeamLogo2.setBounds(604, 144, 220,220);
        MatchupPane.add(TeamLogo2, new Integer(4));

        StatTable1 = new JTable(NBA.NBATeams.get(30).genStats(),NBA.NBATeams.get(30).genColNames());    // Creates table based off stats we selected to be displayed
        StatTable1.setBounds(180,365,400,250);
        StatTable1.setOpaque(false);
        StatTable1.setForeground(Color.white);
        StatTable1.setRowHeight(25);
        StatTable1.setRowMargin(5);
        ((DefaultTableCellRenderer)StatTable1.getDefaultRenderer(Object.class)).setOpaque(false);  // Sets the table to be transparent.
        StatTable1.setShowGrid(false);                                                              // https://stackoverflow.com/questions/11609900/how-to-make-the-background-of-a-jtable-transparent assisted us in discovering how to do this.
        MatchupPane.add(StatTable1, new Integer(5));


        StatTable2 = new JTable(NBA.NBATeams.get(31).genStats(),NBA.NBATeams.get(31).genColNames());    // Creates second table for other side
        StatTable2.setBounds(605,365,400,250);
        StatTable2.setOpaque(false);
        StatTable2.setForeground(Color.white);
        StatTable2.setRowHeight(25);
        StatTable2.setRowMargin(5);
        ((DefaultTableCellRenderer)StatTable2.getDefaultRenderer(Object.class)).setOpaque(false);
        StatTable2.setShowGrid(false);
        MatchupPane.add(StatTable2, new Integer(5));



        TeamSelectFrame.setVisible(true);
        TeamSelectFrame.add(MatchupPane);
        TeamSelectFrame.revalidate();
        TeamSelectFrame.repaint();
        addMatchupPaneListeners();

    }

    /**
     * This function generates all the components for the third and final JFrame which
     * is displayed upon simulation. It also utilizes JTables and includes a button
     * that allows you to go back to the 2nd frame once you are done looking
     * at the current simulation.
     * @param Teams
     */
    public void GenSimulationComponents(ArrayList<NBATeamSelect> Teams) {
        SimulationFrame = new JFrame("Game Simulation");
        SimulationFrame.setSize(new Dimension(1000,600));
        SimulationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SimulationPane = new JLayeredPane();
        SimulationPane.setPreferredSize(new Dimension(1000, 600));
        SimulationFrame.add(SimulationPane);

        SimulationPaneBackground = new JLabel(genImage("SimulationBackground.png", 1000, 600));
        SimulationPaneBackground.setBounds(0,0,1000,600);
        SimulationPane.add(SimulationPaneBackground, new Integer(0));



        ScoreLabelTeam1 = new JLabel(genImage("ScoreboardBackground.png", 250, 100));
        ScoreLabelTeam1.setText(Integer.toString(Teams.get(0).getTotalPTS()) + "  -  " + Integer.toString(Teams.get(1).getTotalPTS())); // Creates scoreboard text
        ScoreLabelTeam1.setBounds(375, 70, 250, 100);
        ScoreLabelTeam1.setHorizontalTextPosition(JLabel.CENTER);
        ScoreLabelTeam1.setForeground(Color.RED);
        ScoreLabelTeam1.setFont(new Font("Garamond", Font.BOLD, 50));
        SimulationPane.add(ScoreLabelTeam1, new Integer(2));

        SimulationTeamLogo1 = new JLabel(genImage(Teams.get(0).getTeamName() +".png", 200,200));
        SimulationTeamLogo1.setBounds(160, 27, 200, 200);
        SimulationPane.add(SimulationTeamLogo1, new Integer(2));

        SimulationTeamLogo2 = new JLabel(genImage(Teams.get(1).getTeamName() +".png", 200, 200));
        SimulationTeamLogo2.setBounds(650, 27, 200, 200);
        SimulationPane.add(SimulationTeamLogo2, new Integer(2));


        SimulationTable1 = new JTable(Teams.get(0).genGameStats(), Teams.get(0).genColNames());             // Creates another Jtable using the same method as before
        SimulationTable1.setBounds(130, 300, 450, 225);
        SimulationTable1.setOpaque(false);
        SimulationTable1.setForeground(Color.BLACK);
        SimulationTable1.setRowHeight(40);
        SimulationTable1.setRowMargin(0);
        ((DefaultTableCellRenderer)SimulationTable1.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
        SimulationTable1.setShowGrid(false);
        SimulationTable1.setFont(new Font("Garamond", Font.BOLD, 28));
        SimulationPane.add(SimulationTable1, new Integer(5));

        SimulationTable2 = new JTable(Teams.get(1).genGameStats(), Teams.get(1).genColNames());             // JTable for other side
        SimulationTable2.setBounds(570, 300, 450, 225);
        SimulationTable2.setOpaque(false);
        SimulationTable2.setForeground(Color.BLACK);
        SimulationTable2.setRowHeight(40);
        SimulationTable2.setRowMargin(0);
        ((DefaultTableCellRenderer)SimulationTable2.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
        SimulationTable2.setShowGrid(false);
        SimulationTable2.setFont(new Font("Garamond", Font.BOLD, 28));
        SimulationPane.add(SimulationTable2, new Integer(5));

        ReturnFromSimulation = new JButton();
        ReturnFromSimulation.setText("Done");
        ReturnFromSimulation.setPreferredSize(new Dimension(100, 50));
        ReturnFromSimulation.setForeground(Color.BLACK);
        ReturnFromSimulation.setFont(new Font("Garamond", Font.BOLD, 24));
        ReturnFromSimulation.setBounds(850, 510, 100, 50);
        SimulationPane.add(ReturnFromSimulation, new Integer(6));

        addSimulationPaneListeners();
        SimulationFrame.revalidate();
        SimulationFrame.repaint();
        SimulationFrame.setVisible(true);


    }

    /**
     * This function allows easy creation of Labels contaiing images
     * by returning an imageIcon which then can be conveniently be called into an image.
     * @param s Title of the image stored in resources
     * @param w width of image to scale to
     * @param h height of image to scale to
     * @return
     */
    public ImageIcon genImage(String s, int w, int h){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        URL imgURL = getClass().getResource("/Resources/"+ s);
        Image image = toolkit.getImage(imgURL);
        image = image.getScaledInstance(w,h,Image.SCALE_SMOOTH);
        ImageIcon imageIcon= new ImageIcon(image);
        return imageIcon;

    }
/*
    public void music(String name){
        try {
            String soundfile = "/cs1/2020/gmarler20/CS-372-1-JavaAppDev/LAtestVersionFinal/src/Resources/" + name +".mp3";
            InputStream in = new FileInputStream(soundfile);

            AudioStream stream = new AudioStream(in);

            AudioPlayer.player.start(stream);
        }catch (Exception ex){System.out.println(ex.getCause());}
    }*/

    /**
     * This function controls the button to return from the 3rd frame
     * back to the second frame so another simulation can commence.
     */
    public void addSimulationPaneListeners() {
            ReturnFromSimulation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SelectedTeams.remove(1);            // When the user returns from the simmulation, remove the teams from the array.
                    SelectedTeams.remove(0);

                    SimulationFrame.setVisible(false);
                    GenTeamSelectComponents();


                }
            });
    }

    /**
     * This function adds all of the listeners
     * for the matchup frame. Allows teams to be selected
     * and display their logo and information. This function
     * also adds a simulate button that upon click will
     * get the object values for both teams and add them
     * to an array that will be utilized to generate their
     * game statistics for the simulation screen. This function
     * also sets the current frame visibility to false at the end
     * in order to transition to the simulation frame.
     */
    public void addMatchupPaneListeners() {
        MouseListener mouseListener = new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String selectedItem = TeamList1.getSelectedValue();             // Set string to value of selected list member
                for (int i = 0; i < NBA.NBATeams.size(); i++) {
                    if (selectedItem == NBA.NBATeams.get(i).getTeamName()) {    // Compare to members of NBATeams arraylist to retrieve that team's information.
                        TeamLogo1.setIcon(genImage(NBA.NBATeams.get(i).getTeamName() + ".png", 200, 200));  // Set the icon to the user selected team
                        StatTable1.setVisible(false);
                        StatTable1 = new JTable(NBA.NBATeams.get(i).genStats(),NBA.NBATeams.get(i).genColNames());      // Create table displaying the information for the user selected team
                        StatTable1.setBounds(180,365,400,250);
                        StatTable1.setOpaque(false);
                        StatTable1.setForeground(Color.white);
                        StatTable1.setRowHeight(25);
                        StatTable1.setRowMargin(5);
                        ((DefaultTableCellRenderer)StatTable1.getDefaultRenderer(Object.class)).setOpaque(false);
                        StatTable1.setShowGrid(false);
                        MatchupPane.add(StatTable1, new Integer(5));
                        StatTable1.setVisible(true);
                        TeamSelectFrame.revalidate();
                        TeamSelectFrame.repaint();

                    }
                }
            }

        };
        TeamList1.addMouseListener(mouseListener);

        MouseListener List2Listener = new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param a
             */
            @Override
            public void mouseClicked(MouseEvent a) {
                super.mouseClicked(a);

                String selectedItem = TeamList2.getSelectedValue();                 // Same process, but for the team on the right side
                for (int i = 0; i < NBA.NBATeams.size(); i++) {
                    if (selectedItem == NBA.NBATeams.get(i).getTeamName()) {
                        TeamLogo2.setIcon(genImage(NBA.NBATeams.get(i).getTeamName() + ".png", 200, 200));
                        StatTable2.setVisible(false);
                        StatTable2 = new JTable(NBA.NBATeams.get(i).genStats(),NBA.NBATeams.get(i).genColNames());
                        StatTable2.setBounds(605,365,400,250);
                        StatTable2.setOpaque(false);
                        StatTable2.setForeground(Color.white);
                        StatTable2.setRowHeight(25);
                        StatTable2.setRowMargin(5);
                        ((DefaultTableCellRenderer)StatTable2.getDefaultRenderer(Object.class)).setOpaque(false);                          //referenced online (link emailed)
                        StatTable2.setShowGrid(false);
                        MatchupPane.add(StatTable2, new Integer(5));
                        StatTable2.setVisible(true);
                        TeamSelectFrame.revalidate();
                        TeamSelectFrame.repaint();
                    }
                }
            }

        };
        TeamList2.addMouseListener(List2Listener);
        SimulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ;
                String selectedTeam1 = TeamList1.getSelectedValue();
                String selectedTeam2 = TeamList2.getSelectedValue();
                if(selectedTeam1 == selectedTeam2) {    // Checks if selected team on each side is the same team
                                                        // If the teams are the same, display pop up box telling the user they cant do that.
                    JOptionPane.showMessageDialog(null, "You cannot simulate a game between the same teams!");
                }
                else {                                  // Otherwise, create a new NBATeamSelect object, passing in one of the selected teams
                                                        // to transfer its information. The selected team is then added to SelectedTeams ArrayList.
                    for (int i = 0; i < NBA.NBATeams.size(); i++) {
                        if (selectedTeam1 == NBA.NBATeams.get(i).getTeamName()) {
                            SelectedTeams.add(new NBATeamSelect(NBA.NBATeams.get(i)));
                        }
                    }
                                                        // Adds the other selected team to SelectedTeams.
                    for (int j = 0; j < NBA.NBATeams.size(); j++) {
                        if (selectedTeam2 == NBA.NBATeams.get(j).getTeamName()) {
                            SelectedTeams.add(new NBATeamSelect(NBA.NBATeams.get(j)));
                        }
                    }
                                                        // If the game is a tie
                    if(SelectedTeams.get(0).getTotalPTS() == SelectedTeams.get(1).getTotalPTS()) {
                        System.out.println("tie");
                        Random r = new Random();

                        int rando = r.nextInt(2);       // Create random number to determine winner

                        if(rando == 0) {
                            SelectedTeams.get(0).setTiebreaker(2);      // Adds two points to randomly selected team
                        }
                        else {
                            SelectedTeams.get(1).setTiebreaker(2);
                        }


                    }
                    GenSimulationComponents(SelectedTeams); // Calls GenSimulationComponents, passing in the current array of SelectedTeams.

                    TeamSelectFrame.setVisible(false);
                }
            }
        });

    }


}
