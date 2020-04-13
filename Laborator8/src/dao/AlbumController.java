package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class AlbumController {
    private Connection conn;
    static int id=1;
    /** adaugarea unei linii in tabelul albums*/
    public void create(String name,int artistId,int releaseYear)
    {/** ? : can be inserted with setString below*/
        try{String sql = "insert into albums (id,name,artist_id,release_year) values (?,?,?,?)";
            PreparedStatement pstmt=  conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setInt(3,artistId);
            pstmt.setInt(4,releaseYear);

            pstmt.executeUpdate();

               String sql1 = "insert into charts (album_id,rating) values (?,?)";
            PreparedStatement pstmt1=  conn.prepareStatement(sql1);
            pstmt1.setInt(1,id);
            Random rand=new Random();
            int k=rand.nextInt(100);
            pstmt1.setInt(2,k);
            pstmt1.executeUpdate();
            /** incrementam id-ul */
            id++;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public AlbumController(Connection conn)
    {
        this.conn=conn;
    }
/** cautarea unui album in tabela dupa id-ul artistului */
    public void findByName(int artistId)
    {
        try {
            String sql = "SELECT * from albums where artist_id=?";
            PreparedStatement pstmt=  conn.prepareStatement(sql);
            pstmt.setInt(1,artistId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("name");
                int artist=rs.getInt("artist_id");
                int releaseYear=rs.getInt("release_year");
                System.out.println(id + ", " + nume+ ", "+artist+", "+releaseYear);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
