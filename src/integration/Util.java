package integration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    private static List<String> tables = new ArrayList<String>();
    private static String connectionChain = "jdbc:mariadb://localhost:3306/greengo?user=manager&password=manager_if";

    public static void deleteAll() throws DAOException {
        Connection connec;
        try {
            connec = DriverManager.getConnection(connectionChain);
        } catch (SQLException ex) {
            throw new DAOException("ERROR: access to DB at operation 'deleteAll' @city unsuccessful\n");
        }

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            ps.execute();
            ps.close();

            ps = connec.prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='greengo' ");
            ResultSet  rs =  ps.executeQuery();
            rs.next();
            while(rs.next()){
                tables.add(rs.getString(1));
            }
            ps.close();

            for(String table : tables){
                ps = connec.prepareStatement("TRUNCATE TABLE " + table);
                ps.execute();
                ps.close();
            }

            ps = connec.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new DAOException("ERROR: SQL statement execution at operation 'deleteAll' unsuccessful\n");
        } finally {
            try {
                connec.close();
            } catch (SQLException e) {
                throw new DAOException("ERROR: closing connection to DB at operation 'deleteAll' unsuccessful\n");
            }
        }
    }

    public static void driverIdentify() throws DAOException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new DAOException("ERROR: couldn't register MARIADB driver: " + ex);
        }
    }

    public static String getConnectionChain(){
        return connectionChain;
    }
}
