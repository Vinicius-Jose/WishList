package dao;


import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import entity.Estudio;
import entity.Filme;
import entity.Game;
import entity.Indicacao;
import entity.ItemFavoritos;
import entity.Serie;
import entity.Usuario;
import enumeradas.Etaria;
import servicos.ServicoEmail;
import servicos.ServicoEntretenimento;

public class DriverTeste {

	public static void main(String[] args) {
		Filme a = new Filme();
		a.setNomeOriginal("Kill Bill");
		
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

		ServicoEntretenimento so = new ServicoEntretenimento();
		a = (Filme) so.servicoEntretenimento(a);
		a.setDuracao(0);
		a.setClassificacaoEtaria(Etaria.PG12);
		Estudio es = new Estudio();
		es.setLocalizacao("USA");
		es.setNome("Universal");
		EstudioDAO ed = new EstudioDAOImpl();
		ed.adicionar(es);
		a.setEstudio(es);
		FilmeDAOImpl fd = new FilmeDAOImpl();
		fd.adicionar(a);
		
		
//		 Usuario u = new Usuario();
//		 u.setPrimeiroNome("Rodrigo");
//		 u.setSegundoNome("Santiago");
//		 u.setEmail("rodrigo.cdl1997@gmail.com");
//		 u.setSenha("123456");
//		 new ServicoEmail().servicoEmail(u);
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
