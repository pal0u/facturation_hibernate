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

public class CategorieDaoOld extends Dao<Categorie,Integer> {

	public CategorieDaoOld(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Categorie obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.getIdCategorie().intValue() == 0 ){
			String sql = "INSERT INTO categorie VALUES(NULL,?);";
			PreparedStatement pst = null;
			ResultSet rsKey = null;
			try {
				pst = this.conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				pst.setString(1, obj.getNomCategorie());
				pst.executeUpdate();
				rsKey = pst.getGeneratedKeys();
				rsKey.next();
				obj.setIdCategorie(rsKey.getInt(1));
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				if ( pst != null ){
					try {
						pst.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				throw new MyException(e.getMessage());
			}
			finally{
				if ( pst != null ){
					try {
						pst.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		else {
			throw new MyException("l'objet Categorie a déjà été sauvegardé");
		}
		
	}
	
	@Override
	public void insert(Categorie[] obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.length > 0 ){
			String sql = "INSERT INTO categorie VALUES(NULL,?);";
			PreparedStatement pst = null;
			ResultSet rsKey = null;
			try {
				conn.setAutoCommit(false);
				pst = this.conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
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
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if ( pst != null ){
					try {
						pst.close();
					} 
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				throw new MyException(e.getMessage());
			}
			finally{
				try {
					conn.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if ( pst != null ){
					try {
						pst.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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
	}

	//******************************************************
	// valable pour java 1.7 et sup
	public void insert2(Categorie obj) throws MyException {
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
	public void update(Categorie obj) throws MyException {
		// TODO Auto-generated method stub
		if ( obj.getIdCategorie().intValue() != 0 ){
			String sql = "UPDATE categorie SET nomCategorie = ? WHERE idCategorie = ?;";
			PreparedStatement pst = null;
			try {
				pst = this.conn.prepareStatement(sql);
				pst.setString(1, obj.getNomCategorie());
				pst.setInt(2,obj.getIdCategorie());
				pst.executeUpdate();				
			}
			catch (SQLException e){
				if ( pst != null ){
					try {
						pst.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				throw new MyException("l'objet Categorie n'a pas été modifié");
			}
			finally {
				if ( pst != null ){
					try {
						pst.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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
			PreparedStatement pst = null;
			try {
				pst = this.conn.prepareStatement(sql);
				pst.setInt(1,obj.getIdCategorie());
				pst.executeUpdate();				
			}
			catch (SQLException e){
				if ( pst != null ){
					try {
						pst.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				throw new MyException("l'objet Categorie n'a pas été supprimé");
			}
			finally {
				if ( pst != null ){
					try {
						pst.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}		
		}
		else {
			throw new MyException("l'objet Categorie n'a pas été sauvegardé");
		}
	}

	@Override
	public Categorie find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		if ( primaryKey.intValue() > 0 ){
			String sql = "SELECT * FROM categorie WHERE idCategorie = ?;";
			PreparedStatement pst = null;
			ResultSet rs = null;
			Categorie cat = null;
			try {
				pst = this.conn.prepareStatement(sql);
				pst.setInt(1, primaryKey);
				rs = pst.executeQuery();	
				rs.next();
				cat = new Categorie();
				cat.setIdCategorie(rs.getInt(1));
				cat.setNomCategorie(rs.getString(2));				
			}
			catch (SQLException e){
				if ( pst != null ){
					try {
						pst.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				throw new MyException("l'objet Categorie n'a pas été trouvé");
			}
			finally {
				if ( pst != null ){
					try {
						pst.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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
		Statement st = null;
		ResultSet rs = null;
		List<Categorie> listeCategories = null;
		try {
			st = this.conn.createStatement();
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
			if ( st != null ){
				try {
					st.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			throw new MyException("impossible de récupérer les catégories");
		}
		finally {
			if ( st != null ){
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listeCategories;
	}

	@Override
	public List<Categorie> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		if ( a.intValue() > 0 && b.intValue() > 0 && b.intValue() > a.intValue() ){
		String sql = "SELECT * FROM categorie WHERE idCategorie BETWEEN "+ a +" AND "+ b +";";
		Statement st = null;
		ResultSet rs = null;
		List<Categorie> listeCategories = null;
		try {
			st = this.conn.createStatement();
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
			if ( st != null ){
				try {
					st.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			throw new MyException("impossible de récupérer les catégories");
		}
		finally {
			if ( st != null ){
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listeCategories;
		}
		else {
			throw new MyException("arguments Integer incorrects");
		}
	}
}
