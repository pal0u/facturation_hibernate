package dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import connexion.MyException;
import pojo.Facture;

public class FactureDao extends Dao<Facture, Integer> {

	public FactureDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Facture obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Facture[] obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Collection<Facture> obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Facture obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Facture obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Facture find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Facture> findAll() throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Facture> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

}
