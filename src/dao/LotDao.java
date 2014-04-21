package dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import connexion.MyException;
import pojo.Lot;

public class LotDao extends Dao<Lot, Integer> {

	public LotDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Lot obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Lot[] obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Collection<Lot> obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Lot obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Lot obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Lot find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lot> findAll() throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lot> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

}
