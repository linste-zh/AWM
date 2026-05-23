package linstezh.database;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import linstezh.logic.ExperimentItem;

import java.sql.SQLException;


public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:database/database.db";
    private final ConnectionSource connectionSource;

    public DatabaseManager() throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL);
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public void initTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, ExperimentItem.class);
    }

    public void close() throws Exception {
        connectionSource.close();
    }
}
