package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * classe pour générer un et un seul objet Connection 
 */

public class Singleton {
	
	// LES ATTRIBUTS static
	private static Connection conn = null;
	private static Properties props = null;
	
	// constructeur en privé
	private Singleton () throws MyException{
		String url = "jdbc:mysql://"+props.getProperty("serveur")+"/"+props.getProperty("database");
		try {
			conn = DriverManager.getConnection(url, props.getProperty("admin"),props.getProperty("password"));
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			//throw new MyException(e.getMessage()); 
			throw new MyException("création objet Connection impossible"); 
			// on crée un objet MyException si problème
		}
	}
	
	// méthode static pour récupérer un objet Connection
	// synchronized pour utiliser le singleton avec le multiThreading
	public static synchronized Connection getInstance(Properties myprops) throws MyException{
		props = myprops;
		if ( conn != null )		
			 return conn;
		else {
			 new Singleton(); // on utilise le constructeur private
			 return conn;
		}
	}
	
	// en fin d'utilisation, libérer la connexion
	public static void reinitialize() throws MyException {
		if ( conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// throw new MyException(e.getMessage()); 
				throw new MyException("fermeture objet Connection impossible"); 
			}
			}
		}
	
}
