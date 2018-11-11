package integration.Transaction;

import integration.DAOException;
import integration.TransactionException;

import java.sql.Connection;

public interface Transaction {

     void start() throws TransactionException, DAOException;

     void commit() throws TransactionException;

     void rollback() throws TransactionException;

     Object getResource();

     String getConnectionChain();
}
