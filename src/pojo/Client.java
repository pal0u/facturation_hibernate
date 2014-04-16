package pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "client", catalog = "facturationmf")
@AttributeOverrides({ 
	@AttributeOverride(name="nom", column=@Column(name="nom")),
	@AttributeOverride(name="prenom", column=@Column(name="prenom")), 
	@AttributeOverride(name="email", column=@Column(name="email")), 
	@AttributeOverride(name="telephone", column=@Column(name="telephone")) })

public class Client extends Personne implements Serializable {

  private Date dateNaissance = null;
  private char sexe;
  private double remise = 0.0;
  private String societe = null;
  private String nrTva = null;

  private Adresse adresse = null;
  private Set<Facture> factures = new HashSet<Facture>(0);
  private Identification identification = null;
  
	public Client() {
		super();
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adresse_idAdresse", nullable = false)
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateNaissance", nullable = false)
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	@Column(name = "sexe", nullable = false, length = 1)
	public char getSexe() {
		return sexe;
	}
	public void setSexe(char sexe) {
		this.sexe = sexe;
	}

	@Column(name = "remise", nullable = false, precision = 22, scale = 0)
	public double getRemise() {
		return remise;
	}
	public void setRemise(double remise) {
		this.remise = remise;
	}

	@Column(name = "societe", nullable = false, length = 100)
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}

	@Column(name = "nrTva", nullable = false, length = 20)
	public String getNrTva() {
		return nrTva;
	}
	public void setNrTva(String nrTva) {
		this.nrTva = nrTva;
	}

	// cascade signifie que si je supprime un client de ma BD, 
	// il va automatiquement effacer l'enregistrement 
	// correspondant dans identification
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "client", 
			cascade = CascadeType.ALL)
	public Identification getIdentification() {
		return identification;
	}
	public void setIdentification(Identification identification) {
		this.identification = identification;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")	
	public Set<Facture> getFactures() {
		return factures;
	}
	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}

	@Override
	public String toString() {
		return "Client [adresse=" + adresse + ", dateNaissance="
				+ dateNaissance + ", sexe=" + sexe + ", remise=" + remise
				+ ", societe=" + societe + ", nrTva=" + nrTva + ", id=" + id
				+ ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", telephone=" + telephone + "]";
	} 
  
}
