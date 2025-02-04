package model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static Conexao conexaoSingleton;
	
	private Connection connection;
	
	public Conexao() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/estoqueprodutos", "root", "");			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public static Conexao getInstance() {
		if (conexaoSingleton == null) {
			conexaoSingleton  = new Conexao();
		}
		return conexaoSingleton;
	}
	
	public Connection getConnection() {
		return connection;
	} 
}
