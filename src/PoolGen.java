import util.FileUtils;
import util.StringUtils;

import java.io.*;
import java.util.*;

/**
 * Author: Zed
 * Date: 4/3/2015
 */
public class PoolGen {
    private static final String ACCEPTED_COMPETITOR_FILE_TYPES = "csv,txt";

    private final PoolGenModel model = new PoolGenModel();

    public static void main(String[] args) {
        PoolGen poolGen = new PoolGen();

        if (args.length == 0) {
            poolGen.startGUI();
        } else {
            poolGen.startCMD(args);
        }
    }

    private void startGUI() {
        PoolGenView view = new PoolGenView();
    }

    private void startCMD(String[] args) {
        try {
            parseArgs(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            printUsage();
        }
    }

    /**
     * Parses arguments and populates the PoolGenModel
     *
     * @param args arguments passed to program start
     * @throws IllegalArgumentException
     */
    private void parseArgs(String[] args) throws IllegalArgumentException {
        if (args.length < 3) throw new IllegalArgumentException("Missing arguments");

        String competitorsFilePath = "", bracketSize = "", poolExitSize = "";

        try {
            for (String arg : args) {
                String opt = arg.substring(0, arg.indexOf('='));
                String val = arg.substring(arg.indexOf('=') + 1);

                switch (opt) {
                    case "--importFile":
                        competitorsFilePath = val;
                        break;
                    case "--bracketSize":
                        bracketSize = val;
                        break;
                    case "--poolExitSize":
                        poolExitSize = val;
                        break;
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid arguments.  Arguments must be formatted as: --option=value", e);
        }

        if (StringUtils.isAnyNullOrEmpty(competitorsFilePath, bracketSize, poolExitSize))
            throw new IllegalArgumentException("Missing arguments");

        model.setCompetitorsFilePath(competitorsFilePath);
        loadCompetitors();

        try {
            model.setBracketSize(Integer.parseInt(bracketSize));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("bracketSize must be an integer.", e);
        }

        try {
            model.setNumExitCompetitors(Integer.parseInt(poolExitSize));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("poolExitSize must be an integer.", e);
        }

        // Only warn about excess arguments after required arguments have been validated
        if (args.length > 3) {
            System.out.println("Warning: ignoring excess arguments");
        }
    }

    private void printUsage() {
        String usage = "Usage:\r\n"
                + "--importFile: absolute path to import file\r\n"
                + "--bracketSize: maximum number of players to be seeded into final bracket\r\n"
                + "--poolExitSize: number of players to leave each pool\r\n";

        System.out.println(usage);
    }

    private void loadCompetitors() throws IllegalArgumentException {
        model.setCompetitors(new ArrayList<Competitor>());

        File competitorsFile = new File(model.getCompetitorsFilePath());

        if (FileUtils.isValidFile(competitorsFile, ACCEPTED_COMPETITOR_FILE_TYPES.split(","))) {
            model.setCompetitorsFile(competitorsFile);
        } else {
            throw new IllegalArgumentException("Input file is not valid or does not exist.");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(model.getCompetitorsFile()));
            String line = br.readLine();

            while (line != null) {
                String[] parts = line.split(",");

                if (parts.length != 3) {
                    throw new IllegalArgumentException("Input File is not valid.");
                }

                Competitor competitor = new Competitor(parts[0], Integer.parseInt(parts[1]), parts[2]);
                model.getCompetitors().add(competitor);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Input file is not valid.", e);
        }
    }

    public void generatePools() {
        // Sort competitors
        Competitor[] competitors = new Competitor[model.getCompetitors().size()];
        model.getCompetitors().toArray(competitors);
        Arrays.sort(competitors);

        // Get the number of pools
        int numPools = new Double(Math.floor(model.getBracketSize() / (double) model.getNumExitCompetitors())).intValue();

        // Get the number of competitors per pool
        int playersPerPool = new Double(Math.ceil(model.getCompetitors().size() / (float) numPools)).intValue();

        Map<Integer, List<Competitor>> pools = new HashMap<>();
        for (int i = 0; i < numPools; i++) {
            pools.put(i, new ArrayList<Competitor>());

            for (int j = 0; j < playersPerPool; j++) {

            }
        }
    }

    public PoolGenModel getModel() {
        return model;
    }
//-------Begin Insertion Sort algorithm methods--------//
    public static void insertionSort(Competitor[] list){
        int N = list.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && list[j].compareTo(list[j-1]) <= 0; j--)
                exch(list, j, j - 1);
        }
    }

    private static void exch(Competitor[] list, int i, int j) {
        Competitor t = list[i];
        list[i] = list[j];
        list[j] = t;
    }
//------End Insertion sort algorithm methods-----------//
}
