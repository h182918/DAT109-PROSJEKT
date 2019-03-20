package entities;

public class standOverview {
	    private String name;
	    private Double average;
	    private int totalScore;

	    public standOverview(String name, Double average, int totalScore) {
	        this.name = name;
	        this.average = average;
	        this.totalScore = totalScore;
	    }
	    
	    public standOverview() {

	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public Double getAverage() {
	        return average;
	    }

	    public void setAverage(Double average) {
	        this.average = average;
	    }

	    public int getTotalScore() {
	        return totalScore;
	    }

	    public void setTotalScore(int totalScore) {
	        this.totalScore = totalScore;
	    }

}
