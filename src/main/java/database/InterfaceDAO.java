package database;

import java.util.List;

public interface InterfaceDAO<T> {
	
	public T get(String id);
	
	public List<T> getAll();
	
	public void add(T obj);
	
	public void delete(T obj);
	
	public void update(T obj);
}
