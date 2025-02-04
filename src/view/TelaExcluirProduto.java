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


public class TelaExcluirProduto extends JFrame {
	
	private JPanel panel;
	private JTextField textFieldId;
	
	private	ProdutoController produtoController;
	
	public TelaExcluirProduto() {
		setTitle("Excluir Produtos");
		setLayout(new FlowLayout());
		
		produtoController = new ProdutoControllerImpl();
		
		panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(500,500));
		add(panel); 
		
		criarTextFieldId("Codigo do Produto a ser Excluido");
		
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
	
	private void criarTextFieldId(String label) {
		this.criarLabel(label);
		textFieldId = new JTextField(); 
		textFieldId.setPreferredSize(new Dimension(300,40));
		panel.add(textFieldId); 
	}
	
		
	private void criarBotaoSalvar() { 
		JButton buttonSalvar = new JButton("Salvar"); 
		buttonSalvar.addActionListener(new ButtonExcluirHandler()); 
		panel.add(buttonSalvar); 
	}
	
	private void criarBotaovoltar() { 
		JButton buttonVoltar = new JButton("Voltar"); 
		buttonVoltar.addActionListener(new buttonVoltarHandler()); 
		buttonVoltar.setPreferredSize(new Dimension(300,40));
		panel.add(buttonVoltar); 
	}
	
	private Produto criarObjetoProduto() {
		Produto produto = new Produto();
		if(!textFieldId.getText().isEmpty()) {
			produto.setQuantidade(Integer.parseInt(textFieldId.getText()));
		}		
		return produto;
	}
	
	private void limparCampos() {
		textFieldId.setText("");		
	}
	
		
	private class ButtonExcluirHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			produtoController.excluir(Integer.parseInt(textFieldId.getText()));;
			limparCampos();
			JOptionPane.showMessageDialog(null, "Produto Excluido Com Sucesso!");			
		}
				
	}
	
	private class buttonVoltarHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
	    }
	}

}

