package servicos;

import java.util.Random;

public class ServicoSenha {

	private String letras ="abYZcd01234FGWXefUVghTyzUijwxRSkl!@#$%^&*_=+-/PQ5mnNOopLMqDErABC6789JKstHIuv";
	
	public String gerarSenha() {
		Random random = new Random();
		StringBuffer senha = new StringBuffer();
		for(int i = 0; i <=8; i++) {
			senha.append((letras).charAt(random.nextInt(letras.length())));
		}
		return senha.toString();
	}
}
