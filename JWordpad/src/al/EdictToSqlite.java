package al;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author alaix
 */
public class EdictToSqlite
{
    void Open(String file)
    {
        try
        {
            FileInputStream fis = new FileInputStream(file);
            Arreglo ar = new Arreglo();
            int c;
            int i=0;
            while((c = fis.read()) != -1)
            {
                if(c == '\n')
                {
                    String entry = ar.toString();
                    ar.v.clear();/*
                    if(entry.contains("？"))
                        continue;*/
                    readEntry(entry);
                    /*if(i++ >= 500)
                        break;*/
                }
                else
                    ar.add(c);
            }
            fis.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void readEntry(String entry)
    {
        i=0;
        newEntry();
        readKanjis(entry);
        readReadings(entry);
        readGloss(entry);
        endEntry();
    }
    private void readKanjis(String entry)
    {
        StringBuilder sb = new StringBuilder();
        for(; entry.charAt(i) != ' '; i++)
        {
            if(entry.charAt(i) == '(')
            {
                while(entry.charAt(++i) != ')');
                continue;
            }
            if(entry.charAt(i) == ';')
            {
                newKanji(sb.toString());
                sb.setLength(0);
            }
            else
                sb.append(entry.charAt(i));
        }
        newKanji(sb.toString());
        i++;
    }
    private void readReadings(String entry)
    {
        if(entry.charAt(i) != '[')
            return;
        i++;
        StringBuilder sb = new StringBuilder();
        /*
        try
        {
        */
        for(; entry.charAt(i) != ']'; i++)
        {
            if(entry.charAt(i) == '(')
            {
                while(entry.charAt(++i) != ')');
                continue;
            }
            if(entry.charAt(i) == ';')
            {
                newReading(sb.toString());
                sb.setLength(0);
            }
            else
                sb.append(entry.charAt(i));
        }
        /*
        }
        catch(StringIndexOutOfBoundsException e)
        {
            System.out.println(e.getMessage());
        }
        */
        newReading(sb.toString());
        i++;
        i++;
    }
    private void readGloss(String entry)
    {
        StringBuilder sb = new StringBuilder();
        
        for(;;)
        {
            i++;
            if(entry.charAt(i) == '(')
            {
                while(entry.charAt(++i) != ')');
                i++;
            }
            else if(entry.charAt(i) == '{')
            {
                while(entry.charAt(++i) != '}');
                i++;
            }
            else
                break;
        }
        for(;i < entry.length(); i++)
        {    
            if(entry.charAt(i) == '/')
            {
                if(i+1 == entry.length())
                    break;
                newGloss(sb.toString());
                sb.setLength(0);
                for(;;)
                {
                    i++;
                    if(entry.charAt(i) == '(')
                    {
                        while(entry.charAt(++i) != ')');
                        i++;
                    }
                    else if(entry.charAt(i) == '{')
                    {
                        while(entry.charAt(++i) != '}');
                        i++;
                    }
                    else
                    {
                        i--;
                        break;
                    }
                }
            }
            else
                sb.append(entry.charAt(i));
        }
    }
    ///////////////////////
    void InitDB() throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:edict.db");
        stat = conn.createStatement();
        
        stat.executeUpdate("drop table if exists kanji;");
        stat.executeUpdate("drop table if exists reading;");
        stat.executeUpdate("drop table if exists gloss;");
        stat.executeUpdate("drop table if exists entry;");
        stat.executeUpdate("drop table if exists entry_kanji;");
        stat.executeUpdate("drop table if exists entry_reading;");
        stat.executeUpdate("drop table if exists entry_gloss;");
        stat.executeUpdate("CREATE TABLE kanji(idk INTEGER PRIMARY KEY ASC AUTOINCREMENT, ka varchar(500) NOT NULL UNIQUE);");
        stat.executeUpdate("CREATE TABLE reading(idr INTEGER PRIMARY KEY ASC AUTOINCREMENT, re varchar(500) NOT NULL UNIQUE);");
        stat.executeUpdate("CREATE TABLE gloss(idg INTEGER PRIMARY KEY ASC AUTOINCREMENT, gl varchar(500) NOT NULL UNIQUE);");
        stat.executeUpdate("CREATE TABLE entry(ide INTEGER PRIMARY KEY ASC AUTOINCREMENT);");
        stat.executeUpdate("CREATE TABLE entry_kanji(ide INTEGER NOT NULL, idk INTEGER NOT NULL);");
        stat.executeUpdate("CREATE TABLE entry_reading(ide INTEGER NOT NULL, idr INTEGER NOT NULL);");
        stat.executeUpdate("CREATE TABLE entry_gloss(ide INTEGER NOT NULL, idg INTEGER NOT NULL);");
        
        pSelKanji = conn.prepareStatement("SELECT idk FROM kanji WHERE ka=?;");
        pSelReading = conn.prepareStatement("SELECT idr FROM reading WHERE re=?;");
        pSelGloss = conn.prepareStatement("SELECT idg FROM gloss WHERE gl=?;");
        
        pInsEntry = conn.prepareStatement("INSERT INTO entry (ide) VALUES (?);");
        pInsKanji = conn.prepareStatement("INSERT INTO kanji (ka) VALUES (?);");
        pInsReading = conn.prepareStatement("INSERT INTO reading (re) VALUES (?);");
        pInsGloss = conn.prepareStatement("INSERT INTO gloss (gl) VALUES (?);");
        pInsEntryKanji = conn.prepareStatement("INSERT INTO entry_kanji (ide, idk) VALUES (?, ?);");
        pInsEntryReading = conn.prepareStatement("INSERT INTO entry_reading (ide, idr) VALUES (?, ?);");
        pInsEntryGloss = conn.prepareStatement("INSERT INTO entry_gloss (ide, idg) VALUES (?, ?);");
        
        conn.setAutoCommit(false);
    }
    void newEntry()
    {
        try {
            ide++;
            pInsEntry.setInt(1, ide);
            pInsEntry.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void newKanji(String K)
    {
        int id = 0, idk;
        try
        {
            pInsKanji.setString(1, K);
            pInsKanji.executeUpdate();
        }
        catch(SQLException e)
        {
            try {
                pInsKanji.close();
                pInsKanji = conn.prepareStatement("INSERT INTO kanji (ka) VALUES (?);");
            } catch (SQLException ex) {
                Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(e.toString().contains("unique"));
            {
                rk++;
                try {
                    pSelKanji.setString(1, K);
                    ResultSet rs = pSelKanji.executeQuery();
                    rs.next();
                    id = rs.getInt("idk");
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            if(id == 0)
            {
                ResultSet resultSet = pInsKanji.getGeneratedKeys();
                resultSet.next();
                idk = resultSet.getInt(1);
                resultSet.close();
            }
            else
                idk = id;
            pInsEntryKanji.setInt(1, ide);
            pInsEntryKanji.setInt(2, idk);
            pInsEntryKanji.executeUpdate();
            
        } catch (SQLException ex)
        {
            Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void newReading(String R)
    {
        int id = 0, idr;
        try {
            pInsReading.setString(1, R);
            pInsReading.executeUpdate();
        }
        catch(SQLException e)
        {
            try {
                pInsReading.close();
                pInsReading = conn.prepareStatement("INSERT INTO reading (re) VALUES (?);");
            } catch (SQLException ex) {
                Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(e.toString().contains("unique"));
            {
                rr++;
                try {
                    pSelReading.setString(1, R);
                    ResultSet rs = pSelReading.executeQuery();
                    rs.next();
                    id = rs.getInt("idr");
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try {
            if(id == 0)
            {
                ResultSet resultSet = pInsReading.getGeneratedKeys();
                resultSet.next();
                idr = resultSet.getInt(1);
                resultSet.close();
            }
            else
                idr = id;
            pInsEntryReading.setInt(1, ide);
            pInsEntryReading.setInt(2, idr);
            pInsEntryReading.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void newGloss(String G)
    {
        int id = 0, idg;
        try {
            pInsGloss.setString(1, G);
            pInsGloss.executeUpdate();
        }
        catch(SQLException e)
        {
            try {
                pInsGloss.close();
                pInsGloss = conn.prepareStatement("INSERT INTO gloss (gl) VALUES (?);");
            } catch (SQLException ex) {
                Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(e.toString().contains("unique"));
            {
                rg++;
                try {
                    pSelGloss.setString(1, G);
                    ResultSet rs = pSelGloss.executeQuery();
                    rs.next();
                    id = rs.getInt("idg");
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        try
        {
            if(id == 0)
            {
                ResultSet resultSet = pInsGloss.getGeneratedKeys();
                resultSet.next();
                idg = resultSet.getInt(1);
                resultSet.close();
            }
            else
                idg = id;
            pInsEntryGloss.setInt(1, ide);
            pInsEntryGloss.setInt(2, idg);
            pInsEntryGloss.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void endEntry()
    {
        /*try {
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(EdictToSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    int i;
    Connection conn;
    Statement stat;
    int ide;
    int rg, rr, rk;
    PreparedStatement pSelKanji;
    PreparedStatement pSelReading;
    PreparedStatement pSelGloss;
    PreparedStatement pInsKanji;
    PreparedStatement pInsReading;
    PreparedStatement pInsGloss;
    PreparedStatement pInsEntry;
    PreparedStatement pInsEntryKanji;
    PreparedStatement pInsEntryReading;
    PreparedStatement pInsEntryGloss;
    
    public static void main(String[] args) throws Exception
    {
        /*Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:edict.db");
        Statement stat = conn.createStatement();
        */
        /*stat.executeUpdate("drop table if exists people;");
        stat.executeUpdate("create table people (name, occupation);");
        PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?);");

        prep.setString(1, "Gandhi");
        prep.setString(2, "politics");
        prep.addBatch();
        prep.setString(1, "Turing");
        prep.setString(2, "computers");
        prep.addBatch();
        prep.setString(1, "Wittgenstein");
        prep.setString(2, "smartypants");
        prep.addBatch();

        conn.setAutoCommit(false);
        prep.executeBatch();
        conn.setAutoCommit(true);

        ResultSet rs = stat.executeQuery("select * from people;");
        while (rs.next())
        {
            System.out.println("name = " + rs.getString("name"));
            System.out.println("job = " + rs.getString("occupation"));
        }
        rs.close();
        conn.close();
        */
        System.out.println("Se va a ejecutar Edict2Sqlite Seguro [y/N]");
        Scanner scan = new Scanner(System.in);
        if(!scan.nextLine().equals("y"))
            return;
        EdictToSqlite rts = new EdictToSqlite();
        rts.InitDB();
        rts.Open("edict2");
        rts.conn.commit();
        rts.conn.close();
        System.out.println("rg = "+rts.rg);
        System.out.println("rr = "+rts.rr);
        System.out.println("rk = "+rts.rk);
  }
}
/*
kanji(idk, ka, pro?)
reading(idr, re)
gloss(ids, gl, pro?);
ide(ide)
entry_kanji(ide, idk)
entry_reading(ide, idr)
entry_gloss(ide, idg)
 */

class Arreglo
{
    Vector v;

    public Arreglo()
    {
        v = new Vector();
    }

    void add(int i)
    {
        v.addElement(new Byte((byte)i));
    }
    public String toString()
    {
         try
         {
             byte [] ar = new byte[v.size()];
             for(int i=0; i<v.size(); i++)
                 ar[i] = ((Byte)v.elementAt(i)).byteValue();
             
            return new String(ar, "EUC-JP");
         }
         catch (UnsupportedEncodingException ex)
         {
            return "Nel";
         }
    }
}