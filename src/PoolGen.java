import util.FileUtils;

import java.io.File;
import java.util.HashMap;

/**
 * Author: Zed
 * Date: 4/3/2015
 */
public class PoolGen {
    // Map of seeds to competitors
    private HashMap<Integer, Competitor> competitors;
    private File competitorsFile;
    private int bracketSize;
    private int numExitCompetitors;

    public static void main(String[] args) {
        PoolGen poolGen = new PoolGen();

        poolGen.startGUI();
    }

    private void startGUI() {
        // Start GUI
    }

    private void generatePools() {
        // Pools get generated
    }

    public HashMap<Integer, Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(HashMap<Integer, Competitor> competitors) {
        this.competitors = competitors;
    }

    public File getCompetitorsFile() {
        return competitorsFile;
    }

    public void setCompetitorsFile(File competitorsFile) {
        this.competitorsFile = competitorsFile;
    }

    public int getBracketSize() {
        return bracketSize;
    }

    public void setBracketSize(int bracketSize) {
        this.bracketSize = bracketSize;
    }

    public int getNumExitCompetitors() {
        return numExitCompetitors;
    }

    public void setNumExitCompetitors(int numExitCompetitors) {
        this.numExitCompetitors = numExitCompetitors;
    }
}
