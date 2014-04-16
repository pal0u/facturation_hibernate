package test;
import java.util.Date;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Adresse;
import pojo.Client;
import pojo.Pays;
import pojo.Vendeur;


public class TesterHibernateUtil2c {

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
			
			Client client = new Client();
			client.setNom("Dupont");
			client.setPrenom("Maurice");
			client.setEmail("dupontmaurice@hotmail.com");
			client.setTelephone("0475659432");
			client.setDateNaissance(new Date());
			client.setSexe('M');
			client.setRemise(0.00);
			client.setSociete("La Bulle Qui Pete");
			client.setNrTva("BE045987654");
			client.setAdresse(adresse);
			session.persist(client);
			
			Vendeur vendeur = new Vendeur();
			vendeur.setNom("Mars");
			vendeur.setPrenom("Chande");
			vendeur.setTelephone("0477956284");
			vendeur.setEmail("chandemars@gmal.be");
			session.persist(vendeur);
			
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
