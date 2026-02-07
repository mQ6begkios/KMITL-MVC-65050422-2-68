package model;

public class Report {
    public String userId, rumorId, type, date;
    public Report(String userId, String rumorId, String type, String date) {
        this.userId = userId; this.rumorId = rumorId;
        this.type = type; this.date = date;
    }
}