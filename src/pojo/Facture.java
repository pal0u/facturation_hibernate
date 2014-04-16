package pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "facture", catalog = "facturationmf")
public class Facture implements Serializable {
  private Integer idFacture;
  private String nrFacture;
  private Date dateFacturation;
  private char modePaiement;
  private Integer payer;
  private char typeAchat;
  private double tauxTva;
  
  private Client client = null;
  private Vendeur vendeur = null;
  private Adresse adresseLivraison = null;
  private Set<Lot> lots = new HashSet<Lot>(0);
  
  public Facture(){	  
  }
 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idFacture", unique = true, nullable = false)
public Integer getIdFacture() {
	return idFacture;
}
public void setIdFacture(Integer idFacture) {
	this.idFacture = idFacture;
}

@Column(name = "nrFacture", unique = true, nullable = false, length = 20)
public String getNrFacture() {
	return nrFacture;
}
public void setNrFacture(String nrFacture) {
	this.nrFacture = nrFacture;
}

@Temporal(TemporalType.DATE) 
@Column(name = "dateFacture", nullable = false)
public Date getDateFacturation() {
	return dateFacturation;
}
public void setDateFacturation(Date dateFacturation) {
	this.dateFacturation = dateFacturation;
}

@Column(name = "modePaiement", nullable = false, length = 1)
public char getModePaiement() {
	return modePaiement;
}
public void setModePaiement(char modePaiement) {
	this.modePaiement = modePaiement;
}

@Column(name = "payer", nullable = false)
public Integer getPayer() {
	return payer;
}
public void setPayer(Integer payer) {
	this.payer = payer;
}

@Column(name = "typeAchat", nullable = false, length = 1)
public char getTypeAchat() {
	return typeAchat;
}
public void setTypeAchat(char typeAchat) {
	this.typeAchat = typeAchat;
}

@Column(name = "tauxTva", nullable = false, precision = 22, scale = 0)
public double getTauxTva() {
	return tauxTva;
}
public void setTauxTva(double tauxTva) {
	this.tauxTva = tauxTva;
}


// FK peut être null
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "adresse_idAdresse")
public Adresse getAdresseLivraison() {
	return adresseLivraison;
}
public void setAdresseLivraison(Adresse adresse) {
	this.adresseLivraison = adresse;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "client_idClient", nullable = false)
public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name="detailLot", catalog="facturationmf",  joinColumns = {  
		  @JoinColumn(name="facture_idFacture", nullable=false, updatable=false) }, inverseJoinColumns = {  
		  @JoinColumn(name="lot_idLot", nullable=false, updatable=false) })
public Set<Lot> getLots() {
	return lots;
}
public void setLots(Set<Lot> lots) {
	this.lots = lots;
}


//***************************************
// méthodes pour la Set<Lot>
public void addLot(Lot lot){
	this.lots.add(lot);	
}
public void removeLot(Lot lot){
	this.lots.remove(lot);
}
public void removeAllLots(){
	this.lots.clear();
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "vendeur_idVendeur")
public Vendeur getVendeur() {
	return vendeur;
}
public void setVendeur(Vendeur vendeur) {
	this.vendeur = vendeur;
}

// permet de calculer le montant Tvac de la facture
public double montantTotalTvac() {
	double montant = 0.0;	
		for ( Lot l : this.lots ){
			montant += l.getQuantite() * l.getProduit().retourPrix();
		}	
	  return montant * (1 + this.tauxTva);
  }
}
