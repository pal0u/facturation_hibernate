package connexion;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class TesterSingleton {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = null;
		Properties props = new Properties();
		
		String path = "properties/paramsJDBC.properties";
		File file = new File(path);
		FileReader fr = null;
		try {
			fr = new FileReader(file);;
			props.load(fr); // on récupère les paramètres dans l'objet Properties
			fr.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(props.getProperty("serveur")); //OK
		// pour vérifier
		
		//**********************************************
		
		try {
			conn = Singleton.getInstance(props); // générer un objet Connection via le singleton
			// afficher les tables dans la database via la classe DatabaseMetaData
			DatabaseMetaData dbmd = conn.getMetaData();
			String[] types = {"TABLE"};
			ResultSet rs = dbmd.getTables(null, null, "%", types);
			// signification des arguments
		       while( rs.next() )
		       {
		           String nomTable=rs.getString(3);
		           System.out.println(nomTable); // afficher le nom de la table
		 
		       }
		} 
		catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getGeneratedMessage());
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
