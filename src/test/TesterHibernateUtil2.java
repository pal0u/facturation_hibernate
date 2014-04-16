package test;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Adresse;
import pojo.Pays;


public class TesterHibernateUtil2 {

	public static void main(String[] args) {
		
		Session session = null;
		session = HibernateUtil.instance().openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); //commencer la transaction
			
			Pays pays = new Pays();
			pays.setNomPays("Belgique");
			session.persist(pays);
			
			Adresse adresse = new Adresse();
			adresse.setRue("Rue Victor Hugo");
			adresse.setNumero("15");
			adresse.setZipcode("7000");
			adresse.setVille("Mons");
			adresse.setPays(pays);
			session.persist(adresse);
			
			Adresse adresse2 = new Adresse();
			adresse2.setRue("Rue Victor Pas Hugo");
			adresse2.setNumero("995");
			adresse2.setZipcode("7020");
			adresse2.setVille("Nimy");
			adresse2.setPays(pays);
			session.persist(adresse2);
			
			
			
			tx.commit(); // Mettre physiquement les objets créés dans la base de données
			System.out.println("pas de problème de mapping");
			
			Set<Adresse> liste = pays.getAdresses();
			for(Adresse a : liste){
				System.out.println(a);
			}
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
