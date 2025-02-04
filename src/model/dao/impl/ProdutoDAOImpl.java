package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.api.ProdutoDAO;
import model.entidade.Produto;

public class ProdutoDAOImpl implements ProdutoDAO {
				
	private static final String INSERT = "INSERT INTO produto (nome, quantidade) values (?,?)"; 
		
	private static final String UPDATE = "UPDATE produto SET quantidade = ? WHERE codigo = ?"; 
		
	private static final String EXCLUIR = "DELETE FROM produto WHERE codigo = ?"; 
		
	private static final String LISTAR_TODOS = "SELECT * FROM produto";
	
	private static final String LISTAR_MENORES_DEZ = "SELECT * FROM produto WHERE quantidade < 10";
			
	@Override
	public void salvar(Produto produto) {  
		if (produto != null && produto.getCodigo() == 0) {   
			this.novoProduto(produto);  
		} else {  
			this.alterarProduto(produto);  
		}   
	}  
		
	private void novoProduto(Produto produto) {  
		try (PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(INSERT)) {
			ps.setString(1, produto.getNome());
			ps.setInt(2, produto.getQuantidade());
			ps.executeUpdate();  
		} catch (SQLException e) {   
			e.printStackTrace();  
		}
	}
	
	private void alterarProduto(Produto produto) {  
		try {	        
	        String ConsultaQuantidadeAtual = "SELECT quantidade FROM produto WHERE codigo = ?";
	        int quantidadeAtual = 0;
	        
	        try (PreparedStatement psSelect = Conexao.getInstance().getConnection().prepareStatement(ConsultaQuantidadeAtual)) {
	            psSelect.setInt(1, produto.getCodigo());
	            try (ResultSet rs = psSelect.executeQuery()) {
	                if (rs.next()) {
	                    quantidadeAtual = rs.getInt("quantidade");
	                }
	            }
	        }
	        
	        int novaQuantidade = quantidadeAtual + produto.getQuantidade();
	       
	        try (PreparedStatement psUpdate = Conexao.getInstance().getConnection().prepareStatement(UPDATE)) {
	            psUpdate.setInt(1, novaQuantidade);
	            psUpdate.setInt(2, produto.getCodigo());
	            psUpdate.executeUpdate();
	        }
	    
		} catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
		
	@Override 
	public void excluir(Produto produto) {  
		try (PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(EXCLUIR)) {   
			 ps.setInt(1, produto.getCodigo());
			 ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Produto> listarTodos() {
		List<Produto> produtos = new ArrayList<Produto>();
		try (PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(LISTAR_TODOS);
			ResultSet resultset = ps.executeQuery();	
			) {
			while (resultset.next()) {
				Produto produto = new Produto();
				produto.setCodigo(resultset.getInt("codigo"));
				produto.setNome(resultset.getString("nome"));
				produto.setQuantidade(resultset.getInt("quantidade"));
				produtos.add(produto);
			}
		} catch (SQLException e) {   
			e.printStackTrace();  
		}		
		return produtos;
	}
	
	@Override
	public List<Produto> listarMenosDeDez() {
		List<Produto> produtos = new ArrayList<Produto>();
		try (PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(LISTAR_MENORES_DEZ);
			ResultSet resultset = ps.executeQuery();	
			) {
			while (resultset.next()) {
				Produto produto = new Produto();
				produto.setCodigo(resultset.getInt("codigo"));
				produto.setNome(resultset.getString("nome"));
				produto.setQuantidade(resultset.getInt("quantidade"));
				produtos.add(produto);
			}
		} catch (SQLException e) {   
			e.printStackTrace();  
		}		
		return produtos;
	}
}



