package br.com.jogo.view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.jogo.bean.Game;
import br.com.jogo.dao.GameDAO;

@SuppressWarnings("serial")
public class Tela extends JFrame {
	
	//ATRIBUTOS DA CLASSE TELA
	private static JPanel contentPane;

	private static JTextField txtCaixaSinonimos;
	private static JTextField txtPalavra;

	private static JLabel sorryClosed;
	private static JLabel lblLogoSinonimos;
	private static JLabel lblFundoTijolos;
	private static JLabel lblScorePosicao1 = new JLabel("");
	private static JLabel lblScorePosicao2 = new JLabel("");
	private static JLabel lblScorePosicao3 = new JLabel("");
	private static JLabel lblScorePosicao4 = new JLabel("");
	private static JLabel lblPula = new JLabel("");
	private static JLabel lblOutro = new JLabel("");

	private static JButton btnNovo;
	private static JButton btnOutro;
	private static JButton btnPula;
	private static JButton btnEnviar;

	private static int posicaoBranco = 3;
	private static int posicaoAmarelo = 3;

	private static int totalPontuacao = 0;
	private static int acertosConsecultivos = 0;
	private static int acertos = 0;

	private static boolean igualdade = false;
	private static boolean verificaNull = false;
	private static boolean play = false;
	private static boolean checa3Outro = false;
	private static boolean diretoEnviar = false;

	private static boolean primeiraTentativa = true;
	private static boolean segundaTentativa = false;
	private static boolean terceiraTentativa = false;

	private static String msgNovoJogo = "PRESSIONE NOVO JOGO";
	private static String pos01String = "";
	private static String pos02String = "";
	private static String pos03String = "";
	private static String pos04String = "";

	private static Icon sorryClosedIMG = new ImageIcon(Tela.class.getResource("/br/com/jogo/view/closed.png"));
	private static Icon branco1 = new ImageIcon(Tela.class.getResource("/br/com/jogo/view/1_branco.png"));
	private static Icon branco2 = new ImageIcon(Tela.class.getResource("/br/com/jogo/view/2_branco.png"));
	private static Icon branco3 = new ImageIcon(Tela.class.getResource("/br/com/jogo/view/3_branco.png"));
	private static Icon amarelo1 = new ImageIcon(Tela.class.getResource("/br/com/jogo/view/1_amarelo.png"));
	private static Icon amarelo2 = new ImageIcon(Tela.class.getResource("/br/com/jogo/view/2_amarelo.png"));
	private static Icon amarelo3 = new ImageIcon(Tela.class.getResource("/br/com/jogo/view/3_amarelo.png"));
	private static Icon logo = new ImageIcon(Tela.class.getResource("/br/com/jogo/view/logo100.png"));
	private static Icon parabens = new ImageIcon(Tela.class.getResource("/br/com/jogo/view/parabens.png"));
 
