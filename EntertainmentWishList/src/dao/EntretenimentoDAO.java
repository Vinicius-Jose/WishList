package dao;

import java.util.List;

import entity.Entretenimento;
import excecoes.NotEvaluatedException;

public interface EntretenimentoDAO {

	Entretenimento buscarMediaUsuarios(Entretenimento entretenimento) throws NotEvaluatedException;

	List<String> buscarNomes();

}