package dao;

import java.util.List;

import entity.Entretenimento;
import entity.ItemFavoritos;
import entity.Usuario;

public interface ItemFavoritoDAO {

	void adicionar(ItemFavoritos itemFavoritos);

	void alterar(ItemFavoritos itemFavoritos);

	List<ItemFavoritos> buscarFavoritos(Usuario user);

	void remover(ItemFavoritos itemFavoritos);

	List<ItemFavoritos> buscarCriticas(Entretenimento entretenimento);

}