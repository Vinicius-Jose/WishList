package excecoes;

public class UserException extends Exception {
	public UserException() {
		super("Usu�rio n�o cadastrado ou combina��o de email/senha incorreta");
	}

	public UserException(String email) {
		super("O email informado n�o foi encontrado no sistema");
	}

	public UserException(String email, String primeiroNome) {
		super("Este email ja est� cadastrado, caso tenha esquecido a senha tente recuper�-la");
	}
}
