package excecoes;

public class UserNotFoundException extends Exception {
	public UserNotFoundException() {
		super("Usu�rio n�o cadastrado ou combina��o de email/senha incorreta");
	}
}
