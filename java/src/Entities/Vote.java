package Entities;

public class Vote {

    private String userName;
    private Stand stand;
    private int score;

    public Vote(String userName, Stand stand, int score) {
        this.userName = userName;
        this.stand = stand;
        this.score = score;
    }

    public Vote() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}