package excecoes;

public class CodeNotFoundException extends Exception {
	public CodeNotFoundException() {
		super("A combinação usuario/codigo não foi encontrada no sistema, verifique se digitou o codigo corretamente ou solicite um novo codigo para a recuperação de senha");
	}
}
