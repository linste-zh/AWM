package linstezh.database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import linstezh.database.dao.ExperimentDAO;
import linstezh.database.dao.ExperimentItemDAO;
import linstezh.database.dao.ItemDAO;
import linstezh.database.dao.SectionDAO;
import linstezh.database.dbo.ExperimentDBO;
import linstezh.database.dbo.ExperimentItemDBO;
import linstezh.database.dbo.ItemDBO;
import linstezh.database.dbo.SectionDBO;

import java.sql.SQLException;


public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:database/database.db";
    private final ConnectionSource connectionSource;
    private static DatabaseManager INSTANCE;

    private final ExperimentDAO experimentDAO;
    private final SectionDAO sectionDAO;
    private final ItemDAO itemDAO;
    private final ExperimentItemDAO experimentItemDAO;

    private DatabaseManager() throws Exception {
        connectionSource = new JdbcConnectionSource(DB_URL);
        experimentDAO = new ExperimentDAO(connectionSource);
        sectionDAO = new SectionDAO(connectionSource);
        itemDAO = new ItemDAO(connectionSource);
        experimentItemDAO = new ExperimentItemDAO(connectionSource);
    }

    public static DatabaseManager getInstance() throws Exception {
        if(INSTANCE == null){
            INSTANCE = new DatabaseManager();
        }
        return INSTANCE;
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public ExperimentDAO experiments(){
        return experimentDAO;
    }

    public SectionDAO sections(){
        return sectionDAO;
    }

    public ItemDAO items(){
        return itemDAO;
    }

    public ExperimentItemDAO experimentItems(){
        return experimentItemDAO;
    }

    public void initTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, ExperimentDBO.class);
        TableUtils.createTableIfNotExists(connectionSource, SectionDBO.class);
        TableUtils.createTableIfNotExists(connectionSource, ItemDBO.class);
        TableUtils.createTableIfNotExists(connectionSource, ExperimentItemDBO.class);
    }

    public void close() throws Exception {
        connectionSource.close();
    }
}
