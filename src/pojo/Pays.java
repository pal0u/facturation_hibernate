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
@Table(name = "pays", catalog = "facturationmf")
public class Pays implements Serializable {
  private Integer idPays = 0;
  private String nomPays = null;
  private Set<Adresse> adresses = new HashSet<Adresse>(0);
  
  public Pays(){
  }
  	@Id
  	@GeneratedValue(strategy = IDENTITY)
  	@Column(name = "idPays", nullable = false, unique = true)
	public Integer getIdPays() {
		return idPays;
	}	
	public void setIdPays(Integer idPays) {
		this.idPays = idPays;
	}
	
	@Column(name = "nomPays", nullable = false, length = 100)
	public String getNomPays() {
		return nomPays;
	}	
	public void setNomPays(String nomPays) {
		this.nomPays = nomPays;
	}
	
	// Un pays peut avoir plusieurs adresses
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "pays")
	public Set<Adresse> getAdresses() {
		return adresses;
	}
	public void setAdresses(Set<Adresse> adresses) {
		this.adresses = adresses;
	}

	@Override
	public String toString() {
		return "Pays [idPays=" + idPays + ", nomPays=" + nomPays + "]";
	}
  
}
