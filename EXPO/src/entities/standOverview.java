package entities;

import java.text.DecimalFormat;

/**
 * A class representing the sql view of same name. Only difference is the entire Stand entity is connected to standOverview. 
 * @author david
 *
 */
public class standOverview {
	    private Stand stand;
	    private Double average;
	    private long totalScore;

	    public standOverview(Stand stand, Double average, int totalScore) {
	        this.stand = stand;
	        this.average = average;
	        this.totalScore = totalScore;
	    }
	    
	    public standOverview() {

	    }


	    public Stand getStand() {
			return stand;
		}

		public void setStand(Stand stand) {
			this.stand = stand;
		}

		public Double getAverage() {
	        return average;
	    }

	    /**
	     * Rounds the input number to one digit and assigns it to 'average'.
	     * @param average
	     */
	    public void setAverage(Double average) {
	    	DecimalFormat df = new DecimalFormat("0.0");
			String avgFormattedStr = df.format(average).replaceAll(",", ".");
			double formattedAvg = Double.parseDouble(avgFormattedStr);
	        this.average = formattedAvg;
	    }

	    public long getTotalScore() {
	        return totalScore;
	    }

	    public void setTotalScore(long totalScore) {
	        this.totalScore = totalScore;
	    }

}
