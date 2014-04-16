package pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lot", catalog = "facturationmf")
public class Lot implements Serializable {
  private Integer idLot;
  private int quantite;
  // Le produit peut etre un produit simple ou un produit composite
  private Produit produit = null;
  private Set<Facture> factures = new HashSet<Facture>(0);
  
  public Lot(){	  
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idLot", unique = true, nullable = false)
public Integer getIdLot() {
	return idLot;
}
public void setIdLot(Integer idLot) {
	this.idLot = idLot;
}

@Column(name = "quantite", nullable = false)
public int getQuantite() {
	return quantite;
}
public void setQuantite(int quantite) {
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
@JoinTable(name="detailLot", catalog="facturationmf",  joinColumns = {  
		  @JoinColumn(name="lot_idLot", nullable=false, updatable=false) }, inverseJoinColumns = {  
		  @JoinColumn(name="facture_idFacture", nullable=false, updatable=false) })
public Set<Facture> getFactures() {
	return factures;
}
public void setFactures(Set<Facture> factures) {
	this.factures = factures;
}
}
