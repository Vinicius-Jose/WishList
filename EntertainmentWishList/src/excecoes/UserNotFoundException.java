package excecoes;

public class UserNotFoundException extends Exception {
	public UserNotFoundException() {
		super("Usuário não cadastrado ou combinação de email/senha incorreta");
	}
}
