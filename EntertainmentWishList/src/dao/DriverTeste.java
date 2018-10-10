package dao;

import java.sql.Date;
import java.util.List;

import entity.Game;
import entity.ItemFavoritos;
import entity.Usuario;
import enumeradas.Etaria;

public class DriverTeste {

	public static void main(String[] args) {
		Game a = new Game();
		a.setNomeOriginal("teste");
		a.setDataLancamento(new Date(1111));
		a.setClassificacaoEtaria(Etaria.PG10);
		a.setGenero("fasfd");

		Game b = new Game();
		b.setId(2);
		b.setNomeOriginal("hadouken");
		b.setDataLancamento(new Date(2555555));
		b.setClassificacaoEtaria(Etaria.PG18);
		b.setGenero("fight");
		GameDAO gd = new GameDAOImpl();
//		 gd.adicionar(a);
		// gd.adicionar(b);
		List<Game> games = gd.buscarGames("ken");
		for (Game s : games) {
			System.out.println(s.getNomeOriginal());
		}
		
		ItemFavoritoDAO it = new ItemFavoritoDAOImpl();
		List<ItemFavoritos> s = it.buscarFavoritos(new Usuario());
		for(ItemFavoritos i : s) {
			System.out.println(i.getNota());
		}

	}

}
