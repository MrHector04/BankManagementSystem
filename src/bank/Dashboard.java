package bank;

import javax.swing.*;

public class Dashboard extends JFrame {

    String accountNo;

    public Dashboard(String acc) {
        this.accountNo = acc;

        setTitle("Dashboard");
        setSize(400,350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton deposit = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        JButton balance = new JButton("Balance");
        JButton statement = new JButton("Mini Statement");
        JButton pin = new JButton("Change PIN");
        JButton exit = new JButton("Exit");

        deposit.setBounds(100,30,200,30);
        withdraw.setBounds(100,70,200,30);
        balance.setBounds(100,110,200,30);
        statement.setBounds(100,150,200,30);
        pin.setBounds(100,190,200,30);
        exit.setBounds(100,230,200,30);

        add(deposit);
        add(withdraw);
        add(balance);
        add(statement);
        add(pin);
        add(exit);

        // 🔚 FINAL STEP – CONNECT BUTTONS
        deposit.addActionListener(e -> new Deposit(accountNo));
        withdraw.addActionListener(e -> new Withdraw(accountNo));
        balance.addActionListener(e -> new Balance(accountNo));
        statement.addActionListener(e -> new MiniStatement(accountNo));
        pin.addActionListener(e -> new ChangePin(accountNo));
        exit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}
