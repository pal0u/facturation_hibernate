package test;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Adresse;
import pojo.Pays;


public class TesterHibernateUtil2b {

	public static void main(String[] args) {
		
		Session session = null;
		session = HibernateUtil.instance().openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); //commencer la transaction
			
			// Recuperer un objet pays dans la DB
			Pays pays = (Pays) session.get(Pays.class, new Integer(1));
			
			Set<Adresse> liste = pays.getAdresses();
			for(Adresse a : liste){
				System.out.println(a);
			}
			
			tx.commit(); // Mettre physiquement les objets créés dans la base de données
			System.out.println("pas de problème de mapping");
			
			
		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); // On lui dit de ne pas tenir compte des opérations effectuées
			e.printStackTrace(); 
		}
		finally {
			session.close(); 
		}
		
	}

}
