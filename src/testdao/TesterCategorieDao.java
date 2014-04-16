package testdao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import outils.OutilsCollection;
import outils.OutilsConnexion;
import pojo.Categorie;
import connexion.MyException;
import connexion.Singleton;
import dao.CategorieDao;

public class TesterCategorieDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// récupérer Properties
		String path = "properties/paramsJDBC.properties";
		Properties props = OutilsConnexion.chargerProps(path);
		Connection conn = null;
		CategorieDao categoriedao = null;
		Categorie c = null;
		Categorie r = null;
		try {
			conn = Singleton.getInstance(props); // utiliser Singleton
			categoriedao = new CategorieDao(conn);
			
			// tester la récupération si table vide
			// retourne exception
//			c = categoriedao.find(new Integer(1));
//			System.out.println(c);
			
//			System.out.println("insérer Catégorie");
//			c = new Categorie();
//			c.setNomCategorie("HardDisk");
//			c.setIdCategorie(new Integer(0));
//			categoriedao.insert(c);
//			System.out.println("catégorie insérée");
//			System.out.println(c);
//			
//			System.out.println("récupérer objet catégorie");
//			r = categoriedao.find(new Integer(8));
//			System.out.println(r);
//			
//			System.out.println("modifier nom");
//			r.setNomCategorie("MotherCard");
//			System.out.println(r);			
//			categoriedao.update(r);
//			
//			System.out.println("récupérer objet catégorie modifié");
//			r = categoriedao.find(new Integer(8));
//			System.out.println(r);
//			
//			//System.out.println("supprimer catégorie avec id 7");
//			//categoriedao.delete(r);
			
			//ok
//			List<Categorie> recupList = categoriedao.findBetween(new Integer(1), new Integer(10));
//			System.out.println(OutilsCollection.listerCollection(recupList));
			
			Categorie c1 = new Categorie();
			c1.setIdCategorie(new Integer(0));
			c1.setNomCategorie("test1");
			Categorie c2 = new Categorie();
			c2.setIdCategorie(new Integer(0));
			c2.setNomCategorie("test2");
			Categorie c3 = new Categorie();
			c3.setIdCategorie(new Integer(0));
			c3.setNomCategorie("test3");
			Categorie c4 = new Categorie();			
			c4.setIdCategorie(new Integer(0));
			c4.setNomCategorie("test4");
			Categorie c5 = new Categorie();
			c5.setIdCategorie(new Integer(0));
			c5.setNomCategorie("test5");
			Categorie[] tabcategorie = {c1,c2,c3,c4,c5};
			categoriedao.insert(tabcategorie);
			
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
