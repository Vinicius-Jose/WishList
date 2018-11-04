package excecoes;

public class UserException extends Exception {
	public UserException() {
		super("Usuário não cadastrado ou combinação de email/senha incorreta");
	}

	public UserException(String email) {
		super("O email informado não foi encontrado no sistema");
	}

	public UserException(String email, String primeiroNome) {
		super("Este email ja está cadastrado, caso tenha esquecido a senha tente recuperá-la");
	}
}
