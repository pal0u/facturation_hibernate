package pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name="typeProduit", discriminatorType=DiscriminatorType.STRING) 
@Table(name="produit",catalog="facturationmf") 
@DiscriminatorValue("Produit") // Voir si c'est vraiment nécessaire

public abstract class Produit implements Serializable {
  protected int idProduit;

  protected String libelle;
  protected String reference;
  protected double prixHtva;
  protected String description;
  protected String vignette;
  protected int stock; 
  
  protected Categorie categorie = null;
  
  protected Set<Lot> lots = new HashSet<Lot>(0);
  protected Set<Composite> lstComposites = new HashSet<Composite>(0);
  
  public abstract double retourPrix() ;

@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "idProduit", unique = true, nullable = false)
public int getIdProduit() {
	return idProduit;
}
public void setIdProduit(int idProduit) {
	this.idProduit = idProduit;
}

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "categorie_idCategorie", nullable = false)
public Categorie getCategorie() {
	return categorie;
}
public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}

@Column(name = "libelle", nullable = false, length = 100)
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}

@Column(name = "reference", unique = true, nullable = false, length = 100)
public String getReference() {
	return reference;
}
public void setReference(String reference) {
	this.reference = reference;
}

@Column(name = "prixHtva", nullable = false, precision = 22, scale = 0)
public double getPrixHtva() {
	return prixHtva;
}
public void setPrixHtva(double prixHtva) {
	this.prixHtva = prixHtva;
}

@Column(name = "description", nullable = false, length = 65535)
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

@Column(name = "vignette", nullable = false, length = 100)
public String getVignette() {
	return vignette;
}
public void setVignette(String vignette) {
	this.vignette = vignette;
}

@Column(name = "stock", nullable = false)
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}

@OneToMany(fetch = FetchType.LAZY, mappedBy = "produit")
public Set<Composite> getLstComposites() {
	return lstComposites;
}
public void setLstComposites(Set<Composite> lstComposites) {
	this.lstComposites = lstComposites;
}

@OneToMany(fetch = FetchType.LAZY, mappedBy = "produit")
public Set<Lot> getLots() {
	return lots;
}
public void setLots(Set<Lot> lots) {
	this.lots = lots;
}

// *************************
public void decrementerStock(int i) {
	// TODO Auto-generated method stub
	// vérifier pour éviter stock négatif ...
	this.setStock(this.getStock() - i);
}

public void incrementerStock(int i) {
	// TODO Auto-generated method stub
	this.setStock(this.getStock() + i);
}
}
