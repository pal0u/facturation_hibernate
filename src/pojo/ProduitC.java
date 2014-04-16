package pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produit", catalog = "facturationmf")
@DiscriminatorValue("ProduitC")
public class ProduitC extends Produit implements Serializable {
	public ProduitC(){		
	}	
	
  private Set<Composite> composites = new HashSet<Composite>(0);

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name="detailComposite", catalog="facturationmf",  joinColumns = {  
		  @JoinColumn(name="produit_idProduit", nullable=false, updatable=false) }, inverseJoinColumns = {  
		  @JoinColumn(name="composite_idComposite", nullable=false, updatable=false) }) 
	public Set<Composite> getComposites() {
		return composites;
	}	
	public void setComposites(Set<Composite> composites) {
		this.composites = composites;
	}

	//***************************************
	// méthodes pour la Set<Composite>
	public void addComposite(Composite composite){
		this.composites.add(composite);	
	}
	public void removeComposite(Composite composite){
		this.composites.remove(composite);
	}
	public void removeAllComposites(){
		this.composites.clear();
	}
	

	// calcul du prix de revient du produit composite ...
public double retourPrix() {
	double prixProduitC = 0.0;	
	for ( Composite c : this.composites ){
		prixProduitC += c.getQuantite() * c.getProduit().retourPrix();
	}
	  return prixProduitC;
  }

}
