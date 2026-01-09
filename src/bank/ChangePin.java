package bank;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ChangePin extends JFrame {

    public ChangePin(String accNo) {

        setTitle("Change PIN");
        setSize(300,200);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("New PIN:");
        l1.setBounds(30,30,100,30);
        add(l1);

        JPasswordField pin = new JPasswordField();
        pin.setBounds(120,30,100,30);
        add(pin);

        JButton btn = new JButton("Change");
        btn.setBounds(90,80,100,30);
        add(btn);

        btn.addActionListener(e -> {
            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(
                                "UPDATE Account SET pin=? WHERE account_no=?");
                ps.setString(1, new String(pin.getPassword()));
                ps.setString(2, accNo);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "PIN Changed Successfully");
                dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}
