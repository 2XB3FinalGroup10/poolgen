//Main.java

import java.io*

public class Main {
	public static void Main(String[] args) {
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

		String outFile = fileInput.nextString();
		int tournamentSize = fileInput.nextInt();
		int bracketSize = fileInput.nextInt();
		int poolNumber = fileInput.nextInt();
		int poolSize = fileInput.nextInt();	

		private Competitor[] = new competitorList[tournamentSize+1];

		if(tournamentSize <= 0) throw new IllegalArgumentException();
		
		for(int i = 0; i < tournamentSize; i++) {
			String name =fileInput.nextString();
			int rank = fileInput.nextInt();
			String region = fileInput.nextInt();
			Competitor[i] = Competitor(name, rank, region);

		MergeSort(competitorList);


		}
	}
}