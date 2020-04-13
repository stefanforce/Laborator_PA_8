import bd.Database;
import com.github.javafaker.Faker;
import dao.AlbumController;
import dao.ArtistController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



public class Main {
    public static void main(String[] args) {
        Connection conn;
        try {
            Database db = Database.getObj();
            conn = db.conn;
            Statement stmt = conn.createStatement();
            /** crearea tabelelor artist    */
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
            stmt.execute("create table charts(" +
                    "album_id integer not null, " +
                    "rating integer not null," +
                    "foreign key (album_id) references albums (id)" +
                    ")");

            AlbumController albumController = new AlbumController(conn);
            ArtistController artistController = new ArtistController(conn);


            artistController.create("Mihai", "Georgia");
            artistController.create("George", "Macedonia");
            artistController.create("Ahmed", "Romania");
            albumController.create("Rockers", 1, 2019);
            albumController.create("Magical Flute", 1, 2018);
            albumController.create("119th Symphony", 2, 2024);
            albumController.create("Inspiration", 3, 1994);

            System.out.println("FINDS:\n");
            artistController.findByName("Mihai");
            albumController.findByName(1);

            Faker faker = new Faker();

            artistController.create(new Faker().name().fullName(), new Faker().nation().nationality());
            artistController.create(new Faker().name().fullName(), new Faker().nation().nationality());

            albumController.create(new Faker().funnyName().name(), 3, 2001);
            albumController.create(new Faker().funnyName().name(), 4, 2015);
            albumController.create(new Faker().funnyName().name(), 4, 2023);

            System.out.println("RANKING:\n");
            ResultSet rs=stmt.executeQuery("select * from charts order by rating");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getInt(2));

            Database.getObj().conn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
