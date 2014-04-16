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
import connexion.MyException;

/*
 * CREATE TABLE IF NOT EXISTS `categorie` (
  `idCategorie` int(11) NOT NULL AUTO_INCREMENT,
  `nomCategorie` varchar(100) NOT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;
 */

public class CategorieDao extends Dao<Categorie,Integer> {

	public CategorieDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	//******************************************************
		// valable pour java 1.7 et sup
		@Override
		public void insert(Categorie obj) throws MyException {
			// TODO Auto-generated method stub
			if ( obj.getIdCategorie().intValue() == 0 ){
				String sql = "INSERT INTO categorie VALUES(NULL,?);";
				ResultSet rsKey = null;
				// try avec ressources à partir de 1.7
				// Classes auto-closeable
				try (PreparedStatement pst = this.conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
					pst.setString(1, obj.getNomCategorie());
					pst.executeUpdate();
					rsKey = pst.getGeneratedKeys();
					rsKey.next();
					obj.setIdCategorie(rsKey.getInt(1));
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new MyException(e.getMessage());
				}
			}
			else {
				throw new MyException("l'objet Categorie a déjà été sauvegardé");
			}		
		}
	//******************************************************
	@Override
	public void insert(Categorie[] obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.length > 0 ){
			String sql = "INSERT INTO categorie VALUES(NULL,?);";
			ResultSet rsKey = null;
			try (PreparedStatement pst = this.conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
				conn.setAutoCommit(false);				
				for ( int i = 0; i < obj.length; i++ ){
					if ( obj[i].getIdCategorie().intValue() == 0 ){
						pst.setString(1, obj[i].getNomCategorie());
						pst.executeUpdate();
						rsKey = pst.getGeneratedKeys();
						rsKey.next();
						obj[i].setIdCategorie(rsKey.getInt(1));
					}
					else {
						throw new SQLException("un objet Categorie a déjà été sauvegardé");
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
			throw new MyException("problème avec le tableau de catégories");
		}		
	}	

	@Override
	public void insert(Collection<Categorie> obj) throws MyException {
		// TODO Auto-generated method stub
		// convertir la Collection en tableau
		Categorie[] tabCategories = (Categorie[]) obj.toArray(new Categorie[obj.size()]);
		this.insert(tabCategories);
		// pour convertir un tableau en Collection type List utiliser classe Arrays
	}

	
	//******************************************************

	@Override
	public void update(Categorie obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.getIdCategorie().intValue() != 0 ){
			String sql = "UPDATE categorie SET nomCategorie = ? WHERE idCategorie = ?;";
			try ( PreparedStatement pst = this.conn.prepareStatement(sql) ){
				pst.setString(1, obj.getNomCategorie());
				pst.setInt(2,obj.getIdCategorie());
				pst.executeUpdate();				
			}
			catch (SQLException e){
				throw new MyException("l'objet Categorie n'a pas été modifié");
			}
		}
		else {
			throw new MyException("l'objet Categorie n'a pas été sauvegardé");
		}		
	}

	@Override
	public void delete(Categorie obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.getIdCategorie().intValue() != 0 ){
			String sql = "DELETE FROM categorie WHERE idCategorie = ?;";
			try (PreparedStatement pst = this.conn.prepareStatement(sql)){
				pst.setInt(1,obj.getIdCategorie());
				pst.executeUpdate();				
			}
			catch (SQLException e){
				throw new MyException("l'objet Categorie n'a pas été supprimé");
			}
		}
		else {
			throw new MyException("l'objet Categorie n'a pas été sauvegardé");
		}
	}

	//******************************
	@Override
	public Categorie find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		if ( primaryKey.intValue() > 0 ){
			String sql = "SELECT * FROM categorie WHERE idCategorie = ?;";
			ResultSet rs = null;
			Categorie cat = null;
			try (PreparedStatement pst = this.conn.prepareStatement(sql)) {
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();	
				rs.next();
				cat = new Categorie();
				cat.setIdCategorie(rs.getInt(1));
				cat.setNomCategorie(rs.getString(2));				
			}
			catch (SQLException e){
				throw new MyException("l'objet Categorie n'a pas été trouvé");
			}
			return cat;
		}
		else {
			throw new MyException("votre Valeur PK n'est pas correcte");
		}
	}

	@Override
	public List<Categorie> findAll() throws MyException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM categorie;";
		ResultSet rs = null;
		List<Categorie> listeCategories = null;
		try (Statement st = this.conn.createStatement()){
			rs = st.executeQuery(sql);	
			listeCategories = new ArrayList<Categorie>(0);
			Categorie cat = null;
			while (rs.next()){
				cat = new Categorie();
				cat.setIdCategorie(rs.getInt(1));
				cat.setNomCategorie(rs.getString(2));
				listeCategories.add(cat);
			}
		}
		catch (SQLException e){
			throw new MyException("impossible de récupérer les catégories");
		}
		return listeCategories;
	}

	@Override
	public List<Categorie> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		if ( a.intValue() > 0 && b.intValue() > 0 && b.intValue() > a.intValue() ){
		String sql = "SELECT * FROM categorie WHERE idCategorie BETWEEN "+ a +" AND "+ b +";";
		ResultSet rs = null;
		List<Categorie> listeCategories = null;
		try (Statement st = this.conn.createStatement()) {
			rs = st.executeQuery(sql);	
			listeCategories = new ArrayList<Categorie>(0);
			Categorie cat = null;
			while (rs.next()){
				cat = new Categorie();
				cat.setIdCategorie(rs.getInt(1));
				cat.setNomCategorie(rs.getString(2));
				listeCategories.add(cat);
			}
		}
		catch (SQLException e){
			throw new MyException("impossible de récupérer les catégories");
		}
		return listeCategories;
		}
		else {
			throw new MyException("arguments Integer incorrects");
		}
	}
	
	// on récupère un ResultSet que l'on peut parcourir et que l'on peut modifier
	public ResultSet findResult() throws MyException {
		// TODO Auto-generated method stub
			String sql = "SELECT * FROM categorie;";
			ResultSet rs = null;
			// par défaut, ResultSet.TYPE_FORWARD_ONLY  && 	ResultSet.CONCUR_READ_ONLY
			try {
			Statement st = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);	
			}
			catch (SQLException e){
				throw new MyException("impossible de récupérer les catégories");
			}
			return rs;
		}
}
