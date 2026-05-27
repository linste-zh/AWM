package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.logic.ExperimentItem;
import linstezh.logic.Participant;
import linstezh.logic.ParticipantMemResponse;
import linstezh.logic.Section;

import java.util.ArrayList;
import java.util.List;

public class ParticipantMemResponseDAO {
    private final Dao<ParticipantMemResponse, Integer> pmrDao;
    ConnectionSource src;

    public ParticipantMemResponseDAO(ConnectionSource src) throws Exception {
        this.src = src;
        pmrDao = DaoManager.createDao(src, ParticipantMemResponse.class);
    }

    public void create(ParticipantMemResponse response) throws Exception {
        pmrDao.create(response);
    }

    public ParticipantMemResponse getForItemAndParticipant(ExperimentItem item, Participant participant){
        for (ParticipantMemResponse pmr : pmrDao) {
            if(pmr.getItem() == item && pmr.getParticipant() == participant) {
                return pmr;
            }
        }
        return null;
    }

    public List<ParticipantMemResponse> getAll(){
        ArrayList<ParticipantMemResponse> allPMRs = new ArrayList<>();

        for (ParticipantMemResponse participant : pmrDao) {
            allPMRs.add(participant);
        }

        return allPMRs;
    }
}
