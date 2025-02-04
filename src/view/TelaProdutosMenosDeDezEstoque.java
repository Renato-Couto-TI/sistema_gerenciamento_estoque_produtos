package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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


public class TelaProdutosMenosDeDezEstoque extends JFrame {
	
	private JPanel panel;
	private JList<Produto> listProdutos;
	private DefaultListModel<Produto> listProdutoModel;
	private	ProdutoController produtoController;
	
	public TelaProdutosMenosDeDezEstoque() {
		setTitle("Obter Listagem dos Produtos com Menos De Dez Unidades em Estoque");
		setLayout(new FlowLayout());
		
		produtoController = new ProdutoControllerImpl();
		
		panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(500,500));
		add(panel); 
		
		listProdutoModel = new DefaultListModel<>();
        listProdutos = new JList<>(listProdutoModel);
        listProdutos.setPreferredSize(new Dimension(400, 300));
        JScrollPane scrollPane = new JScrollPane(listProdutos);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        panel.add(scrollPane);
					
        criarBotaoListarMenosDezUnidades();
		criarBotaovoltar();
						
		setSize(500,500);
		setPreferredSize(new Dimension(500,500));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
					
	private void criarBotaoListarMenosDezUnidades() { 
		JButton button = new JButton("Listar Produtos com Menos De Dez Unidades em Estoque"); 
		button.addActionListener(new ButtonListarMenosDezUnidadesHandler()); 
		button.setPreferredSize(new Dimension(300,40));
		panel.add(button);						 
	}
	
	private void criarBotaovoltar() { 
		JButton buttonVoltar = new JButton("Voltar"); 
		buttonVoltar.addActionListener(new buttonVoltarHandler()); 
		buttonVoltar.setPreferredSize(new Dimension(300,40));
		panel.add(buttonVoltar); 
	}
			
	private class ButtonListarMenosDezUnidadesHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			listProdutoModel.clear();
			List<Produto> produtosCarregados = produtoController.listarMenosDeDez();
			
			for (Produto produto : produtosCarregados) {
                listProdutoModel.addElement(produto);
            }
			
		}
		
	}
	
	private class buttonVoltarHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
	    }
	}	


}

