package entities;

public class Vote {

    private int id;
    private String userName;
    private int stand;
    private int score;

    public Vote(String userName, int stand, int score) {
        this.userName = userName;
        this.stand = stand;
        this.score = score;
    }

    public Vote() {
    }

    /**
     * Returns the user for this vote
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user for this vote
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * Gets the stand
     * @return
     */
    public int getStand() {
        return stand;
    }
    
    /**
     * Sets the stand
     * @param stand
     */
    public void setStand(int stand) {
        this.stand = stand;
    }

    /**
     * Gets the score
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", stand=" + stand +
                ", score=" + score +
                '}';
    }
}
