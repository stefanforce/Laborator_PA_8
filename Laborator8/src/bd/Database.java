package bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public Connection conn;
    private static Database db;
    private Database()
    {/**connecting on local host */
        String url="jdbc:oracle:thin:@localhost:1521:xe";
        String dbName="MusicAlbums";
        /** couldn't create the user dba*/
        String user="dbaa";
        String password="sql";
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
        }
     catch (Exception e) {
        e.printStackTrace();
    }
    }
    /** modelul de tip singleton,dupa ce a fost creat un obiect,nu mai poate fi creat altu*/
    public static synchronized Database getObj() {
        if ( db == null ) {
            db = new Database();
        }
        return db;

    }
}
