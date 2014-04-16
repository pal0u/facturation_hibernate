package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pojo.Categorie;
import pojo.Pays;
import connexion.MyException;

// structure de la table
/*
 * CREATE TABLE IF NOT EXISTS `pays` (
  `idPays` int(11) NOT NULL AUTO_INCREMENT,
  `nomPays` varchar(100) NOT NULL,
  PRIMARY KEY (`idPays`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;
 */

public class PaysDao extends Dao<Pays,Integer> {

	public PaysDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
		@Override
		public void insert(Pays obj) throws MyException {
			// TODO Auto-generated method stub
			if ( obj.getIdPays().intValue() == 0 ){
				String sql = "INSERT INTO pays VALUES(NULL,?);";
				ResultSet rsKey = null;
				try (PreparedStatement pst = this.conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
					pst.setString(1, obj.getNomPays());
					pst.executeUpdate();
					rsKey = pst.getGeneratedKeys();
					rsKey.next();
					obj.setIdPays(rsKey.getInt(1));
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new MyException("problème avec l'insertion du pays");
				}
			}
			else {
				throw new MyException("l'objet Pays a déjà été sauvegardé");
			}		
		}
	//******************************************************
	@Override
	public void insert(Pays[] obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.length > 0 ){
			String sql = "INSERT INTO pays VALUES(NULL,?);";
			ResultSet rsKey = null;
			try (PreparedStatement pst = this.conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
				// en mode transaction
				conn.setAutoCommit(false);	
				// faire une boucle sur le tableau de Pays
				for ( int i = 0; i < obj.length; i++ ){
					if ( obj[i].getIdPays().intValue() == 0 ){
						pst.setString(1, obj[i].getNomPays());
						pst.executeUpdate();
						rsKey = pst.getGeneratedKeys();
						rsKey.next();
						obj[i].setIdPays(rsKey.getInt(1));
					}
					else {
						throw new SQLException("un objet Pays a déjà été sauvegardé");
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
			throw new MyException("problème avec le tableau de pays");
		}		
	}	

	@Override
	public void insert(Collection<Pays> obj) throws MyException {
		// TODO Auto-generated method stub
		// convertir la Collection en tableau
		Pays[] tabPays = (Pays[]) obj.toArray(new Pays[obj.size()]);
		this.insert(tabPays);
		// pour convertir un tableau en Collection type List utiliser classe Arrays
	}
	
	
	//******************************************************

		public void insert2(Pays[] obj) throws MyException {
			// TODO Auto-generated method stub
			if ( obj.length > 0 ){
					// en mode transaction
					try {
						conn.setAutoCommit(false);					
					// faire une boucle sur le tableau de Pays
					for ( int i = 0; i < obj.length; i++ ){
						if ( obj[i].getIdPays().intValue() == 0 ){
							this.insert(obj[i]); // appel de la méthode insert de la classe PaysDao
						}
						else {
							throw new SQLException("un objet Pays a déjà été sauvegardé");
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
				throw new MyException("problème avec le tableau de pays");
			}		
		}	

	
	//******************************************************

	@Override
	public void update(Pays obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.getIdPays().intValue() != 0 ){
			String sql = "UPDATE pays SET nomPays = ? WHERE idPays = ?;";
			// changer le nom du pays
			try ( PreparedStatement pst = this.conn.prepareStatement(sql) ){
				pst.setString(1, obj.getNomPays());
				pst.setInt(2,obj.getIdPays());
				pst.executeUpdate();				
			}
			catch (SQLException e){
				throw new MyException("l'objet Pays n'a pas été modifié");
			}
		}
		else {
			throw new MyException("l'objet Pays n'a pas été sauvegardé");
		}		
	}

	@Override
	public void delete(Pays obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.getIdPays().intValue() != 0 ){
			String sql = "DELETE FROM pays WHERE idPays = ?;";
			// supprimer l'enregistrement correspondant au pays
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,obj.getIdPays());
				pst.executeUpdate();				
			}
			catch (SQLException e){
				throw new MyException("l'objet Pays n'a pas été supprimé");
			}
		}
		else {
			throw new MyException("l'objet Pays n'a pas été sauvegardé");
		}
	}

	//******************************
	@Override
	public Pays find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		if ( primaryKey.intValue() > 0 ){
			String sql = "SELECT * FROM pays WHERE idPays = ?;";
			// rechercher un pays
			ResultSet rs = null;
			Pays pays = null;
			try (PreparedStatement pst = this.conn.prepareStatement(sql)) {
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();	
				rs.next();
				pays = new Pays();
				pays.setIdPays(new Integer(rs.getInt(1)));
				pays.setNomPays(rs.getString(2));				
			}
			catch (SQLException e){
				throw new MyException("l'objet Pays n'a pas été trouvé");
			}
			return pays;
		}
		else {
			throw new MyException("votre Valeur PK n'est pas correcte");
		}
	}

	@Override
	public List<Pays> findAll() throws MyException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM pays;";
		// récupérer tous les pays
		ResultSet rs = null;
		List<Pays> listePays = null;
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);	
			listePays = new ArrayList<Pays>(0);
			Pays pays = null;
			while (rs.next()){
				pays = new Pays();
				pays.setIdPays(new Integer(rs.getInt(1)));
				pays.setNomPays(rs.getString(2));
				listePays.add(pays);
			}
		}
		catch (SQLException e){
			throw new MyException("impossible de récupérer les catégories");
		}
		return listePays;
	}

	@Override
	public List<Pays> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		if ( a.intValue() > 0 && b.intValue() > 0 && b.intValue() > a.intValue() ){
		String sql = "SELECT * FROM pays WHERE idPays BETWEEN "+ a +" AND "+ b +";";
		// récupérer un rang de pays
		ResultSet rs = null;
		List<Pays> listePays = null;
		try (Statement st = this.conn.createStatement()) {
			rs = st.executeQuery(sql);	
			listePays = new ArrayList<Pays>(0);
			Pays pays = null;
			while (rs.next()){
				pays = new Pays();
				pays.setIdPays(new Integer(rs.getInt(1)));
				pays.setNomPays(rs.getString(2));
				listePays.add(pays);
			}
		}
		catch (SQLException e){
			throw new MyException("impossible de récupérer les pays");
		}
		return listePays;
		}
		else {
			throw new MyException("arguments Integer incorrects");
		}
	}
	
	// on récupère un ResultSet que l'on peut parcourir et que l'on peut modifier
	// voir Constantes sur ResultSet ...
	public ResultSet findResult() throws MyException {
		// TODO Auto-generated method stub
			String sql = "SELECT * FROM pays;";
			ResultSet rs = null;
			// par défaut, ResultSet.TYPE_FORWARD_ONLY  && 	ResultSet.CONCUR_READ_ONLY
			try {
			Statement st = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);	
			}
			catch (SQLException e){
				throw new MyException("impossible de récupérer les pays");
			}
			return rs;
		}
}
