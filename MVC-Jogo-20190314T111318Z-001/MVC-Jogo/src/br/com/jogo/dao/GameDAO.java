package br.com.jogo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.jogo.bean.Game;
import br.com.jogo.util.ConnectionFactory;

public class GameDAO {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private String SQL;
	
	// Chamando Classe ConnectionFactory e abre conexão com o Banco
	public GameDAO(){
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception erro) {
			erro.printStackTrace();;
		}
	}
	
	public void pegaPalavra () {

		int contagemLinhas = 0;
		String codSQL = "";
		int x =0;
			try {
				
				//BUSCA TODOS OS CODIGO DISPONIVIS NO BANCO
				SQL = "SELECT codigo FROM palavras where utilizado = 'N'";
				ps = conn.prepareStatement(SQL);
				rs = ps.executeQuery();
				
				//CONTA QUANTAS LINHAS FORAM ENCONTRADAS
				while(rs.next()){
					contagemLinhas = rs.getRow();
				}				
	
				//FAZ O SORTEIO RANDOMICO DE 1 POSIÇÃO
				int sorteioCodigo =  gerarRandomico(contagemLinhas);
				
				//ABRE UM VETOR COM A QUANTIDADE ENCONTRADE DE LINHAS
				String armzCod [] = new String [contagemLinhas];
					
				//ARMAZENA O CODIGO ENCONTRADO EM CADA POSIÇÃO
				rs = ps.executeQuery();
				while(rs.next()){					
					armzCod [x] = rs.getString("codigo");
					x++;		
				}
				
				//SELECIONA UM CODIGO COM O NUMERO SORTEADO NO RANDOM
				codSQL = armzCod[sorteioCodigo];
				
				//FAZ A BUSCA DAS PALAVRAS E SINONIMOS DA VEZ
				SQL = "SELECT codigo,palavra,sinonimo1,sinonimo2,sinonimo3 FROM palavras where codigo = ? and utilizado = 'N'";
				ps = conn.prepareStatement(SQL);
				ps.setString(1, codSQL);
				rs = ps.executeQuery();
				
				//PEGA O RESULTADO DA SELECT, ARMAZENA E ENVIA PARA A CLASSE GAME
				while(rs.next()){
					int codigo = rs.getInt("codigo");
					String palavra = rs.getString("palavra");
					String sinonimo1 = rs.getString("sinonimo1");
					String sinonimo2 = rs.getString("sinonimo2");
					String sinonimo3 = rs.getString("sinonimo3");						
					new Game(palavra, sinonimo1, sinonimo2, sinonimo3);
					}
				
				//ATUALIZA A PALAVRA COMO UTILIZADA (Utilizada = S)
				SQL = "update palavras set utilizado = 'S' where codigo = ?";
				ps = conn.prepareStatement(SQL);
				ps.setString(1, codSQL);
				ps.executeUpdate();
				ConnectionFactory.close(conn, ps, rs);
			}	
			catch (Exception ExPegaPalavra) {
				LimparUtilizacao();
				pegaPalavra();
			} 
		
	}	
	
	//METODO PARA GERAR RANDOMICO
	public int gerarRandomico (int randomico){
		int resultadoRandom = (int) (Math.random () * randomico);
		return resultadoRandom;
	}
	
	
	// LIMPA UTILIZAÇÃO DAS PALAVRAS
	public void LimparUtilizacao(){
		int contagemLinhas = 0;
		int x = 0;
		String codSQL="";
		
		//BUSCA TODOS OS CODIGO DISPONIVIS NO BANCO
		SQL = "SELECT codigo FROM palavras";
		try {
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			
			//CONTA QUANTAS LINHAS FORAM ENCONTRADAS
			while(rs.next()){
				contagemLinhas = rs.getRow();
			}
			
			//ABRE UM VETOR COM A QUANTIDADE ENCONTRADE DE LINHAS
			String armzCod [] = new String [contagemLinhas];
				
			//ARMAZENA O CODIGO ENCONTRADO EM CADA POSIÇÃO
			rs = ps.executeQuery();
			while(rs.next()){
			armzCod [x] = rs.getString("codigo");
			codSQL = armzCod[x];
			SQL = "update palavras set utilizado = 'N' where codigo = ?";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, codSQL);
			ps.executeUpdate();
			x++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}	
}
