package bank;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Balance {

    public Balance(String accNo) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            "SELECT balance FROM Account WHERE account_no=?");
            ps.setString(1, accNo);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null,
                        "Available Balance: ₹ " + rs.getDouble(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
