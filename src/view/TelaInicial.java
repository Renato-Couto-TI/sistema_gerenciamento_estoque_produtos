package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.api.ProdutoController;
import controller.impl.ProdutoControllerImpl;
import model.entidade.Produto;


public class TelaInicial extends JFrame {
	
	private JPanel panel;
		
	private	ProdutoController produtoController;
	
	public TelaInicial() {
		setTitle("Sistema de Gerenciamento de Estoque de Produtos");
		setLayout(new FlowLayout());
		
		produtoController = new ProdutoControllerImpl();
		
		panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(500,500));
		add(panel); 
		
		criarBotaoCadastroNovosProdutos();
		criarBotaoExcluirProdutos();
		criarBotaoAtualizarQuantidadeProdutoEstoque();
		criarBotaoListarTodosProdutos();
		criarBotaoListarProdutosMenosDeDezEstoque();
		criarBotaoFazerBackupArquivo();
		criarBotaoLerArquivoBackup();
		criarBotaoSair();
		
		
		setSize(500,500);
		setPreferredSize(new Dimension(500,500));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
			
	private void criarBotaoCadastroNovosProdutos() { 
		JButton button = new JButton("Cadastrar Novos Produtos"); 
		button.addActionListener(new ButtonCadastrarHandler()); 
		button.setPreferredSize(new Dimension(300,40));
		panel.add(button); 
	}
	
	private void criarBotaoExcluirProdutos() { 
		JButton button = new JButton("Excluir Produtos"); 
		button.addActionListener(new ButtonExcluirHandler()); 
		button.setPreferredSize(new Dimension(300,40));
		panel.add(button); 
	}
	
	private void criarBotaoAtualizarQuantidadeProdutoEstoque() { 
		JButton button = new JButton("Atualizar a Quantidade de Produto no Estoque"); 
		button.addActionListener(new ButtonAtualizarQuantidadeHandler()); 
		button.setPreferredSize(new Dimension(300,40));
		panel.add(button); 
	}
	
	private void criarBotaoListarTodosProdutos() { 
		JButton button = new JButton("Listar Todos os Produtos em Estoque"); 
		button.addActionListener(new ButtonListarTodosHandler()); 
		button.setPreferredSize(new Dimension(300,40));
		panel.add(button); 
	}
	
	private void criarBotaoListarProdutosMenosDeDezEstoque() { 
		JButton button = new JButton("Listar Produtos com Menos de Dez Unidades"); 
		button.addActionListener(new ButtonListarMenosDeDezHandler());
		button.setPreferredSize(new Dimension(300,40));
		panel.add(button); 
	}
	
	private void criarBotaoFazerBackupArquivo() {
		JButton button = new JButton("Fazer Backup dos Produtos em Estoque"); 
		button.addActionListener(new ButtonFazerBackupArquivo());
		button.setPreferredSize(new Dimension(300,40));
		panel.add(button);
	}
		
	private void criarBotaoLerArquivoBackup() {
		JButton button = new JButton("Visualizar Arquivo de Backup na Console"); 
		button.addActionListener(new ButtonLerArquivoBackup());
		button.setPreferredSize(new Dimension(300,40));
		panel.add(button);
	}
	
	private void criarBotaoSair() { 
		JButton button = new JButton("Sair"); 
		button.addActionListener(new buttonSairHandler()); 
		button.setPreferredSize(new Dimension(300,40));
		panel.add(button); 
	}
	
