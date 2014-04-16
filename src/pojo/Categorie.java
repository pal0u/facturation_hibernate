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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorie", catalog = "facturationmf")
public class Categorie implements Serializable{
  private Integer idCategorie;
  private String nomCategorie;
  private Set<Produit> produits = new HashSet<Produit>(0);

	public Categorie(){		  
	}

  	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idCategorie", unique = true, nullable = false)
	public Integer getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}
	
	@Column(name = "nomCategorie", nullable = false, length = 100)
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie="
				+ nomCategorie + "]";
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	public Set<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}
}
