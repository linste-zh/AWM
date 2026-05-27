package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.logic.*;

import java.util.ArrayList;
import java.util.List;


public class ParticipantEvalResponseDAO {
    private final Dao<ParticipantEvalResponse, Integer> perDao;
    ConnectionSource src;

    public ParticipantEvalResponseDAO(ConnectionSource src) throws Exception {
        this.src = src;
        perDao = DaoManager.createDao(src, ParticipantEvalResponse.class);
    }

    public void create(ParticipantEvalResponse response) throws Exception {
        perDao.create(response);
    }

    public ParticipantEvalResponse getForItemAndParticipant(ExperimentItem item, Participant participant){
        for (ParticipantEvalResponse per : perDao) {
            if(per.getItem() == item && per.getParticipant() == participant) {
                return per;
            }
        }
        return null;
    }

    public List<ParticipantEvalResponse> getAll(){
        ArrayList<ParticipantEvalResponse> allParticipantEvalResponses = new ArrayList<>();

        for (ParticipantEvalResponse response : perDao) {
            allParticipantEvalResponses.add(response);
        }

        return allParticipantEvalResponses;
    }
}
