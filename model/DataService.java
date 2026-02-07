package model;

import java.io.*;
import java.util.*;

public class DataService {
    public List<Rumor> rumors = new ArrayList<>();
    public List<Report> reports = new ArrayList<>();

    public void loadDataFromCSV() {
        reports.clear();
        rumors.clear();
        loadReports();
        loadRumors();
        updatePanicStatus();
    }

    private void loadReports() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/reports.csv"), "UTF-8"))) {
            String line; br.readLine();
            while ((line = br.readLine()) != null) {
                String[] v = line.split(",");
                reports.add(new Report(v[0], v[1], v[2], v[3]));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadRumors() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/rumors.csv"), "UTF-8"))) {
            String line; br.readLine();
            while ((line = br.readLine()) != null) {
                String[] v = line.split(",");
                rumors.add(new Rumor(v[0], v[1], v[2], v[3], Integer.parseInt(v[4]), v[5], Boolean.parseBoolean(v[6])));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    // ห้ามผู้ใช้คนเดิมรายงานข่าวเดิมซ้ำ
    public String addNewReport(String userId, String rumorId, String type) {
        for (Report r : reports) {
            if (r.userId.equals(userId) && r.rumorId.equals(rumorId)) {
                return "คุณเคยรายงานข่าวนี้ไปแล้ว ไม่สามารถรายงานซ้ำได้";
            }
        }
        reports.add(new Report(userId, rumorId, type, "2026-02-07"));
        updatePanicStatus(); 
        return "ส่งรายงานสำเร็จ!";
    }

    // สำหรับ Checker เลือกฟันธงตรงๆ โดยไม่สนคะแนนเดิม
    public void updateVerification(String rumorId, boolean status, boolean isFakeDecision) {
        Rumor r = findRumor(rumorId);
        if (r != null) {
            r.isVerified = status;
            r.score = isFakeDecision ? 100 : 0; // ตัดสินเป็นเท็จ=100, จริง=0
            updatePanicStatus();
        }
    }

    private void updatePanicStatus() {
        for (Rumor r : rumors) {
            r.status = (getReportCount(r.id) >= 5) ? "panic" : "ปกติ";
        }
    }

    public int getReportCount(String rumorId) {
        return (int) reports.stream().filter(r -> r.rumorId.equals(rumorId)).count();
    }

    public Rumor findRumor(String id) {
        return rumors.stream().filter(r -> r.id.equals(id)).findFirst().orElse(null);
    }
}