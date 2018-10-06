package excecoes;

public class UserException extends Exception {
	public UserException() {
		super("Usu�rio n�o cadastrado ou combina��o de email/senha incorreta");
	}

	public UserException(String email) {
		super("Este email ja est� cadastrado, caso tenha esquecido a senha tente recuper�-la");
	}
}
