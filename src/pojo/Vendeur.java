package pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vendeur", catalog = "facturationmf")
@AttributeOverrides({ 
	@AttributeOverride(name="nom", column=@Column(name="nom")),
	@AttributeOverride(name="prenom", column=@Column(name="prenom")), 
	@AttributeOverride(name="email", column=@Column(name="email")), 
	@AttributeOverride(name="telephone", column=@Column(name="telephone")) })
public class Vendeur extends Personne implements Serializable {
	
	  private float commission;
	  private String typeVendeur; 
	  
	private Vendeur chef = null;	
	private Set<Facture> factures = new HashSet<Facture>(0);
	
	public Vendeur(){		
	}
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idChef")
	public Vendeur getChef() {
		return chef;
	}
	public void setChef(Vendeur chef) {
		this.chef = chef;
	}
	
	// récupérer la liste des factures encodées par le vendeur
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendeur")
	public Set<Facture> getFactures() {
		return factures;
	}
	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}	
	

	public float getCommission() {
		return commission;
	}
	public void setCommission(float commission) {
		this.commission = commission;
	}

	public String getTypeVendeur() {
		return typeVendeur;
	}
	public void setTypeVendeur(String typeVendeur) {
		this.typeVendeur = typeVendeur;
	}

	@Override
	public String toString() {
		return "Vendeur [id=" + id + ", nom=" + nom + ", prenom=" + prenom
				+ ", email=" + email + ", telephone=" + telephone + "]";
	}
}
