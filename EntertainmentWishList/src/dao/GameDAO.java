package dao;

import java.util.List;

import entity.Game;

public interface GameDAO {

	void adicionar(Game game);

	void alterar(Game game);

	List<Game> buscarGames(String nome);

}