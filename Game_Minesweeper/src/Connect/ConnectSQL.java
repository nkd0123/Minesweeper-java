package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectSQL implements AutoCloseable {
    protected Connection con = null;

    public ConnectSQL() {
        String strServer = "NKD\\NKD";
        String strData = "Player_Minesweeper";
        String strUser = "sa";
        String strPass = "123";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, "SQL Server JDBC Driver not found.", ex);
        }

        String conURL = "jdbc:sqlserver://" + strServer
                + ":1433;databaseName=" + strData
                + ";encrypt=true;trustServerCertificate=true;user=" + strUser
                + ";password=" + strPass;

        try {
            con = DriverManager.getConnection(conURL);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, "Failed to establish connection.", ex);
        }
    }

    public Connection getConnection() {
        return con;
    }

    @Override
    public void close() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, "Failed to close connection.", ex);
            }
        }
    }
}
