package pojo;
// Faire attention aux importations !!!
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "identification", catalog = "facturationmf", uniqueConstraints = 
@UniqueConstraint(columnNames =  {"login", "password"}))

public class Identification implements Serializable {
	private Integer id;
  private String login;
  private String password;
  private Client client = null;
  
  public Identification(){	  
  }

  // Hibernate doit recopier l'id du client 
  // dans id de cette classe
  @GenericGenerator(name = "generator", strategy = "foreign", parameters = 
		  @Parameter(name = "property", value = "client")) 
  @Id
  @GeneratedValue(generator = "generator")
  @Column(name = "id", unique = true, nullable = false)
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

@Column (name = "login", nullable = false, length = 10)
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}

// Ca dependra du cryptage utilisé (Pour SHA1, length  = 44, MD5 length = 32)
@Column (name = "password", nullable = false, length = 44)
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

// Pour recopier la valeur de client vers identification
@OneToOne(fetch = FetchType.LAZY)
@PrimaryKeyJoinColumn
public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}

@Override
public String toString() {
	return "Identification [id=" + id + ", login=" + login + ", password="
			+ password + ", client=" + client + "]";
}
}
