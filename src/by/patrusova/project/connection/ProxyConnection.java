package by.patrusova.project.connection;

import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;

/**
 * Class for safely wrapping Connection instance for storing in connection pool.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ProxyConnection implements Connection {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String USER = "user";
    private final static String PASS = "pass";
    private final static String URL = "url";
    private final static String BUNDLE = "resources.connectionDB";
    private Connection connection;

    protected ProxyConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method: create instance of Connection using {@link DriverManager}
     * @throws DaoException object
     * @return instance of {@link Connection}
     */
    public static Connection createConnection() throws DaoException {
        Connection connection;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE);
            String user = bundle.getString(USER);
            String pass = bundle.getString(PASS);
            String url = bundle.getString(URL);
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Connection's creation failed. ", e);
            throw new DaoException(e);
        }
        return connection;
    }

    /**
     * Method: create instance of ProxyConnection by wrapping {@link Connection}
     * @throws DaoException object
     * @return instance of ProxyConnection
     */
    public static ProxyConnection createProxyConnection() throws DaoException {
        return new ProxyConnection(createConnection());
    }

    /**
     * Method: create instance of Statement
     * @return instance of {@link Statement}
     */
    @Override
    public Statement createStatement() {
        Statement statement = null;
        try {
            if (connection != null) {
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Connection is null (or try to get null statement). ", e);
            try {
                throw new DaoException(e);
            } catch (DaoException ex) {
                ex.printStackTrace();
            }
        }
        return statement;
    }

    /**
     * Method: prepare Statement for executing
     * @param s is String value of SQL query
     * @return instance of {@link PreparedStatement}
     */
    @Override
    public PreparedStatement prepareStatement(String s) {
        PreparedStatement statement = null;
        try {
            if (connection != null) {
                    statement = connection.prepareStatement(s);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Connection is null. ", e);
            try {
                throw new DaoException(e);
            } catch (DaoException ex) {
                ex.printStackTrace();
            }
        }
        return statement;
    }

    /**
    * Method: releasing connection (back in pool)
    */
    @Override
    public void close() {
        try {
            ConnectionPool.getInstance().releaseConnection(this);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Connection's releasing failed");
            e.printStackTrace();
        }
    }

    @Override
    public void setAutoCommit(boolean b) throws SQLException {
        connection.setAutoCommit(b);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }

    @Override
    public CallableStatement prepareCall(String s) throws SQLException {
        return connection.prepareCall(s);
    }

    @Override
    public String nativeSQL(String s) throws SQLException {
        return connection.nativeSQL(s);
    }

    @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    @Override
    public void setReadOnly(boolean b) throws SQLException {
        connection.setReadOnly(b);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }

    @Override
    public void setCatalog(String s) throws SQLException {
        connection.setCatalog(s);
    }

    @Override
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int i) throws SQLException {
        connection.setTransactionIsolation(i);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }

    @Override
    public Statement createStatement(int i, int i1) throws SQLException {
        return connection.createStatement(i, i1);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
        return connection.prepareStatement(s, i, i1);
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
        return connection.prepareCall(s, i, i1);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        connection.setTypeMap(map);
    }

    @Override
    public void setHoldability(int i) throws SQLException {
        connection.setHoldability(i);
    }

    @Override
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String s) throws SQLException {
        return connection.setSavepoint(s);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        connection.rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        connection.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int i, int i1, int i2) throws SQLException {
        return connection.createStatement(i, i1, i2);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
        return connection.prepareStatement(s, i, i1, i2);
    }

    @Override
    public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
        return connection.prepareCall(s, i, i1, i2);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int i) throws SQLException {
        return connection.prepareStatement(s, i);
    }

    @Override
    public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
        return connection.prepareStatement(s, ints);
    }

    @Override
    public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
        return connection.prepareStatement(s, strings);
    }

    @Override
    public Clob createClob() throws SQLException {
        return connection.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return connection.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return connection.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return connection.createSQLXML();
    }

    @Override
    public boolean isValid(int i) throws SQLException {
        return connection.isValid(i);
    }

    @Override
    public void setClientInfo(String s, String s1) throws SQLClientInfoException {
        connection.setClientInfo(s, s1);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String s) throws SQLException {
        return connection.getClientInfo(s);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return connection.getClientInfo();
    }

    @Override
    public Array createArrayOf(String s, Object[] objects) throws SQLException {
        return connection.createArrayOf(s, objects);
    }

    @Override
    public Struct createStruct(String s, Object[] objects) throws SQLException {
        return connection.createStruct(s, objects);
    }

    @Override
    public void setSchema(String s) throws SQLException {
        connection.setSchema(s);
    }

    @Override
    public String getSchema() throws SQLException {
        return connection.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        connection.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int i) throws SQLException {
        connection.setNetworkTimeout(executor, i);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return connection.getNetworkTimeout();
    }

    @Override
    public void beginRequest() throws SQLException {
        connection.beginRequest();
    }

    @Override
    public void endRequest() throws SQLException {
        connection.endRequest();
    }

    @Override
    public boolean setShardingKeyIfValid(ShardingKey shardingKey, ShardingKey superShardingKey, int timeout) throws
            SQLException {
        return connection.setShardingKeyIfValid(shardingKey, superShardingKey, timeout);
    }

    @Override
    public boolean setShardingKeyIfValid(ShardingKey shardingKey, int timeout) throws SQLException {
        return connection.setShardingKeyIfValid(shardingKey, timeout);
    }

    @Override
    public void setShardingKey(ShardingKey shardingKey, ShardingKey superShardingKey) throws SQLException {
        connection.setShardingKey(shardingKey, superShardingKey);
    }

    @Override
    public void setShardingKey(ShardingKey shardingKey) throws SQLException {
        connection.setShardingKey(shardingKey);
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return connection.unwrap(aClass);
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return connection.isWrapperFor(aClass);
    }
}