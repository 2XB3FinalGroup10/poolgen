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
    //getter methods to retrieve competitor information
    public int getRank () {
        return this.rank;
    }

    public String getName() {
        return this.name;
    }

    public String getRegion() {
        return this.region;
    }

    //This will allow competitors to be edited if there are any mistakes with the file.
    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}