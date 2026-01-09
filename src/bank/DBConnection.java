package bank;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url =
                "jdbc:sqlserver://localhost:1433;"
              + "databaseName=BankDB;"
              + "integratedSecurity=true;"
              + "encrypt=true;"
              + "trustServerCertificate=true;";

            return DriverManager.getConnection(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
