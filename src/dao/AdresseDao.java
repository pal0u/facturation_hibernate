package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pojo.Adresse;
import pojo.Categorie;
import pojo.Pays;
import connexion.MyException;

/*
 * CREATE TABLE IF NOT EXISTS `adresse` (
  `idAdresse` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(10) NOT NULL,
  `rue` varchar(100) NOT NULL,
  `ville` varchar(100) NOT NULL,
  `zipCode` varchar(5) NOT NULL,
  `pays_idPays` int(11) NOT NULL,
  PRIMARY KEY (`idAdresse`),
  KEY `FK_o3pshmpp7efsl7igib5pkffr` (`pays_idPays`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;
 */

public class AdresseDao extends Dao<Adresse,Integer> {
	
	private PaysDao paysdao = null;
	
	public AdresseDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
		@Override
		public void insert(Adresse obj) throws MyException {
			// TODO Auto-generated method stub
			if ( obj.getIdAdresse().intValue() == 0 ){
				
				// vérifier si l'objet pays de la classe Adresse est null
				if ( obj.getPays() == null ){
					throw new MyException("l'objet Adresse n'a pas de pays");
				}
				if ( obj.getPays().getIdPays().intValue() == 0 ) {
					// le pays n'a pas encoré été sauvegardé ...
					// créer un objet paysDao et utiliser la méthode insert de la classe dao
						PaysDao paysdao = new PaysDao(this.conn);
						paysdao.insert(obj.getPays());
						paysdao = null;					
				}
				
						String sql = "INSERT INTO adresse VALUES(NULL,?,?,?,?,?);";
						// insérer une adresse
						ResultSet rsKey = null;
						try (PreparedStatement pst = this.conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
							pst.setString(1, obj.getNumero());
							pst.setString(2, obj.getRue());
							pst.setString(3, obj.getVille());
							pst.setString(4, obj.getZipcode());
							pst.setInt(5, obj.getPays().getIdPays());
							pst.executeUpdate();
							rsKey = pst.getGeneratedKeys();
							rsKey.next();
							obj.setIdAdresse(rsKey.getInt(1));
						} 
						catch (SQLException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							throw new MyException("problème avec l'insertion de l'adresse");
						}

			}
			else {
				throw new MyException("l'objet Adresse a déjà été sauvegardé");
			}		
		}
	//******************************************************
	@Override
	public void insert(Adresse[] obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.length > 0 ){
			String sql = "INSERT INTO adresse VALUES(NULL,?,?,?,?,?);";
			ResultSet rsKey = null;
			try (PreparedStatement pst = this.conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
				conn.setAutoCommit(false);				
				for ( int i = 0; i < obj.length; i++ ){
					if ( obj[i].getIdAdresse().intValue() == 0 ){
						pst.setString(1, obj[i].getNumero());
						pst.setString(2, obj[i].getRue());
						pst.setString(3, obj[i].getVille());
						pst.setString(4, obj[i].getZipcode());
						pst.setInt(5, obj[i].getPays().getIdPays());
						pst.executeUpdate();
						rsKey = pst.getGeneratedKeys();
						rsKey.next();
						obj[i].setIdAdresse(rsKey.getInt(1));
					}
					else {
						throw new SQLException("un objet Adresse a déjà été sauvegardé");
					}
				}
				conn.commit();
				conn.setAutoCommit(true);
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				try {
					conn.rollback();
					conn.setAutoCommit(true);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				throw new MyException(e.getMessage());
			}
		}
		else {
			throw new MyException("problème avec le tableau de adresse");
		}		
	}	

