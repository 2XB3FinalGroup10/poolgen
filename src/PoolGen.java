import util.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Zed
 * Date: 4/3/2015
 */
public class PoolGen {
    private static final String ACCEPTED_COMPETITOR_FILE_TYPES = "csv, txt";

    // Map of seeds to competitors
    private Map<Integer, Competitor> competitors;
    private File competitorsFile;
    private String competitorsFilePath;
    private int bracketSize;
    private int numExitCompetitors;

    public static void main(String[] args) {
        PoolGen poolGen = new PoolGen();

        poolGen.startGUI(poolGen);
        System.out.println("test");
    }

    private void startGUI(PoolGenView v) {
        // Start GUI
    }

    private void loadCompetitors() throws IOException, IllegalArgumentException {
        competitors = new HashMap<Integer, Competitor>();

        File competitorsFile = new File(this.competitorsFilePath);

        if (FileUtils.isValidFile(competitorsFile, ACCEPTED_COMPETITOR_FILE_TYPES)) {
            this.competitorsFile = competitorsFile;
        } else {
            throw new IllegalArgumentException("Input file is not valid or does not exist.");
        }

        BufferedReader br = new BufferedReader(new FileReader(this.competitorsFile));

        String line = br.readLine();

        while (line != null) {
            String[] parts = line.split(",");

            if (parts.length != 3) {
                throw new IllegalArgumentException("Input File is not valid.");
            }

            Competitor competitor = new Competitor(parts[0], Integer.parseInt(parts[1]), parts[2]);
            competitors.put(competitor.getRank(), competitor);
        }
    }

    private void generatePools() {
        // Pools get generated
    }

    public Map<Integer, Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(HashMap<Integer, Competitor> competitors) {
        this.competitors = competitors;
    }

    public String getCompetitorsFilePath() {
        return competitorsFilePath;
    }

    public void setCompetitorsFilePath(String competitorsFilePath) {
        this.competitorsFilePath = competitorsFilePath;
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
