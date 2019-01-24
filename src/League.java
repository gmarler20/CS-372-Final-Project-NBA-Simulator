import java.util.ArrayList;

/**
 * This class models a league of teams as it takes
 * in information read from a website and creates
 * all 30 teams in the NBA based off of this information.
 * By iterating through the array in a pattern that retrieves only
 * the information necessary to create an NBA Team object.
 * @author Iain Black and Griffen Marler
 * @version 1.00, 23 January 2019
 */
public class League {
    private FileReader NBAReader = new FileReader();
    public ArrayList<NBATeam> NBATeams = new ArrayList<>();

    /**
     * Gets the arraylist
     * @return arraylist of all NBAteams
     */
    public ArrayList getNBATeams() {
        return NBATeams;
    }

    public League() {
        genTeams(NBAReader);
    }

    /**
     * Reads file and dynamically creates NBA Teams objects,
     * assigning variables based on information given in the
     * file. Also creates two East and West All Star teams.
     * @param rdr Passes is in the information read from online regarding the statistics
     */
    public void genTeams(FileReader rdr) {
        int teamcount = 0;
        int i = 2;

        try {

            while (teamcount < 30) {
                NBATeam t = new NBATeam();                          // Because information within the file is stored the same way for all teams,
                                                                    // we created an iteration pattern that will always return the correct values.
                t.setTeamName(rdr.TeamArray.get(teamcount));
                t.setFGp(Double.parseDouble(rdr.StatArray.get(i)));
                i++;
                t.setFGa(Double.parseDouble(rdr.StatArray.get(i)));
                i = i + 2;
                t.setThreePTp(Double.parseDouble(rdr.StatArray.get(i)));
                i++;
                t.setThreePTa(Double.parseDouble(rdr.StatArray.get(i)));
                i = i + 2;
                t.setFTp(Double.parseDouble(rdr.StatArray.get(i)));
                i++;
                t.setFTa(Double.parseDouble(rdr.StatArray.get(i)));
                i = i + 2;
                t.setTOV(Double.parseDouble(rdr.StatArray.get(i)));
                i = i + 4;
                t.setREB(Double.parseDouble(rdr.StatArray.get(i)));
                NBATeams.add(t);
                teamcount++;
                if (teamcount == 30) {                                  // Once all teams are created, break out of the loop so an exception is not thrown
                    break;
                } else
                    i = i + 7;
            }

            NBATeam East = new NBATeam();                           // Create East All Star team
            East.setTeamName("East All Stars");
            East.setFGp(60.1);
            East.setFGa(90.4);
            East.setThreePTp(18.4);
            East.setThreePTa(45.3);
            East.setFTp(15);
            East.setFTa(20);
            East.setTOV(5.6);
            East.setREB(49.1);

            NBATeams.add(East);

            NBATeam West = new NBATeam();                       // Create West All Star team
            West.setTeamName("West All Stars");
            West.setFGp(65.1);
            West.setFGa(99.1);
            West.setThreePTp(19.3);
            West.setThreePTa(43.1);
            West.setFTp(11);
            West.setFTa(18);
            West.setTOV(9.4);
            West.setREB(55.3);

            NBATeams.add(West);


        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
}
