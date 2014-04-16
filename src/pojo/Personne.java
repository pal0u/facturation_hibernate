package pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Table(name = "personne", catalog = "facturationmf")
abstract class Personne implements Serializable {
  protected Integer id;
  protected String nom;
  protected String prenom;
  protected String email;
  protected String telephone;

  public Personne(){	  
  }

@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "CUSTOMER_ID")
@TableGenerator(name = "CUSTOMER_ID",  table = "counters", pkColumnName = "CounterName",  valueColumnName = "CounterValue",  pkColumnValue = "CUSTOMER_NO", initialValue = 1, allocationSize = 1) 
@Column(name = "id", nullable = false)
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

@Column(name = "nom", nullable = false, length = 100)
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}

@Column(name = "prenom", nullable = false, length = 100)
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}

@Column(name = "email", nullable = false, length = 100)
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

@Column(name = "telephone", nullable = false, length = 20)
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}  
  
}
