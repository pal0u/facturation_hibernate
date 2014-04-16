package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import connexion.MyException;

/*
 * classe abstraite permettant de créer des classes Dao pour chaque Entité logique
 */

public abstract class Dao <T,PK>{
	// généricité T pour le type de l'objet à traiter, PK pour le type pour la Primary Key
	
	protected Connection conn = null;	
	public Dao(Connection conn){
		this.conn = conn;
	}
	
	// les différentes méthodes à redéfinir
	public abstract void insert(T obj) throws MyException;
	public abstract void insert(T[] obj) throws MyException;
	public abstract void insert(Collection<T> obj) throws MyException;
	public abstract void update(T obj) throws MyException;
	public abstract void delete(T obj) throws MyException;
	public abstract T find(PK primaryKey) throws MyException;
	public abstract List<T> findAll() throws MyException;
	public abstract List<T> findBetween(PK a, PK b) throws MyException;



	



}
