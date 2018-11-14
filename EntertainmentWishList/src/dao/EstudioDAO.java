package dao;

import java.util.List;

import entity.Estudio;

public interface EstudioDAO {

	void adicionar(Estudio estudio);

	void alterar(Estudio estudio);

	List<Estudio> buscarEstudios();

	Estudio buscarEspecifico(String nome);

}