package common.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;

public class JdbcUtil {
	  public JdbcUtil() {}

	    public static Connection getConnection() {
	        Connection conn = null;
	        try {
	            Context initContext = new InitialContext();
	            DataSource ds = (DataSource) initContext.lookup("java:comp/env/oracleDB");
	            conn = ds.getConnection();
	            conn.setAutoCommit(false);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return conn;
	    }
	
	    public static boolean isConnected(Connection conn) {

	        boolean validConnection = true;

	        try {
	            if (conn == null || conn.isClosed())
	                validConnection = false;
	        } catch (SQLException e) {
	            validConnection = false;
	            e.printStackTrace();
	        }
	        return validConnection;
	    }
	
	    public static void close(Connection conn) {

	        if (isConnected(conn)) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public static void close(Statement stmt) {

	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void close(ResultSet rset) {

	        try {
	            if (rset != null)
	                rset.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static void commit(Connection conn) {

	        try {
	            if (isConnected(conn)) {
	                conn.commit();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	    public static void rollback(Connection conn) {

	        try {
	            if (isConnected(conn)) {
	                conn.rollback();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
