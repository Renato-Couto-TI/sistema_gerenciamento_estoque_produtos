package controller.api;

import java.util.List;

import model.entidade.Produto;

public interface ProdutoController {
	
	public void salvar(Produto produto);  
	
	public void excluir(int id);  
	
	public List<Produto> listarTodos();
	
	public List<Produto> listarMenosDeDez();

}
