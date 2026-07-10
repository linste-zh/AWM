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
import linstezh.database.mapper.ExperimentItemMapper;
import linstezh.database.mapper.ExperimentMapper;
import linstezh.database.mapper.ItemMapper;
import linstezh.database.mapper.SectionMapper;
import linstezh.logic.Experiment.Experiment;
import linstezh.logic.Item.Item;
import linstezh.logic.Item.ItemInterface;
import linstezh.logic.Item.ItemTypes;
import linstezh.logic.Section.Section;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

    public void initTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, ExperimentDBO.class);
        TableUtils.createTableIfNotExists(connectionSource, SectionDBO.class);
        TableUtils.createTableIfNotExists(connectionSource, ItemDBO.class);
        TableUtils.createTableIfNotExists(connectionSource, ExperimentItemDBO.class);
    }

    /*public ExperimentDAO experiments(){
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
    }*/

    public List<Experiment> getAllExperiments() throws SQLException {
        List<ExperimentDBO> expDBOs = experimentDAO.getAll();
        List<Experiment> allExps = new ArrayList<>();
        for(ExperimentDBO dbo : expDBOs){
            allExps.add(ExperimentMapper.fromDBO(dbo));
        }
        return allExps;
    }

    public Experiment loadExperiment(Experiment experiment) throws SQLException {
        List<SectionDBO> expSections = sectionDAO.getByExperimentID(experiment.getID());
        for(SectionDBO sectionDBO : expSections){
            Section section = SectionMapper.fromDBO(sectionDBO, experiment);
            experiment.addSection(section);

            List<ItemDBO> sectionItems = itemDAO.getBySectionID(section.getID());
            for(ItemDBO itemDBO : sectionItems){
                section.addItem(transformItemDBO(itemDBO, section));
            }
        }

        return experiment;
    }

    public ItemInterface transformItemDBO(ItemDBO itemDBO, Section section) throws SQLException {
        Item item = ItemMapper.fromDBO(itemDBO, section);
        if(item.getType() == ItemTypes.EXPERIMENT){
            ExperimentItemDBO experimentInfo = experimentItemDAO.getByItemID(item.getID());
            return ExperimentItemMapper.fromDBO(experimentInfo, item);
        }else{
            return item;
        }
    }

    public void close() throws Exception {
        connectionSource.close();
    }
}
