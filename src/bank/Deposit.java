package bank;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Deposit extends JFrame {

    public Deposit(String accNo) {

        setTitle("Deposit");
        setSize(300,200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Enter Amount:");
        l1.setBounds(30,30,100,30);
        add(l1);

        JTextField amount = new JTextField();
        amount.setBounds(140,30,100,30);
        add(amount);

        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(90,80,100,30);
        add(depositBtn);

        depositBtn.addActionListener(e -> {
            try {
                double amt = Double.parseDouble(amount.getText());
                Connection con = DBConnection.getConnection();

                PreparedStatement ps1 =
                        con.prepareStatement(
                                "UPDATE Account SET balance = balance + ? WHERE account_no = ?");
                ps1.setDouble(1, amt);
                ps1.setString(2, accNo);
                ps1.executeUpdate();

                PreparedStatement ps2 =
                        con.prepareStatement(
                                "INSERT INTO Transactions(account_no,type,amount) VALUES(?,?,?)");
                ps2.setString(1, accNo);
                ps2.setString(2, "Deposit");
                ps2.setDouble(3, amt);
                ps2.executeUpdate();

                JOptionPane.showMessageDialog(this, "Amount Deposited Successfully");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Amount");
            }
        });

        setVisible(true);
    }
}
