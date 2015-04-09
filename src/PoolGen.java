import util.FileUtils;

import java.io.File;
import java.util.HashMap;

/**
 * Author: Zed
 * Date: 4/3/2015
 */
public class PoolGen {
<<<<<<< HEAD
    // Map of seeds to competitors
    private HashMap<Integer, Competitor> competitors;
    private File competitorsFile;
=======
    private static final String ACCEPTED_COMPETITOR_FILE_TYPES = "csv, txt";

    // Map of seeds to competitors
    private HashMap<Integer, Competitor> competitors;
    private File competitorsFile;
    private String competitorsFilePath;
>>>>>>> c130fbc7b9dcd98eb43f0deddf5090ab8b96d1c3
    private int bracketSize;
    private int numExitCompetitors;

    public static void main(String[] args) {
        PoolGen poolGen = new PoolGen();

        poolGen.startGUI();
    }

    private void startGUI() {
        // Start GUI
    }

<<<<<<< HEAD
=======
    private boolean loadCompetitors() {
        File competitorsFile = new File(this.competitorsFilePath);

        if (FileUtils.isValidFile(competitorsFile, ACCEPTED_COMPETITOR_FILE_TYPES)) {
            this.competitorsFile = competitorsFile;
        } else {
            return false;
        }

        return true;
    }

>>>>>>> c130fbc7b9dcd98eb43f0deddf5090ab8b96d1c3
    private void generatePools() {
        // Pools get generated
    }

    public HashMap<Integer, Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(HashMap<Integer, Competitor> competitors) {
        this.competitors = competitors;
    }

<<<<<<< HEAD
    public File getCompetitorsFile() {
        return competitorsFile;
    }

    public void setCompetitorsFile(File competitorsFile) {
        this.competitorsFile = competitorsFile;
=======
    public String getCompetitorsFilePath() {
        return competitorsFilePath;
    }

    public void setCompetitorsFilePath(String competitorsFilePath) {
        this.competitorsFilePath = competitorsFilePath;
>>>>>>> c130fbc7b9dcd98eb43f0deddf5090ab8b96d1c3
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
