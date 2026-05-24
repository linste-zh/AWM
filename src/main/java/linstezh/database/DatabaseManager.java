package linstezh.database;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import linstezh.database.dao.ItemDAO;
import linstezh.database.dao.SectionDAO;
import linstezh.logic.ExperimentItem;
import linstezh.logic.Section;

import java.sql.SQLException;


public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:database/database.db";
    private final ConnectionSource connectionSource;
    private final ItemDAO itemDAO;
    private final SectionDAO sectionDAO;

    public DatabaseManager() throws Exception {
        connectionSource = new JdbcConnectionSource(DB_URL);
        itemDAO = new ItemDAO(connectionSource);
        sectionDAO = new SectionDAO(connectionSource);
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public ItemDAO items(){
        return itemDAO;
    }

    public SectionDAO sections(){
        return sectionDAO;
    }

    public void initTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, ExperimentItem.class);
        TableUtils.createTableIfNotExists(connectionSource, Section.class);
    }

    public void close() throws Exception {
        connectionSource.close();
    }
}
