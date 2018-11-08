package dao;

import java.util.List;

import entity.Usuario;
import excecoes.UserException;

public interface UsuarioDAO {

	void adicionar(Usuario usuario) throws UserException;

	void alterar(Usuario usuario);

	Usuario validarUsuario(String email, String senha) throws UserException;

	List<Usuario> buscarUsuarios(String nome);

	Usuario buscarUsuarioEspecifico(String email) throws UserException;

}