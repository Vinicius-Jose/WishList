package dao;

import java.util.List;

import entity.Amigo;
import entity.Usuario;
import enumeradas.StatusAmigo;
import excecoes.FriendException;

public interface AmigoDAO {

	List<Amigo> buscarSolicitacaoRecebida(Usuario user);

	List<Amigo> buscarSolicitacaoEnviada(Usuario user);

	void atualizarAmizade(String usuarioEmail, String usuarioEmail2, StatusAmigo status);

	void adicionarAmigo(Amigo amigo) throws FriendException;

	void remover(Amigo a);

	void deletarSolicitacao(Amigo a);

}