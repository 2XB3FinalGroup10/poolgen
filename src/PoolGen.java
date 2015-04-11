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
        new PoolGenView(this);
    }

    private void startCMD(String[] args) {
        try {
            parseArgs(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            printUsage();
        }

        try {
            printPools(generatePools());
        } catch (IOException e) {
            System.out.println("Error writing to file!");
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

        if ((model.getCompetitors().size() < model.getBracketSize())) {
            throw new IllegalArgumentException("Number of competitors cannot be less than the bracket size.");
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

    public void loadCompetitors() throws IllegalArgumentException {
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

    public Map<Integer, List<Competitor>> generatePools() {
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
                if ((i * playersPerPool + j) > competitors.length) break;
                pools.get(i).add(competitors[i * playersPerPool + j]);
            }
        }

        return pools;
    }

    public void printPools(Map<Integer, List<Competitor>> pools) throws IOException {
        File out = new File(model.getCompetitorsFile().getParent(), "pools.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(out));

        for (Map.Entry<Integer, List<Competitor>> pool : pools.entrySet()) {
            bw.write("Pool " + pool.getKey() + "\r\n");
            for (Competitor competitor : pool.getValue()) {
                bw.write("Name: " + competitor.getName() + ", Region: " + competitor.getRegion() + "\r\n");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    public PoolGenModel getModel() {
        return model;
    }
}
