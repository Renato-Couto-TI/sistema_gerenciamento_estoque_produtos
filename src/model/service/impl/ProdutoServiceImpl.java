package model.service.impl;

import java.util.List;

import model.dao.api.ProdutoDAO;
import model.dao.impl.ProdutoDAOImpl;
import model.entidade.Produto;
import model.service.api.ProdutoService;

public class ProdutoServiceImpl implements ProdutoService {
	
	private ProdutoDAO produtoDAO;
	
	public ProdutoServiceImpl() { 
		produtoDAO = new ProdutoDAOImpl(); 
	}

	@Override
	public void salvar(Produto produto) {			
			this.produtoDAO.salvar(produto);				
	}

	@Override
	public void excluir(Produto produto) {
		this.produtoDAO.excluir(produto);		
	}
	
	@Override
	public List<Produto> listarTodos() {		
		return this.produtoDAO.listarTodos();
	}

	@Override
	public List<Produto> listarMenosDeDez() {		
		return this.produtoDAO.listarMenosDeDez();
	}

}
