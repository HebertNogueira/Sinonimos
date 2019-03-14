package br.com.jogo.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ConnectionFactory {
	public static Connection getConnection() throws Exception {
		try {
			String DRIVER = "com.mysql.jdbc.Driver";
			String DBNAME = "game";
			String URL = "jdbc:mysql://__IP DO BANCO MYSQL__:3306/" + DBNAME;
			String LOGIN = "root";
			String SENHA = "+++ COLOCAR A SENHA +++";
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, LOGIN, SENHA);
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
	}
	//Fechando Statement, Resultset, Banco
	public static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
}
