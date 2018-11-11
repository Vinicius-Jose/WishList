package dao;

import entity.Codigo;
import excecoes.CodeNotFoundException;

public interface CodigoDAO {

	/* (non-Javadoc)
	 * @see dao.AmigoDAO#adicionarAmigo(entity.Usuario, entity.Amigo)
	 */
	void adicionarCodigo(Codigo c);

	void remover(Codigo c);

	boolean buscarCodigo(Codigo c) throws CodeNotFoundException;

}