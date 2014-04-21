package dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import pojo.Vendeur;
import connexion.MyException;

public class VendeurDao extends Dao<Vendeur, Integer> {

	public VendeurDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Vendeur obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Vendeur[] obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Collection<Vendeur> obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendeur obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Vendeur obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendeur find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vendeur> findAll() throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vendeur> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

}
