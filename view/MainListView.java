package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainListView extends JFrame {
    public JTable table;
    public DefaultTableModel tableModel;
    public JButton btnDetail = new JButton("ดูรายละเอียด");
    public JButton btnSummary = new JButton("หน้าสรุปผล");

    public MainListView() {
        setTitle("ระบบติดตามข่าวลือ (Rumor Tracking System)");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // กำหนด Font ภาษาไทยให้รองรับการแสดงผล
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 14);
        
        String[] cols = {"ID", "หัวข้อข่าว", "แหล่งที่มา", "คะแนนความร้อนแรง", "สถานะ"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        
        // ตั้งค่า Font ในตาราง
        table.setFont(thaiFont);
        table.getTableHeader().setFont(thaiFont);
        table.setRowHeight(30);
        
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        JPanel pnl = new JPanel();
        btnDetail.setFont(thaiFont);
        btnSummary.setFont(thaiFont);
        pnl.add(btnDetail); 
        pnl.add(btnSummary);
        add(pnl, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null); // แสดงกลางหน้าจอ
    }
}