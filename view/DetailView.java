package view;

import model.*;
import javax.swing.*;
import java.awt.*;

public class DetailView extends JFrame {
    public JComboBox<String> typeCombo = new JComboBox<>(new String[]{"ข้อมูลเท็จ", "บิดเบือน", "ปลุกปั่น", "ข้อมูลจริง"});
    public JButton btnReport = new JButton("ส่งรายงานข่าว");
    
    // ComboBox สำหรับ Checker เลือกฟันธง
    public JComboBox<String> checkResultCombo = new JComboBox<>(new String[]{"ตัดสินเป็น: ข้อมูลจริง ", "ตัดสินเป็น: ข้อมูลเท็จ "});
    public JButton btnVerify = new JButton("บันทึกผลการตรวจสอบ (สำหรับ Checker)");

    public DetailView(Rumor r, DataService ds) {
        setTitle("จัดการข่าวลือ: " + r.id);
        setSize(600, 680);
        setLayout(new BorderLayout(15, 15));
        
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 15);
        Font boldFont = new Font("Tahoma", Font.BOLD, 15);
        
        JPanel pnl = new JPanel(new GridLayout(13, 1, 5, 5));
        pnl.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        pnl.add(new JLabel("หัวข้อ: " + r.title) {{ setFont(new Font("Tahoma", Font.BOLD, 17)); }});
        pnl.add(new JLabel("สถานะ: " + r.status) {{ setFont(thaiFont); }});
        pnl.add(new JLabel("รายงานรวม: " + ds.getReportCount(r.id) + " ราย") {{ setFont(thaiFont); setForeground(Color.BLUE); }});

        pnl.add(new JSeparator());

        // --- ส่วนผู้ตรวจสอบ (Checker) ---
        pnl.add(new JLabel("ส่วนของผู้ตรวจสอบ: เลือกผลที่ต้องการ") {{ setFont(boldFont); }});
        checkResultCombo.setFont(thaiFont);
        btnVerify.setFont(thaiFont);
        btnVerify.setBackground(new Color(255, 215, 0)); // สีเหลืองทอง
        pnl.add(checkResultCombo);
        pnl.add(btnVerify);

        pnl.add(new JSeparator());

        // --- ส่วนผู้ใช้งาน (User) ---
        pnl.add(new JLabel("ส่วนของผู้ใช้งาน: รายงานข้อมูล") {{ setFont(boldFont); }});
        typeCombo.setFont(thaiFont); btnReport.setFont(thaiFont);
        JPanel uPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        uPnl.add(typeCombo); uPnl.add(btnReport);
        pnl.add(uPnl);

        add(pnl, BorderLayout.CENTER);
        JButton btnClose = new JButton("ปิดหน้าต่าง");
        btnClose.setFont(thaiFont); btnClose.addActionListener(e -> dispose());
        add(btnClose, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
    }
}