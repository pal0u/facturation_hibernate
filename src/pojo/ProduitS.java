package pojo;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "produit", catalog = "facturationmf")
@DiscriminatorValue("ProduitS")

public class ProduitS extends Produit implements Serializable {
	public ProduitS(){		
	}
	
  public double retourPrix() {
	  return prixHtva;
  }

}
