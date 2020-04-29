package Abdelhafid.pglp_5_2.Dao;

import java.util.ArrayList;

public interface Dao<T> {

	void ajouter(T object);

	T find(int id);

	ArrayList<T> findAll();

	T update(T obj);

	void delete(T obj);

}
