package dao;

import java.util.List;

import entity.Amigo;
import entity.Usuario;
import excecoes.FriendException;
import excecoes.UserException;

public interface UsuarioDAO {


	void adicionar(Usuario usuario) throws UserException;


	void alterar(Usuario usuario);


	Usuario validarUsuario(String email, String senha) throws UserException;


	List<Usuario> buscarUsuarios(String nome);
	
	public List<Amigo> buscarSolicitacao(Usuario user);




	void adicionarAmigo(Usuario user, Amigo amigo) throws FriendException;


	Usuario buscarUsuarioEspecifico(String email) throws UserException;

}