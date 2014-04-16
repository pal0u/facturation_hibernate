package test;
import java.util.Date;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.*;;


public class TesterHibernateUtil2d {

	public static void main(String[] args) {
		
		Session session = null;
		session = HibernateUtil.instance().openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); //commencer la transaction
			
			// créer des catégories
			Categorie c1 = new Categorie();
			c1.setNomCategorie("Printer");
			session.persist(c1);
			Categorie c2 = new Categorie();
			c2.setNomCategorie("HardDisk");
			session.persist(c2);
			Categorie c3 = new Categorie();
			c3.setNomCategorie("MotherCard");
			session.persist(c3);
			Categorie c4 = new Categorie();
			c4.setNomCategorie("GraphicCard");
			session.persist(c4);
			Categorie c5 = new Categorie();
			c5.setNomCategorie("PC_Tower");
			session.persist(c5);
			
			// créer un produit simple
			Produit ps1 = new ProduitS();
			ps1.setLibelle("LaserJet HP 1200");
			ps1.setDescription("imprimante Laser Monochrome");
			ps1.setPrixHtva(100);
			ps1.setReference("HPDJ1200");
			ps1.setStock(5);
			ps1.setVignette("hp1200.png");
			ps1.setCategorie(c1);// fait partie de la catégorie c1
			session.persist(ps1);


			Produit ps2 = new ProduitS();
			ps2.setLibelle("NVidia 1256");
			ps2.setDescription("carte graphique NVidia");
			ps2.setPrixHtva(55);
			ps2.setReference("NV12563");
			ps2.setStock(10);
			ps2.setVignette("nvidia1256.png");
			ps2.setCategorie(c4);// fait partie de la catégorie c4
			session.persist(ps2);
			
			
			Produit ps3 = new ProduitS();
			ps3.setLibelle("HD Maxtor 125636");
			ps3.setDescription("disque dur Maxtor SATA 1To");
			ps3.setPrixHtva(120);
			ps3.setReference("HDM125636");
			ps3.setStock(10);
			ps3.setVignette("maxtor1T.png");
			ps3.setCategorie(c2);// fait partie de la catégorie c2
			session.persist(ps3);
			
			Produit ps4 = new ProduitS();
			ps4.setLibelle("carte-mère asus 125");
			ps4.setDescription("carte mère Asus pour i7");
			ps4.setPrixHtva(250);
			ps4.setReference("CMAsus1258");
			ps4.setStock(10);
			ps4.setVignette("asus1258.png");
			ps4.setCategorie(c3);// fait partie de la catégorie c2
			session.persist(ps4);
			
			//*********************************
			// créer un produit composite
			
			Produit pc1 = new ProduitC();
			pc1.setLibelle("PC Desktop One");
			pc1.setReference("PCD125");
			pc1.setDescription("tour basique à modifier");
			pc1.setPrixHtva(0);// à réactualiser quand ajoute composite
			pc1.setStock(2);
			pc1.setVignette("pcd125.png");
			pc1.setCategorie(c5);
			session.persist(pc1);
			
			// les composites de pc1
			// 1 carte mère
			Composite cp1 = new Composite();
			cp1.setQuantite(1);
			cp1.setProduit(ps4);
			session.persist(cp1);
			ps4.decrementerStock(2);
			// 1 disque dur
			Composite cp2 = new Composite();
			cp2.setQuantite(1);
			cp2.setProduit(ps3);
			session.persist(cp2);
			ps3.decrementerStock(2);
			// 1 carte graphique
			Composite cp3 = new Composite();
			cp3.setQuantite(1);
			cp3.setProduit(ps2);
			session.persist(cp3);
			ps2.decrementerStock(2);
			
			
			((ProduitC)pc1).getComposites().add(cp1);
			((ProduitC)pc1).getComposites().add(cp2);
			((ProduitC)pc1).getComposites().add(cp3);
			
			// ajouter les composites au produit pc1
			/*((ProduitC)pc1).addComposite(cp1);
			((ProduitC)pc1).addComposite(cp2);
			((ProduitC)pc1).addComposite(cp3);
			*/
     		pc1.setPrixHtva(pc1.retourPrix());
			// pc1 va être automatiquement updaté
					
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
