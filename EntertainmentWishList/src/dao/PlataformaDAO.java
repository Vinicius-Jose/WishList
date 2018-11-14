package dao;

import java.util.List;

import entity.Game;
import entity.Plataforma;

public interface PlataformaDAO {

	void adicionarLista(Plataforma plataforma);

	void adicionar(List<Plataforma> list);
	
	void alterar(Plataforma plataforma);

	List<Plataforma> buscarPlataformas();

	List<Plataforma> buscarPlataformas(Game g);
}