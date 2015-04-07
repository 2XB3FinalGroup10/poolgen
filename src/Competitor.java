//Competitor.java

public class Competitor {
	private final String name;
	private final int rank;
	private final String region;

	public Competitor(String name, int rank, String region) {
		this.name = name;
		this.rank = rank;
		this.region = region;
	}

	public int getRank() {return this.rank;}

	public String getName() {return this.name;}

	public String getRegion() {return this.region;}
}