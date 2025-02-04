package model.dao.api;

import java.util.List;

public interface DAO<T> {
	
	public void salvar(T t);  
	
	public void excluir(T t);  
	
	public List<T> listarTodos();
	
	public List<T> listarMenosDeDez();
}
