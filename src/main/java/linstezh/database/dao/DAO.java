package linstezh.database.dao;

import linstezh.database.dbo.DBO;
import linstezh.exceptions.databaseIdException;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    DBO getByID(int id) throws databaseIdException, SQLException;
}
