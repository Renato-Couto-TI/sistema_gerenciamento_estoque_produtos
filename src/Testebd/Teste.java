package Testebd;

import model.dao.impl.Conexao;
import view.TelaInicial;

public class Teste {

	public static void main(String[] args) {
		
		Conexao conexao = Conexao.getInstance();
        if (conexao.getConnection() != null) {
            System.out.println("Conexão bem-sucedida com o Banco de Dados!");
        } else {
            System.out.println("Falha na conexão.");
        }

        new TelaInicial();
	}

}
