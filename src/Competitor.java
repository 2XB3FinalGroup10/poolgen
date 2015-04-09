//Competitor.java

public class Competitor {
	private String name;
	private int rank;
	private String region;

	public Competitor(String name, int rank, String region) {
		this.name = name;
		this.rank = rank;
		this.region = region;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}