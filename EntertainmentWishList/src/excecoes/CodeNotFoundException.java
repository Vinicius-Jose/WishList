package excecoes;

public class CodeNotFoundException extends Exception {
	public CodeNotFoundException() {
		super("A combina��o usuario/codigo n�o foi encontrada no sistema, verifique se digitou o codigo corretamente ou solicite um novo codigo para a recupera��o de senha");
	}
}
