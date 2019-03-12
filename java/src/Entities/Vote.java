package Entities;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStand() {
        return stand;
    }

    public void setStand(int stand) {
        this.stand = stand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

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
