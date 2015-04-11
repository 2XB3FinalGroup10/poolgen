import util.FileUtils;
import util.StringUtils;

import java.io.*;
import java.util.HashMap;

/**
 * Author: Zed
 * Date: 4/3/2015
 */
public class PoolGen {
    private static final String ACCEPTED_COMPETITOR_FILE_TYPES = "csv, txt";

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
        model.setCompetitors(new HashMap<Integer, Competitor>());

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
                model.getCompetitors().put(competitor.getRank(), competitor);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Input file is not valid.", e);
        }
    }

    public void generatePools() {
        // Pools get generated
    }

    public PoolGenModel getModel() {
        return model;
    }
}