	//METODO MAIN QUE INICIA O JOGO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// METODO QUE COMPOE OS PARAMETROS DA TELA.
	public Tela() throws Exception {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// INSTANCIA PARA PEGAR OS VALORES DE RESOLUÇÃO DE TELA
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tela.class.getResource("/br/com/jogo/view/icon.png")));
		setFont(new Font("Sophia", Font.PLAIN, 12));
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setTitle("#Sin\u00F4nimos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// CENTRALIZA O JFRAME CONFORME VALORES OBTIDOS ANTERIORMENTE
		setBounds(((dim.width / 2) - (638 / 2)), ((dim.height / 2) - (463 / 2)), 638, 463);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblScorePosicao1 = new JLabel("");
		lblScorePosicao1.setIcon(null);
		lblScorePosicao1.setBounds(225, 341, 35, 47);
		contentPane.add(lblScorePosicao1);

		lblScorePosicao2 = new JLabel("");
		lblScorePosicao2.setIcon(null);
		lblScorePosicao2.setBounds(265, 335, 35, 47);
		contentPane.add(lblScorePosicao2);

		lblScorePosicao3 = new JLabel("");
		lblScorePosicao3.setIcon(null);
		lblScorePosicao3.setBounds(305, 343, 35, 47);
		contentPane.add(lblScorePosicao3);

		lblScorePosicao4 = new JLabel("");
		lblScorePosicao4.setIcon(null);
		lblScorePosicao4.setBounds(345, 338, 35, 47);
		contentPane.add(lblScorePosicao4);

		sorryClosed = new JLabel("");
		sorryClosed.setIcon(null);
		sorryClosed.setBounds(99, -13, 410, 462);
		contentPane.add(sorryClosed);

		lblLogoSinonimos = new JLabel("");
		lblLogoSinonimos.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoSinonimos.setIcon(logo);
		lblLogoSinonimos.setBounds(10, 13, 614, 115);
		contentPane.add(lblLogoSinonimos);

		// BOTÃO NOVO
		btnNovo = new JButton("");
		btnNovo.setPressedIcon(new ImageIcon(Tela.class.getResource("/br/com/jogo/view/novo_pressed.png")));
		btnNovo.setSelectedIcon(null);
		btnNovo.setBorder(null);
		btnNovo.setForeground(Color.WHITE);
		btnNovo.setIcon(new ImageIcon(Tela.class.getResource("/br/com/jogo/view/novo.png")));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// INICIA O JOGO
				novoJogo();

			}
		});
		btnNovo.setBounds(10, 389, 98, 39);
		contentPane.add(btnNovo);

		// BOTÃO OUTRO
		btnOutro = new JButton("");
		btnOutro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// VERIFICA SE O JOGO FOI INICIADO
				if (play) {
					// VERIFICA SE METODO OUTRO FOI CHAMADO DIRETO PELO ENVIAR
					// SE SIM FINALIZA JOGO, SE NÃO EXECUTA METODO PULA
					diretoEnviar = false;
					// CHAMA O METODO DE OUTRO E PULA
					checaOutro();
					checaPula();
				} else {
					JOptionPane.showMessageDialog(null, msgNovoJogo);
				}

			}
		});
		btnOutro.setPressedIcon(new ImageIcon(Tela.class.getResource("/br/com/jogo/view/outro_pressed.png")));
		btnOutro.setBorder(null);
		btnOutro.setIcon(new ImageIcon(Tela.class.getResource("/br/com/jogo/view/outro.png")));
		btnOutro.setBounds(425, 389, 106, 39);
		contentPane.add(btnOutro);

		// BOTÃO PULA
		btnPula = new JButton("");
		btnPula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// VERIFICA SE O JOGO FOI INICIADO
				if (play) {

					// VERIFICA SE METODO OUTRO FOI CHAMADO DIRETO PELO ENVIAR
					// SE SIM FINALIZA JOGO, SE NÃO EXECUTA METODO PULA
					diretoEnviar = false;

					// VALIDA VARIAVEL PARA ACESSAR AS CONDIÇÕES DE PULA
					checa3Outro = true;

					// CHAMA O METODO PULA
					checaPula();
				} else {
					JOptionPane.showMessageDialog(null, msgNovoJogo);
				}

			}
		});
		btnPula.setPressedIcon(new ImageIcon(Tela.class.getResource("/br/com/jogo/view/pula_pressed.png")));
		btnPula.setBorder(null);
		btnPula.setIcon(new ImageIcon(Tela.class.getResource("/br/com/jogo/view/pula.png")));
		btnPula.setBounds(541, 389, 83, 39);
		contentPane.add(btnPula);

		btnEnviar = new JButton("");
		btnEnviar.setPressedIcon(new ImageIcon(Tela.class.getResource("/br/com/jogo/view/enviar_pressed.png")));
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// VERIFICA SE METODO OUTRO FOI CHAMADO DIRETO PELO ENVIAR
				// SE SIM FINALIZA JOGO, SE NÃO EXECUTA METODO PULA
				diretoEnviar = true;

				// CHAMA METODO DE COMPAÇÃO DE PALAVRAS
				compararPalavras();

			}
		});
		btnEnviar.setBorder(null);
		btnEnviar.setIcon(new ImageIcon(Tela.class.getResource("/br/com/jogo/view/enviar.png")));
		btnEnviar.setBounds(258, 319, 120, 39);
		contentPane.add(btnEnviar);

		lblOutro.setIcon(amarelo3);
		lblOutro.setBounds(459, 330, 46, 74);
		contentPane.add(lblOutro);

		lblPula.setIcon(branco3);
		lblPula.setBounds(562, 330, 46, 74);
		contentPane.add(lblPula);

		txtPalavra = new JTextField();
		txtPalavra.setText("");
		txtPalavra.setHorizontalAlignment(SwingConstants.CENTER);
		txtPalavra.setForeground(new Color(0, 85, 66));
		txtPalavra.setBackground(new Color(255, 255, 255));
		txtPalavra.setFont(new Font("Sophia", Font.BOLD, 50));
		txtPalavra.setColumns(10);
		txtPalavra.setBounds(89, 224, 457, 55);
		contentPane.add(txtPalavra);

		txtCaixaSinonimos = new JTextField();
		txtCaixaSinonimos.setEditable(false);
		txtCaixaSinonimos.setText("B E M - V I N D O");
		txtCaixaSinonimos.setHorizontalAlignment(SwingConstants.CENTER);
		txtCaixaSinonimos.setForeground(new Color(0, 85, 66));
		txtCaixaSinonimos.setBackground(new Color(255, 255, 255));
		txtCaixaSinonimos.setFont(new Font("Sophia", Font.PLAIN, 60));
		txtCaixaSinonimos.setColumns(10);
		txtCaixaSinonimos.setBounds(10, 139, 614, 74);
		contentPane.add(txtCaixaSinonimos);
		
		//IMAGEM DE FUNDA DA TELA
		lblFundoTijolos = new JLabel("");
		lblFundoTijolos.setIcon(new ImageIcon(Tela.class.getResource("/br/com/jogo/view/fundo.jpg")));
		lblFundoTijolos.setBounds(0, 0, 634, 442);
		contentPane.add(lblFundoTijolos);

	}

	// METODO PARA COMPARAÇÃO DE IGUALDADE
	public void checaPalavra(String obj01, String obj02) {
		try {
			if (obj01.equalsIgnoreCase(obj02))
				igualdade = true;
		} catch (Exception e) {
			verificaNull = true;
		}
	}

	// METODO PARA O BOTÃO ENVIAR
	public void compararPalavras() {
		// ENVIA AS PALAVRAS PARA CHECAGEM
		checaPalavra(Game.getPalavra(), txtPalavra.getText());
		// VERIFICA SE O JOGO FOI INICIADO
		if (play == false) {
			JOptionPane.showMessageDialog(null, msgNovoJogo);
			verificaNull = false;
		} else
		// VERIFICA SE HOUVE IGUALDADE NAS PALAVRAS
		if (igualdade) {
			// EXECUÇÃO AÇÕES PERTINENTES AO RESULTADO
			acertosConsecultivos++;
			lblLogoSinonimos.setIcon(parabens);
			txtPalavra.setText("");
			igualdade = false;
			// METODO QUE ADICIONA PONTUAÇÃO
			pontuacao();
			// CHAMA NOVA PALAVRA
			novaPalavra();
		} else {
			checaOutro();
			checaPula();
		}
	}

	// METODO EQUIVALENTE AO BOTÃO OUTRO
	public void checaOutro() {
		// VERIFICA A POSIÇÃO QUE ESTÁ EM OUTRO
		if (posicaoAmarelo == 3) {
			// ATUALIZA POSIÇÃO PARA PONTUAÇÃO
			primeiraTentativa = false;
			segundaTentativa = true;
			// MUDA ICONE DE NUMERO DA POSIÇÃO
			lblOutro.setIcon(amarelo2);
			// PEGA OUTRO SINONIMO
			txtCaixaSinonimos.setText(Game.getSinonimo2());
			// DECREMENTA POSIÇÃO
			posicaoAmarelo--;
		} else if (posicaoAmarelo == 2) {
			segundaTentativa = false;
			terceiraTentativa = true;
			lblOutro.setIcon(amarelo1);
			txtCaixaSinonimos.setText(Game.getSinonimo3());
			posicaoAmarelo--;
		} else if (posicaoAmarelo == 1) {
			// SE METODO FOI CHAMADO PELO BOITÃO ENVIAR, FIM DE JOGO
			// SE NÃO, UTILIZA 1 PULA
			if (diretoEnviar) {
				fimDeJogo();
			} else {
				if (posicaoBranco == 1) {
					posicaoAmarelo = 3;
					checa3Outro = true;
				} else {
					lblOutro.setIcon(amarelo3);
					posicaoAmarelo = 3;
					checa3Outro = true;
				}
			}
		}
	}

	//METODO EQUIVALENTE AO BOTÃO PULA
	public void checaPula() {

		// VERIFICA A POSIÇÃO QUE ESTÁ EM OUTRO E SE
		// METODO FOI CHAMADO PELA 3ª VEZ DO BOTÃO OUTRO
		if ((posicaoBranco == 3) && (checa3Outro)) {
			// ZERA ACERTOS CONSECULTIVOS
			acertosConsecultivos = 0;
			// CHAMA NOVA PALAVRA
			novaPalavra();
			// MUDA NUMERO DE OUTRO
			lblPula.setIcon(branco2);
			// DECREMENTA POSIÇÃO DE OUTRO
			posicaoBranco--;
			// DESATIVA 3º OUTRO PARA NOVA CHECAGEM
			checa3Outro = false;
		} else

		if ((posicaoBranco == 2) && (checa3Outro)) {
			acertosConsecultivos = 0;
			novaPalavra();
			lblPula.setIcon(branco1);
			posicaoBranco--;
			checa3Outro = false;
		} else

		if ((posicaoBranco == 1) && (checa3Outro)) {
			fimDeJogo();
		}
	}

	// METODO PARA CHAMAR NOVA PALAVRA NO BANCO
	public void novaPalavra() {
		try {
			// PEGA NOVA PALAVRA NO BANCO
			GameDAO dao = new GameDAO();
			dao.pegaPalavra();
			// INFORMA NOVA PALAVRA PARA JOGADOR
			txtCaixaSinonimos.setText(Game.getSinonimo1());
			// RENOVA TENTATIVAS E POSIÇÃO DO BOTÃO OUTRO
			lblOutro.setIcon(amarelo3);
			posicaoAmarelo = 3;
		} catch (Exception ExNOVO) {
			ExNOVO.printStackTrace();
		}
	}

	// METODO PARA INICIAR O JOGO
	public void novoJogo() {
		try {
			// CHAMA METODO PARA ZERAR ATRIBUTOS
			zerarAtributos();
			// RETIRA MENSAGEM DE FIM DE JOGO
			sorryClosed.setIcon(null);
			// PEGA NOVA PALAVRA NO BANCO
			GameDAO dao = new GameDAO();
			dao.pegaPalavra();
			// INFORMA NOVA PALAVRA PARA JOGADOR
			txtCaixaSinonimos.setText(Game.getSinonimo1());
			// VALIDA INICIO DO JOGO
			play = true;
		} catch (Exception ExNOVO) {
			ExNOVO.printStackTrace();
		}
	}

	// METODO PARA SETAR ATRIBUTOS AO VALORES INICIAIS
	public void zerarAtributos() {
		igualdade = false;
		verificaNull = false;
		play = false;
		checa3Outro = false;
		posicaoBranco = 3;
		posicaoAmarelo = 3;
		lblLogoSinonimos.setIcon(logo);
		lblPula.setIcon(branco3);
		lblOutro.setIcon(amarelo3);
		lblScorePosicao1.setIcon(null);
		lblScorePosicao2.setIcon(null);
		lblScorePosicao3.setIcon(null);
		lblScorePosicao4.setIcon(null);
		btnEnviar.setBounds(258, 319, 120, 39);
		txtPalavra.setText("");
	}

	// METODO PARA CONVERTER PONTUAÇÃO EM IMAGENS
	public void pegaPontuacao(String pontos) {
		pos01String = "/br/com/jogo/view/" + String.valueOf(pontos.charAt(0)) + "_score.png";
		pos02String = "/br/com/jogo/view/" + String.valueOf(pontos.charAt(1)) + "_score.png";
		pos03String = "/br/com/jogo/view/" + String.valueOf(pontos.charAt(2)) + "_score.png";
		pos04String = "/br/com/jogo/view/" + String.valueOf(pontos.charAt(3)) + "_score.png";
		lblScorePosicao1.setIcon(new ImageIcon(Tela.class.getResource(pos01String)));
		lblScorePosicao2.setIcon(new ImageIcon(Tela.class.getResource(pos02String)));
		lblScorePosicao3.setIcon(new ImageIcon(Tela.class.getResource(pos03String)));
		lblScorePosicao4.setIcon(new ImageIcon(Tela.class.getResource(pos04String)));
		btnEnviar.setBounds(1, 1, 1, 1);
	}

	// METODO DE PONTUAÇÃO
	public void pontuacao() {
		// SE 5 ACERTOS CONSECUTIVOS GANHA 1 PULA COM LIMITE DE 3
		if (acertosConsecultivos == 5)
			if (posicaoBranco == 2) {
				lblPula.setIcon(branco3);
				posicaoBranco++;
			}
		if (posicaoBranco == 1) {
			lblPula.setIcon(branco2);
			posicaoBranco++;
		}
		// VERIFICA EM QUAL TENTATIVA FOI O ACERTO E INCREMENTA PONTO
		if (primeiraTentativa == true)
			totalPontuacao += 75;
		if (segundaTentativa == true)
			totalPontuacao += 50;
		if (terceiraTentativa == true)
			totalPontuacao += 25;
	}

	// FORMATA A PONTUAÇÃO PARA XXXX (4 DIGITOS STRING)
	public String formatacao(int decimal) {
		int resultado = decimal;
		String ResultadoString = String.valueOf(resultado);
		if ((resultado > 0) && (resultado < 10)) {
			ResultadoString = "000" + ResultadoString;
		}
		if ((resultado >= 10) && (resultado < 100)) {
			ResultadoString = "00" + ResultadoString;
		}
		if ((resultado >= 100) && (resultado < 1000)) {
			ResultadoString = "0" + ResultadoString;
		}
		return ResultadoString;
	}

	// METODO DE FIM DO JOGO
	public void fimDeJogo() {
		// CALCULA PONTUAÇÃO
		pontuacao();
		// PEGA A PONTUAÇÃO EM IMAGEMS E INFORMA NA TELA
		pegaPontuacao(formatacao(totalPontuacao));
		// MOSTRA A IMAGEM DE FIM DE JOGO
		sorryClosed.setIcon(sorryClosedIMG);
		// ENCERRA O JOGO
		play = false;
	}

}
