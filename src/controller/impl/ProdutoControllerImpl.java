package controller.impl;

import java.util.List;

import controller.api.ProdutoController;
import model.entidade.Produto;
import model.service.api.ProdutoService;
import model.service.impl.ProdutoServiceImpl;

public class ProdutoControllerImpl implements ProdutoController {
	
	private ProdutoService produtoService;
	
	public ProdutoControllerImpl() {
		this.produtoService = new ProdutoServiceImpl(); 
	}

	@Override
	public void salvar(Produto produto) {
		if (produto.getQuantidade() >= 0) {
			this.produtoService.salvar(produto);
		}
	}

	@Override
	public void excluir(int id) {
		Produto produto = new Produto();
		produto.setCodigo(id);
		this.produtoService.excluir(produto);		
	}

	@Override
	public List<Produto> listarTodos() {
		return this.produtoService.listarTodos();
	}

	@Override
	public List<Produto> listarMenosDeDez() {
		return this.produtoService.listarMenosDeDez();
	}

}
