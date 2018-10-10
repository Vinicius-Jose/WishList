package dao;

import java.util.List;

import entity.Usuario;
import excecoes.UserException;

public interface UsuarioDAO {

	/* (non-Javadoc)
	 * @see dao.FilmeDAO#adicionar(entity.Usuario)
	 */
	void adicionar(Usuario usuario) throws UserException;

	/* (non-Javadoc)
	 * @see dao.FilmeDAO#alterar(entity.Usuario)
	 */
	void alterar(Usuario usuario);

	/* (non-Javadoc)
	 * @see dao.FilmeDAO#validarUsuario(java.lang.String, java.lang.String)
	 */
	Usuario validarUsuario(String email, String senha) throws UserException;

	/* (non-Javadoc)
	 * @see dao.FilmeDAO#buscarUsuarios(java.lang.String)
	 */
	List<Usuario> buscarUsuarios(String nome);

}