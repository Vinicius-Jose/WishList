package dao;

import java.util.List;

import entity.Plataforma;

public interface PlataformaDAO {

	void adicionar(Plataforma plataforma);

	void alterar(Plataforma plataforma);

	List<Plataforma> buscarPlataformas();

}