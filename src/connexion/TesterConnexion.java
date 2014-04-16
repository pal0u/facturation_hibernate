package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TesterConnexion {
	
	public static void main(String[] args) {
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver ok");
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String serveur = "192.168.6.103";
		String database = "olivier";
		String admin = "root";
		String password = "hpph5233";
		
		String url = "jdbc:mysql://"+ serveur +"/" + database;
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, admin, password);
			System.out.println("connexion réussie");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
