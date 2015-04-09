//Main.java

import java.io.*;
import java.util.*;
import java.lang.*;

public class PoolGenMain {
    public static void main(String[] args) {
        String fileName = "input.txt";
        File file = new File(fileName);

        if(!file.exists()) throw new IllegalArgumentException();
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(file);
        }
        catch(IOException ex) {
            System.err.println(ex);
            throw new IllegalArgumentException(ex);
        }

        String outFile = fileInput.next();

        int tournamentSize = 0;
        while (fileInput.hasNext()) {
            tournamentSize++;
        }

        System.out.println(tournamentSize);

        //This info will be retrieved outside of the given input.txt file
        //int bracketSize = fileInput.nextInt();
        //int poolNumber = fileInput.nextInt();
        //int poolSize = fileInput.nextInt();

        Competitor[] competitorList = new Competitor[tournamentSize+1];

        if(tournamentSize <= 0) throw new IllegalArgumentException();

        for(int i = 0; i < tournamentSize; i++) {
            String name =fileInput.next();
            int rank = fileInput.nextInt();
            String region = fileInput.next();
            Competitor a = new Competitor(name, rank, region);
            competitorList[i] = a;
        }

    }
}