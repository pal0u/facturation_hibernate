package dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import connexion.MyException;
import pojo.ProduitC;

public class ProduitCDao extends Dao<ProduitC, Integer> {

	public ProduitCDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(ProduitC obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(ProduitC[] obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Collection<ProduitC> obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProduitC obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProduitC obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProduitC find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProduitC> findAll() throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProduitC> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

}
