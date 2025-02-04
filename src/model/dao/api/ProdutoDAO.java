package model.dao.api;

import java.util.List;

import model.entidade.Produto;

public interface ProdutoDAO extends DAO<Produto> {
	
	public void salvar(Produto produto);  
	
	public void excluir(Produto produto);  
	
	public List<Produto> listarTodos();
	
	public List<Produto> listarMenosDeDez();

}
