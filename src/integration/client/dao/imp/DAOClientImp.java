package integration.client.dao.imp;

import business.client.TClient;
import integration.DAOException;
import integration.transaction.Transaction;
import integration.Util;
import integration.client.dao.DAOClient;
import integration.transactionManager.TransactionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class DAOClientImp implements DAOClient {

    @Override
    public Integer create(TClient client) throws DAOException {
        Integer id;


        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'create' @client unsuccessful\n");
            }
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("INSERT INTO client(idCardNumber, numRentals, active) " +
                    "VALUES (?,?,?)");
            ps.setString(1, client.getIdCardNumber());
            ps.setInt(2, client.getNumRentals());
            ps.setBoolean(3, client.isActive());
            ps.execute();
            ps.close();

            ps = connec.prepareStatement("SELECT LAST_INSERT_ID() FROM client");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("LAST_INSERT_ID()");
            }
            else
                throw new DAOException("ERROR: LAST_INSERT_ID() returned empty after an insert operation\n");
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'create' @client unsuccessful\n");
        }

        finally {
            if(TransactionManager.getInstance().getTransaction() == null) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'create' @client " +
                            "unsuccessful\n");
                }
            }
        }

        return id;
    }

    @Override
    public Integer update(TClient client) throws DAOException {

        Integer id;

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'update' @client unsuccessful\n");
            }
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("UPDATE client SET idCardNumber = ?, numRentals = ?, " +
                    "active = ? WHERE id = ?");
            ps.setString(1, client.getIdCardNumber());
            ps.setInt(2, client.getNumRentals());
            ps.setBoolean(3, client.isActive());
            ps.setInt(4, client.getId());
            ps.executeQuery();

            ps = connec.prepareStatement("SELECT id FROM client WHERE id = ?");
            ps.setInt(1, client.getId());
            ResultSet rs = ps.executeQuery();
            ps.close();

            if (rs.next()) {
                id = rs.getInt("id");
            }
            else
                throw new DAOException("ERROR: trying to update a nonexistent entity at operation 'update' @client");
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'update' @client unsuccessful\n");
        }

        finally {
            if(TransactionManager.getInstance().getTransaction() == null) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'update' @client " +
                            "unsuccessful\n");
                }
            }
        }

        return id;

    }

    @Override
    public TClient readById(Integer id) throws DAOException {
        TClient readClient;

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readById' @client unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM client WHERE id = ?" + queryTail);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                readClient = new TClient(rs.getInt("id"), rs.getString("idCardNumber"),
                        rs.getInt("numRentals"), rs.getBoolean("active"));
            }
            else
                readClient = null;
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readById' @client unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readById' @client " +
                            "unsuccessful\n");
                }
            }
        }

        return readClient;
    }

    public TClient readByIdCard(String idCard) throws DAOException {
        TClient readClient;

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readByIdCard' @client unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM client WHERE idCardNumber = ?" +
                    queryTail);
            ps.setString(1, idCard);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                readClient = new TClient(rs.getInt("id"), rs.getString("idCardNumber"),
                        rs.getInt("numRentals"), rs.getBoolean("active"));
            }
            else
                readClient = null;
            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readByIdCard' @client unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readByIdCard' @client " +
                            "unsuccessful\n");
                }
            }
        }

        return readClient;
    }


    @Override
    public Collection<TClient> readAll() throws DAOException {
        Collection<TClient> readClients = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readAll' @client unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM client WHERE active = true" + queryTail);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readClients.add(new TClient(rs.getInt("id"), rs.getString("idCardNumber"),
                        rs.getInt("numRentals"), rs.getBoolean("active")));

            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readAll' @client unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readAll' @client " +
                            "unsuccessful\n");
                }
            }
        }

        return readClients;
    }

    public Collection<TClient> readByNRentals(Integer N) throws DAOException {
        Collection<TClient> readClients = new ArrayList<>();

        String queryTail = " FOR UPDATE";

        driverIdentify();
        Transaction transaction = TransactionManager.getInstance().getTransaction();
        Connection connec;
        if(transaction == null){
            try {
                connec = DriverManager.getConnection(Util.getConnectionChain());
            }
            catch(SQLException ex){
                throw new DAOException("ERROR: access to DB at operation 'readByNRentals' @client unsuccessful\n");
            }
            queryTail = "";
        }
        else
            connec = (Connection) transaction.getResource();

        try { // Tratamiento db
            PreparedStatement ps = connec.prepareStatement("SELECT * FROM client WHERE numRentals > ?" +
                    queryTail);
            ps.setInt(1, N);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                readClients.add(new TClient(rs.getInt("id"), rs.getString("idCardNumber"),
                        rs.getInt("numRentals"), rs.getBoolean("active")));
            }

            ps.close();
        }
        catch (SQLException e){
            throw new DAOException("ERROR: SQL statement execution at operation 'readByNRentals' @client " +
                    "unsuccessful\n");
        }

        finally {
            if(queryTail.equals("")) {
                try {
                    connec.close();
                } catch (SQLException e) {
                    throw new DAOException("ERROR: closing connection to DB at operation 'readByNRentals' @client " +
                            "unsuccessful\n");
                }
            }
        }

        return readClients;
    }

    public void deleteAll() throws DAOException {
        Util.deleteAll();
    }

    private void driverIdentify() throws DAOException {
        Util.driverIdentify();
    }

}