	@Override
	public void insert(Collection<Adresse> obj) throws MyException {
		// TODO Auto-generated method stub
		// convertir la Collection en tableau
		Adresse[] tabAdresse = (Adresse[]) obj.toArray(new Adresse[obj.size()]);
		this.insert(tabAdresse);
		// pour convertir un tableau en Collection type List utiliser classe Arrays
	}
	
	
	//******************************************************
	public void insert2(Adresse[] obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.length > 0 ){
				// en mode transaction
				try {
					conn.setAutoCommit(false);					
				// faire une boucle sur le tableau de Pays
				for ( int i = 0; i < obj.length; i++ ){
					if ( obj[i].getIdAdresse().intValue() == 0 ){
						this.insert(obj[i]); // appel de la méthode insert de la classe PaysDao
					}
					else {
						throw new SQLException("un objet Adresse a déjà été sauvegardé");
					}
				}
				conn.commit();
				conn.setAutoCommit(true);
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					try {
						conn.rollback();
						conn.setAutoCommit(true);
					} 
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					throw new MyException(e.getMessage());
				}	
			} 
		else {
			throw new MyException("problème avec le tableau de adresse");
		}		
	}	

	
	//******************************************************

	@Override
	public void update(Adresse obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.getIdAdresse().intValue() != 0 ){
			String sql = "UPDATE adresse SET numero = ?,rue = ?, ville = ?, zipCode = ?, pays_idPays = ?  WHERE idAdresse = ?;";
			try ( PreparedStatement pst = this.conn.prepareStatement(sql) ){
				pst.setString(1, obj.getNumero());
				pst.setString(2, obj.getRue());
				pst.setString(3, obj.getVille());
				pst.setString(4, obj.getZipcode());		
				if ( obj.getPays() == null )
					throw new MyException("l'objet Adresse n'a pas de pays");
				if ( obj.getPays().getIdPays().intValue() == 0 ) {
					PaysDao paysdao = new PaysDao(this.conn);
					paysdao.insert(obj.getPays());
					paysdao = null;					
				}				
				pst.setInt(5,obj.getPays().getIdPays().intValue());
				System.out.println(obj);
				pst.setInt(6,obj.getIdAdresse().intValue());
				pst.executeUpdate();				
			}
			catch (SQLException e){
				throw new MyException("l'objet Adresse n'a pas été modifié");
			}
		}
		else {
			throw new MyException("l'objet Adresse n'a pas été sauvegardé");
		}		
	}

	@Override
	public void delete(Adresse obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.getIdAdresse().intValue() != 0 ){
			String sql = "DELETE FROM adresse WHERE idAdresse = ?;";
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,obj.getIdAdresse());
				pst.executeUpdate();				
			}
			catch (SQLException e){
				throw new MyException("l'objet Adresse n'a pas été supprimé");
			}
		}
		else {
			throw new MyException("l'objet Adresse n'a pas été sauvegardé");
		}
	}

	//******************************
	@Override
	public Adresse find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		if ( primaryKey.intValue() > 0 ){
			String sql = "SELECT * FROM Adresse WHERE idAdresse = ?;";
			ResultSet rs = null;
			Adresse adresse = null;
			try (PreparedStatement pst = this.conn.prepareStatement(sql)) {
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();	
				rs.next();
				adresse = new Adresse();
				adresse.setIdAdresse(new Integer(rs.getInt(1)));
				adresse.setNumero(rs.getString(2));
				adresse.setRue(rs.getString(3));
				adresse.setVille(rs.getString(4));
				adresse.setZipcode(rs.getString(5));
				paysdao  = new PaysDao(this.conn);
				Pays pays = paysdao.find(rs.getInt(6));
				paysdao = null;
				adresse.setPays(pays);
			}
			catch (SQLException e){
				throw new MyException("l'objet Adresse n'a pas été trouvé");
			}
			return adresse;
		}
		else {
			throw new MyException("votre Valeur PK n'est pas correcte");
		}
	}

	@Override
	public List<Adresse> findAll() throws MyException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM adresse;";
		ResultSet rs = null;
		List<Adresse> listeAdresse = null;
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);	
			listeAdresse = new ArrayList<Adresse>(0);
			Adresse adresse  = null;
			paysdao  = new PaysDao(this.conn);
			Pays pays = null;
			while (rs.next()){
				adresse = new Adresse();
				adresse.setIdAdresse(new Integer(rs.getInt(1)));
				adresse.setNumero(rs.getString(2));
				adresse.setRue(rs.getString(3));
				adresse.setVille(rs.getString(4));
				adresse.setZipcode(rs.getString(5));
				paysdao = new PaysDao(this.conn);
				pays = paysdao.find(rs.getInt(6));
				adresse.setPays(pays);
				paysdao = null;
				listeAdresse.add(adresse);
			}
		}
		catch (SQLException e){
			throw new MyException("impossible de récupérer les adresses");
		}
		return listeAdresse;
	}

	@Override
	public List<Adresse> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		if ( a.intValue() > 0 && b.intValue() > 0 && b.intValue() > a.intValue() ){
		String sql = "SELECT * FROM adresse WHERE idAdresse BETWEEN "+ a +" AND "+ b +";";
		ResultSet rs = null;
		List<Adresse> listeAdresse = null;
		try (Statement st = this.conn.createStatement()) {
			rs = st.executeQuery(sql);	
			listeAdresse = new ArrayList<Adresse>(0);
			Adresse adresse  = null;
			paysdao  = new PaysDao(this.conn);
			Pays pays = null;
			while (rs.next()){
				adresse = new Adresse();
				adresse.setIdAdresse(new Integer(rs.getInt(1)));
				adresse.setNumero(rs.getString(2));
				adresse.setRue(rs.getString(3));
				adresse.setVille(rs.getString(4));
				adresse.setZipcode(rs.getString(5));
				// on récupère l'objet pays de l'adresse
				pays = paysdao.find(rs.getInt(6));
				adresse.setPays(pays);
				paysdao = null;
				listeAdresse.add(adresse);
			}
			
		}
		catch (SQLException e){
			throw new MyException("impossible de récupérer les adresses");
		}
		return listeAdresse;
		}
		else {
			throw new MyException("arguments Integer incorrects");
		}
	}
	
	// on récupère un ResultSet que l'on peut parcourir et que l'on peut modifier
	public ResultSet findResult() throws MyException {
		// TODO Auto-generated method stub
			String sql = "SELECT * FROM adresse;";
			ResultSet rs = null;
			// par défaut, ResultSet.TYPE_FORWARD_ONLY  && 	ResultSet.CONCUR_READ_ONLY
			try {
			Statement st = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);	
			}
			catch (SQLException e){
				throw new MyException("impossible de récupérer les adresses");
			}
			return rs;
		}
}
