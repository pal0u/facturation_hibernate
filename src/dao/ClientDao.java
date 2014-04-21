package dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import connexion.MyException;
import pojo.Client;

public class ClientDao extends Dao<Client, Integer> {

	public ClientDao(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Client obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Client[] obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Collection<Client> obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Client obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Client obj) throws MyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client find(Integer primaryKey) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findAll() throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> findBetween(Integer a, Integer b) throws MyException {
		// TODO Auto-generated method stub
		return null;
	}

}
