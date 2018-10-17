package dao;

import java.util.List;

import entity.Serie;

public interface SerieDAO {

	void adicionar(Serie serie);

	void alterar(Serie serie);

	List<Serie> buscarSeries(String nome);

	List<String> buscarNomesSeries();

}