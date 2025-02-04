package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.api.ProdutoController;
import controller.impl.ProdutoControllerImpl;
import model.entidade.Produto;

public class TelaInclusaoProduto extends JFrame {
	
	private JPanel panel;
	
	private JTextField textFieldNome;
	private JTextField textFieldQuantidade;
	
	private	ProdutoController produtoController;
	
	public TelaInclusaoProduto() {
		setTitle("Cadastro de Novos Produtos");
		setLayout(new FlowLayout());
		
		produtoController = new ProdutoControllerImpl();
		
		panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(500,500));
		add(panel); 
		
		criarTextFieldNome("Nome");
		criarTextFieldQuantidade("Quantidade");
		
		criarBotaoSalvar();
		criarBotaovoltar();
		
		setSize(500,500);
		setPreferredSize(new Dimension(500,500));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void criarLabel(String texto) {
		JLabel label = new JLabel(texto);
		label.setPreferredSize(new Dimension(400,40));
		panel.add(label);
	}
	
		
	private void criarTextFieldNome(String label) {
		this.criarLabel(label);
		textFieldNome = new JTextField(); 
		textFieldNome.setPreferredSize(new Dimension(400,40));
		panel.add(textFieldNome); 
	}
	
	private void criarTextFieldQuantidade(String label) {
		this.criarLabel(label);
		textFieldQuantidade = new JTextField(); 
		textFieldQuantidade.setPreferredSize(new Dimension(400,40));
		panel.add(textFieldQuantidade); 
	}
	
	private void criarBotaoSalvar() { 
		JButton button = new JButton("Salvar"); 
		button.addActionListener(new ButtonSalvarHandler()); 
		button.setPreferredSize(new Dimension(300,40));
		panel.add(button);
	}
	
	private void criarBotaovoltar() { 
		JButton buttonVoltar = new JButton("Voltar"); 
		buttonVoltar.addActionListener(new buttonVoltarHandler()); 
		buttonVoltar.setPreferredSize(new Dimension(300,40));
		panel.add(buttonVoltar); 
	}

	
	private Produto criarObjetoProduto() {
		Produto produto = new Produto();
		if(!textFieldNome.getText().isEmpty()) {
			produto.setNome(textFieldNome.getText());
			produto.setQuantidade(Integer.parseInt(textFieldQuantidade.getText()));
			
		}
		return produto;		
	}
	
	private void limparCampos() {
		textFieldNome.setText("");
		textFieldQuantidade.setText("");
	}
	
	private class ButtonSalvarHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Produto produto = criarObjetoProduto();
			if (produto.getQuantidade() >= 0) {
				produtoController.salvar(produto);
			}
			limparCampos();
			JOptionPane.showMessageDialog(null, "Produto Cadastrado Com Sucesso!");			
		}
				
	}
	
	private class buttonVoltarHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
	    }
	}	


}
