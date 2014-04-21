package dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import connexion.MyException;
import pojo.ProduitS;

public class ProduitSDao extends Dao<ProduitS, Integer> {

	public ProduitSDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(ProduitS obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(ProduitS[] obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Collection<ProduitS> obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ProduitS obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProduitS obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProduitS find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProduitS> findAll() throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProduitS> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

}
