package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
