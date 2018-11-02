package excecoes;

import entity.Usuario;

public class FriendException extends Exception {

	public FriendException(Usuario usuario) {
		super("O usu�rio " + usuario.getNickName()
				+ " j� foi adicionado ou a solicita��o ja foi enviada, voc� pode visualizar isso em Amigos");
	}

}
