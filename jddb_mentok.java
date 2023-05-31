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
	             // JDBC kapcsolat l�trehoz�sa
	           //  Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:db", "felhaszn�l�n�v", "jelsz�");

	             // �llom�ny beolvas�sa �s feldolgoz�sa
	             BufferedReader reader = new BufferedReader(new FileReader("adatok.csv"));
	             String line;
	             while ((line = reader.readLine()) != null) {
	                 String[] data = line.split(",");
	                 // Adatok besz�r�sa a t�bl�ba
	                 String insertQuery = "INSERT INTO Betegek (id, n�v, kor, ment��llom�s_id, k�rh�z_id) VALUES (?, ?, ?, ?, ?)";
	                 PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
	                 preparedStatement.setInt(1, Integer.parseInt(data[0]));
	                 preparedStatement.setString(2, data[1]);
	                 preparedStatement.setInt(3, Integer.parseInt(data[2]));
	                 preparedStatement.setInt(4, Integer.parseInt(data[3]));
	                 preparedStatement.setInt(5, Integer.parseInt(data[4]));
	                 preparedStatement.executeUpdate();
	                 preparedStatement.close();
	             }
	             
	             // Bez�rjuk az er�forr�sokat
	             reader.close();
	             conn.close();
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	     	    	
 }

	        // Adatb�zis kapcsolat be�ll�t�sa
	            
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
	    		// SQL utas�t�s a t�bla l�trehoz�s�hoz
	            String createTableQuery = "CREATE TABLE Betegek (\r\n"
	            		+ "    id INT PRIMARY KEY,\r\n"
	            		+ "    n�v VARCHAR(50),\r\n"
	            		+ "    kor INT,\r\n"
	            		+ "    ment��llom�s_id INT,\r\n"
	            		+ "    k�rh�z_id INT,\r\n"
	            		+ "    FOREIGN KEY (ment��llom�s_id) REFERENCES Ment��llom�s(id),\r\n"
	            		+ "    FOREIGN KEY (k�rh�z_id) REFERENCES K�rh�z(id)\r\n"
	            		+ ");\r\n";
	            	   	

	    	try {
	            // Ment�si parancs el�k�sz�t�se
	            String con = "INSERT INTO mytable (column1, column2, column3) VALUES (?, ?, ?)";
	            PreparedStatement statement = conn.prepareStatement(con);

	            // Adatok be�ll�t�sa
	            statement.setString(1, "Value 1");
	            statement.setInt(2, 123);
	            statement.setDouble(3, 45.67);

	            // Ment�s v�grehajt�sa
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Sikeres ment�s!");
	            }
	            // Kapcsolat bez�r�sa
	            statement.close();
	    	}  catch (SQLException | FileNotFoundException e) {
			// HIBA
			e.printStackTrace();
	        } catch(NumberFormatException e) {
	            e.printStackTrace();
	        }
	    	String sql = "{ call uj_mento_hivas(?, ?, ?, ?, ?) }";
	    	CallableStatement statement = conn.prepareCall(sql);

	    	// Param�terek be�ll�t�sa
	    	statement.setString(1, "Johny Waker :) ");
	    	statement.setString(2, "123 Main Street");
	    	statement.setString(3, "Ment��llom�s 1");
	    	statement.setString(4, "S�lyos s�r�l�s");
	    	statement.setString(5, "2023-05-26 10:30:00");

	    	// Elj�r�s megh�v�sa
	    	statement.execute();

	    	statement.close();
	      }
	            	{
	            			
	            	String mentoAllomasQuery = "SELECT id FROM Ment��llom�sok WHERE n�v = ?";
	            	PreparedStatement mentoAllomasStatement = conn.prepareStatement(mentoAllomasQuery);
	            	mentoAllomasStatement.setString(1,"Ment��llom�s 1");
	            	ResultSet mentoAllomasResult = mentoAllomasStatement.executeQuery();

	            	int mentoAllomasId = (Integer) null ;
	            	if (mentoAllomasResult.next()) {
	            	    mentoAllomasId = mentoAllomasResult.getInt("id");
	            	}

	            	mentoAllomasResult.close();
	            	mentoAllomasStatement.close();
				
			}
{
		String hivasInsertQuery = "INSERT INTO H�v�sok (n�v, c�m, ment��llom�s_id, jelleg, id�pont) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement hivasInsertStatement = conn.prepareStatement(hivasInsertQuery);
		hivasInsertStatement.setString(1, "John Smith");
		hivasInsertStatement.setString(2, "123 Main Street");
		int mentoAllomasId;
		hivasInsertStatement.setInt(3, mentoAllomasId);
		hivasInsertStatement.setString(4, "S�lyos s�r�l�s");
		hivasInsertStatement.setTimestamp(5, Timestamp.valueOf("2023-05-26 10:30:00"));
		
		hivasInsertStatement.executeUpdate();
		
		hivasInsertStatement.close();

}

String hivasokQuery = "SELECT * FROM H�v�sok";

/*
String hivasokQuery "SELECT h.*, b.n�v AS beteg_neve
FROM H�v�sok h
JOIN Betegek b ON h.beteg_id = b.id
WHERE h.id�pont BETWEEN TO_DATE('2023-05-01', 'YYYY-MM-DD') AND TO_DATE('2023-05-31', 'YYYY-MM-DD')"";
*/
{
Statement statement = conn.createStatement();
ResultSet resultSet = statement.executeQuery(hivasokQuery);

while (resultSet.next()) {
    int id = resultSet.getInt("id");
    String nev = resultSet.getString("n�v");
    String cim = resultSet.getString("c�m");
    int mentoallomasId = resultSet.getInt("mento�llom�s_id");
    String jelleg = resultSet.getString("jelleg");
    Timestamp idopont = resultSet.getTimestamp("id�pont");

    
}

resultSet.close();
statement.close();


}
}
}