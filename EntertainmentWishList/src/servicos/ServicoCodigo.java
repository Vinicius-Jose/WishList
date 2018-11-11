package servicos;

import java.util.Random;

public class ServicoCodigo {

	private String letras ="abYZcd01234FGWXefUVghTyzUijwxRSkl!@#$%^&*_=+-/PQ5mnNOopLMqDErABC6789JKstHIuv";
	
	public String gerarCodigo() {
		Random random = new Random();
		StringBuffer senha = new StringBuffer();
		for(int i = 0; i <=10; i++) {
			senha.append((letras).charAt(random.nextInt(letras.length())));
		}
		return senha.toString();
	}
}
