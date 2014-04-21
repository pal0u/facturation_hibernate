package dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import connexion.MyException;
import pojo.Composite;

public class CompositeDao extends Dao<Composite, Integer> {

	public CompositeDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Composite obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Composite[] obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Collection<Composite> obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Composite obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Composite obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Composite find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Composite> findAll() throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Composite> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

}
