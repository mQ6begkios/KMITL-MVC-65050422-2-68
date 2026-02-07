package controller;

import model.*;
import view.*;
import javax.swing.*;
import java.awt.Font;
import java.util.Collections;

public class RumorController {
    private DataService model;
    private MainListView view;
    private String currentUserId = "U68_65050422"; // จำลอง User ID

    public RumorController(DataService model, MainListView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        // แก้ภาษาไทยใน Popup
        UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Tahoma", Font.PLAIN, 14));

        refreshTable();

        view.btnDetail.addActionListener(e -> {
            int row = view.table.getSelectedRow();
            if (row != -1) {
                String id = view.table.getValueAt(row, 0).toString();
                Rumor rumor = model.findRumor(id);
                DetailView dv = new DetailView(rumor, model);
                
                // ปุ่มรายงาน (User)
                dv.btnReport.addActionListener(ev -> {
                    String type = dv.typeCombo.getSelectedItem().toString();
                    String msg = model.addNewReport(currentUserId, rumor.id, type);
                    JOptionPane.showMessageDialog(dv, msg);
                    refreshTable();
                    dv.dispose();
                });

                // ปุ่มฟันธง (Checker)
                dv.btnVerify.addActionListener(ev -> {
                    boolean isFake = (dv.checkResultCombo.getSelectedIndex() == 1);
                    model.updateVerification(rumor.id, true, isFake);
                    JOptionPane.showMessageDialog(dv, "บันทึกผลเป็น: " + (isFake ? "ข้อมูลเท็จ" : "ข้อมูลจริง"));
                    refreshTable();
                    dv.dispose();
                });
                dv.setVisible(true);
            }
        });

        view.btnSummary.addActionListener(e -> new SummaryView(model.rumors).setVisible(true));
    }

    public void refreshTable() {
        Collections.sort(model.rumors, (a, b) -> b.score - a.score);
        view.tableModel.setRowCount(0);
        for (Rumor r : model.rumors) {
            view.tableModel.addRow(new Object[]{ r.id, r.title, r.source, r.score, r.status });
        }
    }
}