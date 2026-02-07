package model;

public class Rumor {
    public String id, title, source, date, status;
    public int score;
    public boolean isVerified;

    public Rumor(String id, String title, String source, String date, int score, String status, boolean isVerified) {
        this.id = id; this.title = title; this.source = source;
        this.date = date; this.score = score; this.status = status;
        this.isVerified = isVerified;
    }
}