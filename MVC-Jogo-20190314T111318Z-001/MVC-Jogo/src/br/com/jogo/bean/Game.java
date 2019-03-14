package br.com.jogo.bean;

public class Game {
	//Atributos do jogo
	private static int codigo;
	private static String palavra;
	private static String sinonimo1;
	private static String sinonimo2;
	private static String sinonimo3;
	private static String sinonimoVez;
	
	//Construtor Vazio
	public Game (){
	}
	
	//Construtores
	public Game (String palavra, String sinonimo1, String sinonimo2, String sinonimo3) {
		Game.palavra = palavra;
		Game.sinonimo1 = sinonimo1;
		Game.sinonimo2 = sinonimo2;
		Game.sinonimo3 = sinonimo3;
	}
	
	//getters setters
	public static String getPalavra() {
		return palavra;
	}
	public static void setPalavra(String palavra) {
		Game.palavra = palavra;
	}
	public static String getSinonimo1() {
		return sinonimo1;
	}
	public static void setSinonimo1(String sinonimo1) {
		Game.sinonimo1 = sinonimo1;
	}
	public static String getSinonimo2() {
		return sinonimo2;
	}
	public static void setSinonimo2(String sinonimo2) {
		Game.sinonimo2 = sinonimo2;
	}
	public static String getSinonimo3() {
		return sinonimo3;
	}
	public static void setSinonimo3(String sinonimo3) {
		Game.sinonimo3 = sinonimo3;
	}
	public static String getSinonimoVez() {
		return sinonimoVez;
	}

	public static void setSinonimoVez(String sinonimoVez) {
		Game.sinonimoVez = sinonimoVez;
	}

	public static int getCodigo() {
		return codigo;
	}
	public static void setCodigo(int codigo) {
		Game.codigo = codigo;
	}

}