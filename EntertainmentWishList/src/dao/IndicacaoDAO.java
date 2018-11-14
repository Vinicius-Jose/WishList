package dao;

import java.util.List;

import entity.Indicacao;
import entity.Usuario;

public interface IndicacaoDAO {

	void adicionar(Indicacao indicacao);

	void alterar(Indicacao indicacao);

	List<Indicacao> buscarIndicacaoEnviada(Usuario user);

	List<Indicacao> buscarIndicacaoRecebida(Usuario user);

	void remover(Indicacao indicacao);

}