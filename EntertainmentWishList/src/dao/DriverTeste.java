package dao;


import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import entity.Filme;
import entity.Game;
import entity.Indicacao;
import entity.ItemFavoritos;
import entity.Serie;
import entity.Usuario;
import enumeradas.Etaria;
import servicos.ServicoOMDB;

public class DriverTeste {

	public static void main(String[] args) {
		Filme a = new Filme();
		a.setNomeOriginal("Avatar");
		a.setDataLancamento(new Date(1111));
		a.setClassificacaoEtaria(Etaria.PG10);
//
//		Game b = new Game();
//		b.setId(2);
//		b.setNomeOriginal("hadouken");
//		b.setDataLancamento(new Date(2555555));
//		b.setClassificacaoEtaria(Etaria.PG18);
//		b.setGenero("fight");
//		GameDAO gd = new GameDAOImpl();
//		 gd.adicionar(a);
		// gd.adicionar(b);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", new Locale("en"));
		try {
			System.out.println(sdf.parse("18 Dec 2009").toString());
			System.out.println(new java.sql.Date(sdf.parse("04 Jul 2011").getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServicoOMDB so = new ServicoOMDB();
		a = (Filme) so.servicoEntretenimento(a);
		System.out.println("Nome " + a.getNomeOriginal());
		System.out.println("Metacritic " + a.getMetacritic());
		System.out.println("Poster " + a.getPoster());
		System.out.println("data" + a.getDataLancamento());
//		System.out.println("Director " + a.getDiretor());
		
//		List<Game> games = gd.buscarGames("ken");
//		for (Game s : games) {
//			System.out.println(s.getNomeOriginal());
//		}
//		IndicacaoDAO id = new IndicacaoDAOImpl();
//		Usuario user = new Usuario();
//		user.setEmail("pedro");
//		for(Indicacao ind : id.buscarIndicacaoEnviada(user)) {
//			System.out.println(ind.getStatusIndicacao());
//		}
//		
//		
//		ItemFavoritoDAO it = new ItemFavoritoDAOImpl();
//		List<ItemFavoritos> s = it.buscarFavoritos(new Usuario());
//		for(ItemFavoritos i : s) {
//			System.out.println(i.getNota());
//		}
//
	}

}
