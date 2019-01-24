import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class uses custom made regex to
 * read in information from a website and
 * adds the information to two different arrays.
 */
public class FileReader {
    private static String regex = "(<td>)(\\d+[\\.]+[0-9])|(<td>)([\\.]\\d+)|(<td>)(\\d\\d)";        // Regex to find the stat numbers. 6 groups are created so we can access the information we want.
    private static String teamregex = "(Stats'>)(L.A.[ ]+\\w+)|(Stats'>)(\\w+ \\w+)|(Stats'>)(\\w+)";  // Regex to grab the team names in order. Again, 6 groups are created so we can access the information we want.
    public ArrayList <String> StatArray = new ArrayList();
    public ArrayList <String> TeamArray = new ArrayList();

    /**
     * The filereader constructor
     * takes in the URL of the NBA stat website
     * and grabs the information that we need.
     */
    public FileReader() {
        try {

            String line;
            URL url = new URL("https://basketball.realgm.com/nba/team-stats");
            BufferedReader rdr = new BufferedReader(new InputStreamReader(url.openStream()));
            line = rdr.readLine();
            while ((line = rdr.readLine()) != null) {
                Pattern stat = Pattern.compile(regex);
                Matcher match = stat.matcher(line);
                while (match.find()) {
                    if(match.group(2) != null) {                        // Go through the first regex finding statistics. If a group is not null, Add it to the correct array
                        StatArray.add(match.group(2));
                    }
                    if(match.group(4) != null) {
                        StatArray.add(match.group(4));
                    }
                    if(match.group(6) != null) {
                        StatArray.add(match.group(6));
                    }



                }

                Pattern Teamname = Pattern.compile(teamregex);
                Matcher TeamMatch = Teamname.matcher(line);
                while (TeamMatch.find()) {                      // Go through the second regex finding Team names in order. If a group is not null, add it to the correct array.
                    if(TeamMatch.group(2) != null) {

                        TeamArray.add((TeamMatch.group(2)));
                    }
                    if(TeamMatch.group(4) != null) {

                        TeamArray.add((TeamMatch.group(4)));
                    }
                    if(TeamMatch.group(6) != null) {
                        TeamArray.add((TeamMatch.group(6)));
                    }


                }
            }
        }catch(Exception ex) {
            ;
        }


    }

}
