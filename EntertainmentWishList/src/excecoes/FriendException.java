package excecoes;

import entity.Usuario;

public class FriendException extends Exception {

	public FriendException(Usuario usuario) {
		super("O usuário " + usuario.getNickName()
				+ " já foi adicionado ou a solicitação ja foi enviada, você pode visualizar isso em Amigos");
	}

}
