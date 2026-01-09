package bank;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Withdraw extends JFrame {

    public Withdraw(String accNo) {

        setTitle("Withdraw");
        setSize(300,200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Enter Amount:");
        l1.setBounds(30,30,100,30);
        add(l1);

        JTextField amount = new JTextField();
        amount.setBounds(140,30,100,30);
        add(amount);

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(90,80,100,30);
        add(withdrawBtn);

        withdrawBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amount.getText());
                Connection con = DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(
                                "SELECT balance FROM Account WHERE account_no=?");
                ps.setString(1, accNo);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    double balance = rs.getDouble(1);

                    if (balance < amt) {
                        JOptionPane.showMessageDialog(this, "Insufficient Balance");
                        return;
                    }
                }

                PreparedStatement ps1 =
                        con.prepareStatement(
                                "UPDATE Account SET balance = balance - ? WHERE account_no = ?");
                ps1.setDouble(1, amt);
                ps1.setString(2, accNo);
                ps1.executeUpdate();

                PreparedStatement ps2 =
                        con.prepareStatement(
                                "INSERT INTO Transactions(account_no,type,amount) VALUES(?,?,?)");
                ps2.setString(1, accNo);
                ps2.setString(2, "Withdraw");
                ps2.setDouble(3, amt);
                ps2.executeUpdate();

                JOptionPane.showMessageDialog(this, "Withdraw Successful");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Amount");
            }
        });

        setVisible(true);
    }
}
