package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArtistController {
    private Connection conn;
    static int id=1;
    /** adaugarea unei linii in tabelul artists*/
    public void create(String name,String country)
    {/** ? : can be inserted with setString below*/
        try{String sql = "insert into artists (id,name, country) values (?,?,?)";
        PreparedStatement pstmt=  conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        pstmt.setString(2,name);
        pstmt.setString(3,country);
        pstmt.executeUpdate();
        id++;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public ArtistController(Connection conn)
    {
        this.conn=conn;
    }
    /** cautarea unui artist dupa nume in tabela */
    public void findByName(String name)
    {
        try {
            String sql = "SELECT * from artists where name=?";
            PreparedStatement pstmt=  conn.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("name");
                String country =rs.getString("country");
                System.out.println(id + ", " + nume + ", "+country);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
