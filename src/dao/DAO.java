package dao;

import java.util.ArrayList;

public interface DAO<T> {

	public void addObj(T obj);

	public void atualizarObj(T obj);
	
	public T getObj(int key);
	
	public void removerObj(int key);
	
	public ArrayList<T> getAllObj();

}