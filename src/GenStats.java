/**
 * Interface for generating random statistics
 * based on team averages. Is implemented within NBATeamSelect.
 * @author Iain Black and Griffen Marler
 * @version 1.00, 23 January 2019
 */
public interface GenStats {

    void genFGM(double a, double m, double a2, double m2);
    void genFTM(double a, double m);
    void genREB(double a);
    void genTOV(double a);
    void genTotalPTS();

}
