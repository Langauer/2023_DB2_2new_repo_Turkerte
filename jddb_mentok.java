package jddb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
//import java.io.FileNotFoundException;




public class jddb_mentok {

	
	
private Connection conn;
//	static Connection conn;

	
 public static void main(String[] args )   throws IOException{
	 
	 
	
	         Connection conn = null;
			try {
	             // JDBC kapcsolat létrehozása
	           //  Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:db", "felhasználónév", "jelszó");

	             // Állomány beolvasása és feldolgozása
	             BufferedReader reader = new BufferedReader(new FileReader("adatok.csv"));
	             String line;
	             while ((line = reader.readLine()) != null) {
	                 String[] data = line.split(",");
	                 // Adatok beszúrása a táblába
	                 String insertQuery = "INSERT INTO Betegek (id, név, kor, mentõállomás_id, kórház_id) VALUES (?, ?, ?, ?, ?)";
	                 PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
	                 preparedStatement.setInt(1, Integer.parseInt(data[0]));
	                 preparedStatement.setString(2, data[1]);
	                 preparedStatement.setInt(3, Integer.parseInt(data[2]));
	                 preparedStatement.setInt(4, Integer.parseInt(data[3]));
	                 preparedStatement.setInt(5, Integer.parseInt(data[4]));
	                 preparedStatement.executeUpdate();
	                 preparedStatement.close();
	             }
	             
	             // Bezárjuk az erõforrásokat
	             reader.close();
	             conn.close();
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	     	    	
 }

	        // Adatbázis kapcsolat beállítása
	            
	                // db parameters
	            	public Connection  connect(String dbnev) {

	            		this.conn = null;
	                    try {
	                        // db parameters
	                        String url = "jdbc:sqlite:C:/Users/tinka/Sqlite/jddb.db"+  dbnev;
	                        // create a connection to the database
	                        System.out.println(url);
	                        this.conn = DriverManager.getConnection(url);
	                        
	                        System.out.println("Connection to SQLite has been established.");
	                        
	                    } catch (SQLException e) {
	                        System.out.println(e.getMessage());
	                    }
	    		return conn;
	    		// SQL utasítás a tábla létrehozásához
	            String createTableQuery = "CREATE TABLE Betegek (\r\n"
	            		+ "    id INT PRIMARY KEY,\r\n"
	            		+ "    név VARCHAR(50),\r\n"
	            		+ "    kor INT,\r\n"
	            		+ "    mentõállomás_id INT,\r\n"
	            		+ "    kórház_id INT,\r\n"
	            		+ "    FOREIGN KEY (mentõállomás_id) REFERENCES Mentõállomás(id),\r\n"
	            		+ "    FOREIGN KEY (kórház_id) REFERENCES Kórház(id)\r\n"
	            		+ ");\r\n";
	            	   	

	    	try {
	            // Mentési parancs elõkészítése
	            String con = "INSERT INTO mytable (column1, column2, column3) VALUES (?, ?, ?)";
	            PreparedStatement statement = conn.prepareStatement(con);

	            // Adatok beállítása
	            statement.setString(1, "Value 1");
	            statement.setInt(2, 123);
	            statement.setDouble(3, 45.67);

	            // Mentés végrehajtása
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Sikeres mentés!");
	            }
	            // Kapcsolat bezárása
	            statement.close();
	    	}  catch (SQLException | FileNotFoundException e) {
			// HIBA
			e.printStackTrace();
	        } catch(NumberFormatException e) {
	            e.printStackTrace();
	        }
	    	String sql = "{ call uj_mento_hivas(?, ?, ?, ?, ?) }";
	    	CallableStatement statement = conn.prepareCall(sql);

	    	// Paraméterek beállítása
	    	statement.setString(1, "Johny Waker :) ");
	    	statement.setString(2, "123 Main Street");
	    	statement.setString(3, "Mentõállomás 1");
	    	statement.setString(4, "Súlyos sérülés");
	    	statement.setString(5, "2023-05-26 10:30:00");

	    	// Eljárás meghívása
	    	statement.execute();

	    	statement.close();
	      }
	            	{
	            			
	            	String mentoAllomasQuery = "SELECT id FROM Mentõállomások WHERE név = ?";
	            	PreparedStatement mentoAllomasStatement = conn.prepareStatement(mentoAllomasQuery);
	            	mentoAllomasStatement.setString(1,"Mentõállomás 1");
	            	ResultSet mentoAllomasResult = mentoAllomasStatement.executeQuery();

	            	int mentoAllomasId = (Integer) null ;
	            	if (mentoAllomasResult.next()) {
	            	    mentoAllomasId = mentoAllomasResult.getInt("id");
	            	}

	            	mentoAllomasResult.close();
	            	mentoAllomasStatement.close();
				
			}
{
		String hivasInsertQuery = "INSERT INTO Hívások (név, cím, mentõállomás_id, jelleg, idõpont) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement hivasInsertStatement = conn.prepareStatement(hivasInsertQuery);
		hivasInsertStatement.setString(1, "John Smith");
		hivasInsertStatement.setString(2, "123 Main Street");
		int mentoAllomasId;
		hivasInsertStatement.setInt(3, mentoAllomasId);
		hivasInsertStatement.setString(4, "Súlyos sérülés");
		hivasInsertStatement.setTimestamp(5, Timestamp.valueOf("2023-05-26 10:30:00"));
		
		hivasInsertStatement.executeUpdate();
		
		hivasInsertStatement.close();

}

String hivasokQuery = "SELECT * FROM Hívások";

/*
String hivasokQuery "SELECT h.*, b.név AS beteg_neve
FROM Hívások h
JOIN Betegek b ON h.beteg_id = b.id
WHERE h.idõpont BETWEEN TO_DATE('2023-05-01', 'YYYY-MM-DD') AND TO_DATE('2023-05-31', 'YYYY-MM-DD')"";
*/
{
Statement statement = conn.createStatement();
ResultSet resultSet = statement.executeQuery(hivasokQuery);

while (resultSet.next()) {
    int id = resultSet.getInt("id");
    String nev = resultSet.getString("név");
    String cim = resultSet.getString("cím");
    int mentoallomasId = resultSet.getInt("mentoállomás_id");
    String jelleg = resultSet.getString("jelleg");
    Timestamp idopont = resultSet.getTimestamp("idõpont");

    
}

resultSet.close();
statement.close();


}
}
}