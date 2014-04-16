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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "adresse", catalog = "facturationmf")
public class Adresse implements Serializable{
	
  private Integer idAdresse = 0;
  private String rue = null;
  private String numero = null;
  private String zipcode = null;
  private String ville = null;
  
  private Pays pays = null;
  
  private Set<Client> clients = new HashSet<Client>(0);
  private Set<Facture> factures = new HashSet<Facture>(0);

  public Adresse(){
  }  
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idAdresse", unique = true, nullable = false)
	public Integer getIdAdresse() {
		return idAdresse;
	}	
	public void setIdAdresse(Integer idAdresse) {
		this.idAdresse = idAdresse;
	}
	
	@Column(name = "rue", nullable = false, length = 100)
	public String getRue() {
		return rue;
	}
		public void setRue(String rue) {
		this.rue = rue;
	}
	
	@Column(name = "numero", nullable = false, length = 10)
	public String getNumero() {
		return numero;
	}	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@Column(name = "zipCode", nullable = false, length = 5)
	public String getZipcode() {
		return zipcode;
	}	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Column(name = "ville", nullable = false, length = 100)
	public String getVille() {
		return ville;
	}	
	public void setVille(String ville) {
		this.ville = ville;
	}
	 
	// Plusieurs adresses peuvent avoir le même pays
	@ManyToOne(fetch = FetchType.LAZY) // On peut remplacer "LAZY" par "EAGER"
	// Ca va correspondre à la FK pour relier à la table "pays"
	@JoinColumn(name = "pays_idPays", nullable = false)
	public Pays getPays() {
		return pays;
	}	
	public void setPays(Pays pays) {
		this.pays = pays;
	}

	// récupérer la liste des factures utilisant cette adresse comme adressedelivraison
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "adresseLivraison")
	public Set<Facture> getFactures() {
		return factures;
	}
	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}
	
	// récupérer la liste des clients utilisant cette adresse comme adressedefacturation
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "adresse")
	public Set<Client> getClients() {
		return clients;
	}
	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "Adresse [idAdresse=" + idAdresse + ", pays=" + pays + ", rue="
				+ rue + ", numero=" + numero + ", zipcode=" + zipcode
				+ ", ville=" + ville + "]";
	} 	
}
