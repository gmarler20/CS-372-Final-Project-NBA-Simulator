import java.text.DecimalFormat;
import java.util.Random;


/**
 * This class inherits from NBATeam and implements GenStats.
 * By including an NBATeam object in its constructor, the information
 * is passed into the TeamSelectObject, this is where the random statistics will be generated
 * without effecting the original NBATeam object.
 */
public class NBATeamSelect extends NBATeam implements GenStats{

    private NBATeam T;
    private int TotalPTS;
    private int madeTwoPointers, madeThreePointers, madeFreeThrows, madeFGs;

    /**
     * Generates statistics for the simulation
     * taking in the NBATeam in order to manipulate
     * the statstics.
     * @param t NBATeam
     */
    public NBATeamSelect(NBATeam t){
        T = t;
        this.setTeamName(t.getTeamName());
        genFGM(T.getFGa(),T.getFGp(),T.getThreePTa(), T.getThreePTp());
        genFTM(T.getFTa(), T.getFTp());
        genREB(T.getREB());
        genTOV(T.getTOV());
        genTotalPTS();
    }

    /**
     * Generates a multidimensional genGameStats array to
     * display information within the JTable.
     * https://www.geeksforgeeks.org/java-swing-jtable/
     * Helped us understand how to implement this concept.
     * @return GameStats multidimensional Array
     */
    public String[][] genGameStats() {
        double FGPercent = (this.getFGp()/this.getFGa()) * 100;
        double ThreePercent = (this.getThreePTp()/ this.getThreePTa()) * 100;
        double FTPercent = (this.getFTp() / this.getFTa()) * 100;

        DecimalFormat formatter = new DecimalFormat("#00.0");



        String[][] GameStats = {{"FG %: ", formatter.format(FGPercent) + "%"},
                {"3PT %: ", formatter.format(ThreePercent) + "%"},
                {"FT %: ", formatter.format(FTPercent) + "%"},
                {"REB: ", Double.toString(this.getREB())},
                {"TOV: ", Double.toString(this.getTOV())}};
            return GameStats;
    }

    /**
     * Generates colnames which is needed to initialize JTable
     * @return String Array of gamecolNames
     */
    public String[] genGamecolNames() {
        String[] gameColNames = {this.getTeamName(), "Stat Summary"};
        return gameColNames;
    }

    /**
     * This function passes in AVG FG/3PT attempts and makes.
     * Generates random number for each variable and ranges for
     * each variable. Creates random number between the range surrounding
     * the averages for the variables and sets them to new int variables.
     * @param avgFGAttempts FG attempts
     * @param avgFGMakes FG makes
     * @param avgThreeAttempts three attempts
     * @param avgThreeMakes three makes
     */
    public void genFGM(double avgFGAttempts, double avgFGMakes, double avgThreeAttempts, double avgThreeMakes){
        Random FGMakernd = new Random();
        Random FGAttemptrnd = new Random();
        Random ThreeMakernd = new Random();
        Random ThreeAttemptrnd = new Random();

        double FGMakerange = 8;
        double FGAttemptrange = 5;
        double ThreeMakeRange = 6;
        double ThreeAttemptRange = 3;

        double FGMakeMax = avgFGMakes + FGMakerange;
        double FGMakeMin = avgFGMakes - FGMakerange;
        double FGAttemptMax = avgFGAttempts + FGAttemptrange;
        double FGAttemptMin = avgFGAttempts - FGAttemptrange;

        double ThreeMakeMax = avgThreeMakes + ThreeMakeRange;
        double ThreeMakeMin = avgThreeMakes - ThreeMakeRange;
        double ThreeAttemptMax = avgThreeAttempts + ThreeAttemptRange;
        double ThreeAttemptMin = avgThreeAttempts - ThreeAttemptRange;

        int FGAttemptnum = FGAttemptrnd.nextInt(((int)FGAttemptMax - (int)FGAttemptMin) +1) + (int)FGAttemptMin;
        int FGMakenum = FGMakernd.nextInt(((int)FGMakeMax - (int)FGMakeMin) +1)+(int)FGMakeMin;

        int ThreeAttemptnum = ThreeAttemptrnd.nextInt(((int)ThreeAttemptMax - (int)ThreeAttemptMin)+1) + (int)ThreeAttemptMin;
        int ThreeMakenum = ThreeMakernd.nextInt(((int)ThreeMakeMax - (int)ThreeMakeMin)+1) + (int)ThreeMakeMin;

        madeFGs = FGMakenum;
        madeThreePointers = ThreeMakenum;
        madeTwoPointers = (madeFGs - madeThreePointers);

        this.setThreePTp(madeThreePointers);
        this.setThreePTa(ThreeAttemptnum);
        this.setFGp(madeFGs);
        this.setFGa(FGAttemptnum);
    }

    /**
     * Repeats the random number generating process
     * using free throw attempts and makes.
     * @param avgAttempts Average attempts
     * @param avgMakes average makes
     */
    public void genFTM(double avgAttempts, double avgMakes){
        Random Mrnd = new Random();
        Random Arnd = new Random();

        double Mrange = 5;
        double Arange = 5;

        double mMax = avgMakes + Mrange;
        double mMin = avgMakes - Mrange;

        double aMax = avgAttempts + Arange;
        double aMin = avgAttempts - Arange;

        int Anum = Arnd.nextInt(((int)aMax - (int)aMin) +1) + (int)aMin;
        int Mnum = Mrnd.nextInt(((int)mMax - (int)mMin) +1) + (int)mMin;

       madeFreeThrows = Mnum;
       this.setFTp(madeFreeThrows);
       this.setFTa(Anum);
    }

    /**
     * Generates rebounds by using the
     * random generating process.
     * @param avg rebound avg
     */
    public void genREB(double avg){
        Random rnd = new Random();

        double max = avg + 10;
        double min = avg - 10;

        int num = rnd.nextInt(((int)max - (int)min) +1);
        this.setREB(num + (int)min);
    }

    /**
     * Generates turnovers by using
     * the random generating process.
     * @param avg turnover avg
     */
    public void genTOV(double avg){
        Random rnd = new Random();

        double max = avg + 3;
        double min = avg - 3;

        int num = rnd.nextInt(((int)max - (int)min) +1) + (int)min;
        this.setTOV(num);
    }

    /**
     * Totals up all the points for each team
     */
    public void genTotalPTS(){
        TotalPTS = ((madeTwoPointers * 2) + (madeThreePointers * 3) + (madeFreeThrows));
    }

    /**
     * Gets total points for each team
     * @return Total points
     */
    public int getTotalPTS() {
        return TotalPTS;
    }

    /**
     * Adds t amount of points to a team in
     * order to break a tie
     * @param t amount of points to add
     */
    public void setTiebreaker(int t) {
        TotalPTS = TotalPTS + t;
    }

   /*
    public void TestTotalPts(int t) {
        TotalPTS = t;
    }*/

    /**
     * Used to add an extra two pointer to a team
     * @param i amount to add
     */
    public void AddFG(int i) {
        madeTwoPointers = madeTwoPointers + i;
    }
}
