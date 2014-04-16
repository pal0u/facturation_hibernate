package testdao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import outils.OutilsCollection;
import outils.OutilsConnexion;
import pojo.Categorie;
import connexion.MyException;
import connexion.Singleton;
import dao.CategorieDao;

public class TesterCategorieDao2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// récupérer Properties
		String path = "properties/paramsJDBC.properties";
		Properties props = OutilsConnexion.chargerProps(path);
		Connection conn = null;
		CategorieDao categoriedao = null;
		ResultSet rs = null;
		try {
			conn = Singleton.getInstance(props); // utiliser Singleton
			categoriedao = new CategorieDao(conn);
		
			rs = categoriedao.findResult();
			// faire une boucle
			/*
			try {
				while( rs.next() ){
					System.out.println(rs.getInt(1) + " " + rs.getString(2));					
				}
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new MyException("problème avec la lecture des enregistrements ...");
			}
			*/
			

	System.out.println(rs);
	try {
		// parcourir les enregistrements
		int nb = 0;
		while ( rs.next() ){
			System.out.println(rs.getInt(1) + " " + rs.getString(2));
			nb++;
		}
		System.out.println("Nombre d'enregistrements: " + nb);
		
		// on se replace au niveau du 1er enregistrement
		rs.first();
		System.out.println(rs.getInt(1) + " " + rs.getString(2));
		// on se replace au niveau du dernier enregistrement
		rs.last();
		System.out.println(rs.getInt(1) + " " + rs.getString(2));
		
		rs.first();
		int i = 0;
		do {
			System.out.println(rs.getInt(1) + " " + rs.getString(2));
			rs.next();
			i++;
		}
		while(i<nb);
		
		/*
		rs.first()
		rs.next()
		rs.previous()
		rs.afterLast();
		*/
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
			
		} 
		catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
			if ( conn != null ){
				try {
					conn.close();
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Singleton.reinitialize();
			} 
			catch (MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
