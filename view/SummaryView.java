package view;

import model.Rumor;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SummaryView extends JFrame {
    public SummaryView(List<Rumor> list) {
        setTitle("หน้าสรุปผลการตรวจสอบข่าวลือ");
        setSize(700, 500);
        
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 14);
        JTextArea area = new JTextArea();
        area.setFont(thaiFont);
        area.setEditable(false);
        area.setMargin(new Insets(15, 15, 15, 15));

        StringBuilder sb = new StringBuilder();
        
        // ข่าวลือที่เป็น Panic
        sb.append("========= [1] ข่าวลือที่ต้องเฝ้าระวัง (PANIC) =========\n");
        for (Rumor r : list) {
            if ("panic".equalsIgnoreCase(r.status)) {
                sb.append(String.format("ID: %s | %s\n", r.id, r.title));
            }
        }

        // ข่าวที่ยืนยันว่าเป็น ข้อมูลจริง
        sb.append("\n========= [2] ข่าวที่ตรวจสอบแล้ว: ข้อมูลจริง =========\n");
        for (Rumor r : list) {
            // เงื่อนไข: ยืนยันแล้ว และคะแนนความร้อนแรงต่ำ 
            if (r.isVerified && r.score < 50) {
                sb.append(String.format("ID: %s | %s\n", r.id, r.title));
            }
        }

        // ข่าวที่ยืนยันว่าเป็น ข้อมูลเท็จ
        sb.append("\n========= [3] ข่าวที่ตรวจสอบแล้ว: ข้อมูลเท็จ =========\n");
        for (Rumor r : list) {
            // เงื่อนไข: ยืนยันแล้วและคะแนนความร้อนแรงสูง 
            if (r.isVerified && r.score >= 50) {
                sb.append(String.format("ID: %s | %s\n", r.id, r.title));
            }
        }
        
        area.setText(sb.toString());
        add(new JScrollPane(area));
        setLocationRelativeTo(null);
    }
}