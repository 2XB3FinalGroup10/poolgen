import java.io.File;
import java.util.Map;

/**
 * Author: Zed
 * Date: 4/3/2015
 */
public class PoolGenModel {
    // Map of seeds to competitors
    private Map<Integer, Competitor> competitors;
    private File competitorsFile;
    private String competitorsFilePath;
    private int bracketSize;
    private int numExitCompetitors;

    public Map<Integer, Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(Map<Integer, Competitor> competitors) {
        this.competitors = competitors;
    }

    public File getCompetitorsFile() {
        return competitorsFile;
    }

    public void setCompetitorsFile(File competitorsFile) {
        this.competitorsFile = competitorsFile;
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
