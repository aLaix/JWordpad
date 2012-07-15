/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author alaix
 */
public class Edict
{
    public Edict() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:edict.db");
        
        //pSelIdeByRe = conn.prepareStatement("SELECT re, ide FROM reading, entry_reading WHERE re = ? AND reading.idr = entry_reading.idr");
        pSelIdeByReL = conn.prepareStatement("SELECT re, ide FROM reading, entry_reading WHERE re like ? AND reading.idr = entry_reading.idr LIMIT 10");
        pSelKaByIde = conn.prepareStatement("SELECT ka FROM entry_kanji, kanji WHERE entry_kanji.ide = ? AND entry_kanji.idk = kanji.idk");
        pSelGlByIde = conn.prepareStatement("SELECT gl FROM entry_gloss, gloss WHERE entry_gloss.ide = ? AND entry_gloss.idg = gloss.idg");
    }
    
    public ArrayList<String> SearchByReading(String R, boolean Begin, boolean End, EdictCallback ecb) throws SQLException
    {
        if(Begin && End)
            pSelIdeByReL.setString(1, R);
        else if(Begin && !End)
            pSelIdeByReL.setString(1, R+'%');
        else if(!Begin && End)
            pSelIdeByReL.setString(1, '%'+R);
        else
            pSelIdeByReL.setString(1, '%'+R+'%');
        //sel.setString(1, R);
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> aRe = new ArrayList<String>();
        ArrayList<Integer> aIde = new ArrayList<Integer>();
        ResultSet rs = pSelIdeByReL.executeQuery();
        while(rs.next())
        {
            aRe.add(rs.getString("re"));
            aIde.add(rs.getInt("ide"));
        }
        rs.close();
        for(int i=0; i<aRe.size(); i++)
        {
            StringBuilder sb = new StringBuilder();
            pSelKaByIde.setInt(1, aIde.get(i));
            rs = pSelKaByIde.executeQuery();
            while(rs.next())
                sb.append(rs.getString("ka")).append(", ");
            rs.close();
            String ka = sb.toString();
            sb.setLength(0);
            pSelGlByIde.setInt(1, aIde.get(i));
            rs = pSelGlByIde.executeQuery();
            while(rs.next())
                sb.append(rs.getString("gl")).append("; ");
            rs.close();
            result.add(ka+" ["+aRe.get(i) +"]");
            result.add(sb.toString());
            ecb.funcion(ka+" ["+aRe.get(i) +"]");
            ecb.funcion(sb.toString());
        }
        return result;
    }
    
    //PreparedStatement pSelIdeByRe;
    PreparedStatement pSelIdeByReL;
    PreparedStatement pSelKaByIde;
    PreparedStatement pSelGlByIde;
}