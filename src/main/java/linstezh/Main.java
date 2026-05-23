package linstezh;


import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.BaseConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import linstezh.database.ConnectDB;
import linstezh.database.dao.ItemDAO;
import linstezh.database.mapper.ItemMapper;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ConnectDB db = new ConnectDB();
        Connection connection = db.getConnection();
        if (connection != null) {
            try {
                ConnectionSource src = new JdbcConnectionSource("jdbc:sqlite:database/database.db");
                // Auto-create the "users" table if it doesn't exist
                TableUtils.createTableIfNotExists(src, ItemMapper.class);

                // Initialize DAO
                ItemDAO itemDAO = new ItemDAO(src);

                itemDAO.createItem("1", 1, "Example Text", "Text","happy", true);

                // Perform CRUD operations (see Section 4.5)
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}