package pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "composite", catalog = "facturationmf")
public class Composite implements Serializable {
	private Integer idComposite;
	private Integer quantite;
	private Produit produit;
    private Set<Produit> produits = new HashSet<Produit>(0);

	public Composite(){		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idComposite", unique = true, nullable = false)
	public Integer getIdComposite() {
		return idComposite;
	}
	public void setIdComposite(Integer idComposite) {
		this.idComposite = idComposite;
	}

	@Column(name = "quantite", nullable = false)
	public Integer getQuantite() {
		return quantite;
	}
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produit_idProduit", nullable = false)
	public Produit getProduit() {
		return produit;
	}	
	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="detailComposite", catalog="facturationmf",  joinColumns = {  
			  @JoinColumn(name="produit_idProduit", nullable=false, updatable=false) }, inverseJoinColumns = {  
			  @JoinColumn(name="composite_idComposite", nullable=false, updatable=false) }) 
	public Set<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

}
