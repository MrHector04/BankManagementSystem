package bank;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {

    JTextField acc;
    JPasswordField pin;

    public Login() {
        setTitle("Bank Login");
        setSize(350,250);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Account No");
        l1.setBounds(50,50,100,25);
        add(l1);

        acc = new JTextField();
        acc.setBounds(150,50,120,25);
        add(acc);

        JLabel l2 = new JLabel("PIN");
        l2.setBounds(50,90,100,25);
        add(l2);

        pin = new JPasswordField();
        pin.setBounds(150,90,120,25);
        add(pin);

        JButton login = new JButton("Login");
        login.setBounds(120,140,100,30);
        add(login);

        login.addActionListener(e -> login());

        setVisible(true);
    }

    void login() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM Account WHERE account_no=? AND pin=?"
            );
            ps.setString(1, acc.getText());
            ps.setString(2, new String(pin.getPassword()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                new Dashboard(acc.getText());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,"Invalid Login");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
