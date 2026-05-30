package linstezh.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import linstezh.logic.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {
    private final Dao<Participant, Integer> participantDao;
    ConnectionSource src;

    public ParticipantDAO(ConnectionSource src) throws Exception {
        this.src = src;
        participantDao = DaoManager.createDao(src, Participant.class);
    }

    public void create(Participant item) throws Exception {
        participantDao.create(item);
        System.out.println("Created item: " + item);
    }

    public List<Participant> getAll(){
        ArrayList<Participant> allParticipants = new ArrayList<>();

        for (Participant participant : participantDao) {
            allParticipants.add(participant);
        }

        return allParticipants;
    }
}
