package dao;

import java.util.List;

import entity.Filme;

public interface FilmeDAO {

	void adicionar(Filme filme);

	void alterar(Filme filme);

	List<Filme> buscarFilmes(String nome);

	List<String> buscarNomesFilmes(String nome);

}