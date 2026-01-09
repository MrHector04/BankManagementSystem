package bank;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MiniStatement extends JFrame {

    public MiniStatement(String accNo) {

        setTitle("Mini Statement");
        setSize(500,300);
        setLocationRelativeTo(null);

        String[] cols = {"Type", "Amount", "Date"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(
                            "SELECT type, amount, date FROM Transactions WHERE account_no=?");
            ps.setString(1, accNo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getString(3)
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        add(new JScrollPane(table));
        setVisible(true);
    }
}
