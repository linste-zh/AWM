package linstezh.database;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import linstezh.database.dao.*;
import linstezh.logic.*;

import java.sql.SQLException;


public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:database/database.db";
    private final ConnectionSource connectionSource;
    private static DatabaseManager INSTANCE;

    private final ItemDAO itemDAO;
    private final SectionDAO sectionDAO;
    private final ParticipantDAO participantDAO;
    private final ParticipantEvalResponseDAO perDAO;
    private final ParticipantMemResponseDAO pmrDAO;

    private DatabaseManager() throws Exception {
        connectionSource = new JdbcConnectionSource(DB_URL);
        itemDAO = new ItemDAO(connectionSource);
        sectionDAO = new SectionDAO(connectionSource);
        participantDAO = new ParticipantDAO(connectionSource);
        perDAO = new ParticipantEvalResponseDAO(connectionSource);
        pmrDAO = new ParticipantMemResponseDAO(connectionSource);
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

    public ItemDAO items(){
        return itemDAO;
    }

    public SectionDAO sections(){
        return sectionDAO;
    }

    public ParticipantDAO participants() {
        return participantDAO;
    }

    public ParticipantEvalResponseDAO participantEvalResponses() {
        return perDAO;
    }

    public ParticipantMemResponseDAO participantMemResponses() {
        return pmrDAO;
    }

    public void initTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, ExperimentItem.class);
        TableUtils.createTableIfNotExists(connectionSource, ExperimentSection.class);
        TableUtils.createTableIfNotExists(connectionSource, ParticipantEvalResponse.class);
        TableUtils.createTableIfNotExists(connectionSource, ParticipantMemResponse.class);
        TableUtils.createTableIfNotExists(connectionSource, Participant.class);
    }

    public void close() throws Exception {
        connectionSource.close();
    }
}