	private class ButtonCadastrarHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new TelaInclusaoProduto();			
		}
	}
	
	private class ButtonExcluirHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new TelaExcluirProduto();		}
	}
	
	private class ButtonAtualizarQuantidadeHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new TelaAtualizacaoQuantidadeProduto();		}
	}
		
	private class ButtonListarTodosHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new TelaListarTodosProdutos();			
		}
	}
	
	private class ButtonListarMenosDeDezHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new TelaProdutosMenosDeDezEstoque();			
		}
	}
	
	private class ButtonFazerBackupArquivo implements ActionListener {
		
		private DefaultListModel<Produto> listProdutoModel;
		private	ProdutoController produtoController;
				
		@Override
		public void actionPerformed(ActionEvent e) {
			
			produtoController = new ProdutoControllerImpl();
						
			listProdutoModel = new DefaultListModel<>();
			
			listProdutoModel.clear();
			List<Produto> produtosCarregados = produtoController.listarTodos();
					
			for (Produto produto : produtosCarregados) {
				listProdutoModel.addElement(produto);
			}
			
			escreverObjeto("arquivo_backup", produtosCarregados);
					
		}
	
       
		public void escreverObjeto(String nomeArquivo, List<Produto> produtos) { 
			OutputStream outputStream = null; 
			ObjectOutputStream dataOutputStream = null; 
			try {
				File file = new File(nomeArquivo); 
				outputStream = new FileOutputStream(file); 
				dataOutputStream = new ObjectOutputStream(outputStream); 
				dataOutputStream.writeObject(produtos);
				JOptionPane.showMessageDialog(null, "Backup salvo em " + file.getAbsolutePath());
			} catch (IOException e) { 
				JOptionPane.showMessageDialog(null, "Erro ao realizar a escrita no arquivo" + e.getMessage()); 
				e.printStackTrace(); 
			} finally { 
				try { 
					if (dataOutputStream != null) { 
						dataOutputStream.close(); 
					} 
					if (outputStream != null) { 
						outputStream.close(); } 
				} catch (IOException e) { 
					JOptionPane.showMessageDialog(null, "Erro ao realizar o fechamento do arquivo"); 
				} 
			} 
		}
		
	}
	
	private class ButtonLerArquivoBackup implements ActionListener {
		
		private DefaultListModel<Produto> listProdutoModel;
		private	ProdutoController produtoController;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			produtoController = new ProdutoControllerImpl();
						
			listProdutoModel = new DefaultListModel<>();
			
			listProdutoModel.clear();
			List<Produto> produtosCarregados = produtoController.listarTodos();
					
			for (Produto produto : produtosCarregados) {
				listProdutoModel.addElement(produto);
			}				
			
			lerObjeto("arquivo_backup");
				
		}

		public void lerObjeto(String nomeArquivo) { 
			InputStream inputStream = null; 
			ObjectInputStream dataInputStream = null; 
			try {  
				File file = new File(nomeArquivo);  
				inputStream = new FileInputStream(file);  
				dataInputStream = new ObjectInputStream(inputStream);    
				
				List<Produto> produtos = (List<Produto>) dataInputStream.readObject();
				
				for (Produto produto : produtos) {
		            System.out.println("Código: " + produto.getCodigo());
		            System.out.println("Nome: " + produto.getNome());
		            System.out.println("Quantidade: " + produto.getQuantidade());
		        }
				
				} catch (IOException e) {  
					JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo binário!" + e.getMessage());
					e.printStackTrace();
				} catch (ClassNotFoundException e) {  
					JOptionPane.showMessageDialog(null, "Não foi possível converter o valor lido para "    + "o tipo de objeto escolhido.");
					e.printStackTrace();
				} finally {  
					try {
						if (inputStream != null) {
							inputStream.close();   
						}   
						if (dataInputStream != null) {    
							dataInputStream.close();   
						}  
						} catch (Exception e) {   
							JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo!");  
						} 
				} 
		}
		
	}
					
	private void criarBotaoVoltar() { 
		JButton buttonVoltar = new JButton("Voltar"); 
		buttonVoltar.addActionListener(new buttonVoltarHandler()); 
		buttonVoltar.setPreferredSize(new Dimension(300,40));
		panel.add(buttonVoltar); 
	}
				
	private class buttonVoltarHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
	    }
	}
				
	
	private class buttonSairHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
	    }
	}	

			
}
