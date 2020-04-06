import bd.Database;
import dao.AlbumController;
import dao.ArtistController;

import java.sql.Connection;
import java.sql.Statement;



public class Main {
    public static void main(String[] args) {
        Connection conn;
        try {
            Database db = Database.getObj();
            conn= db.conn;
            Statement stmt = conn.createStatement();
            /** crearea tabelelor artists si albums*/
            stmt.execute("create table artists(" +
                  "id integer primary key," +
                   "name varchar(100) not null," +
                   "country varchar(100)" +
                   ")");
            stmt.execute("create table albums(" +
                "id integer primary key," +
                   "name varchar(100) not null," +
                   "artist_id integer not null, " +
                   "release_year integer, " +
                  "foreign key (artist_id) references artists (id)" +
                   ")");

            AlbumController albumController = new AlbumController(conn);
            ArtistController artistController = new ArtistController(conn);

/** popularea tabelelor artists si albums */
            artistController.create("Mihai","Georgia");
            artistController.create("George","Macedonia");
            artistController.create("Ahmed","Romania");
            albumController.create("Rockers",1,2019);
            albumController.create("Magical Flute",1,2018);
            albumController.create("119th Symphony",2,2024);
            albumController.create("Inspiration",3,1994);

            artistController.findByName("Mihai");
            albumController.findByName(1);
            Database.getObj().conn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
