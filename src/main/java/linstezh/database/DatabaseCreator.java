package linstezh.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreator {
    public static void createDB(ConnectDB db) {
        Connection connection = db.getConnection();

        if(connection != null){
            try{
                Statement statement = connection.createStatement();

                String sql = """
                        CREATE TABLE IF NOT EXISTS items (\
                        id INTEGER PRIMARY KEY,\
                        text text NOT NULL,\
                        corEval INT\
                        );
                """;
                statement.executeUpdate(sql);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
