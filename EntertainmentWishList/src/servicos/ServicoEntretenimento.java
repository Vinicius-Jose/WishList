package servicos;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import entity.Entretenimento;
import entity.Filme;
import entity.Game;
import entity.Serie;

public class ServicoEntretenimento {

	public Entretenimento servicoEntretenimento(Entretenimento e) {
		Entretenimento novo = null;
		if (!(e instanceof Game)) {

			try {

				GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
				builder.registerTypeAdapter(HashMap.class, new OMDBDeserializer());
				Gson gson = builder.create();

				String json = buscaJSON(criaURIOMDB(e));

				if (e instanceof Filme) {
					novo = gson.fromJson(json, Filme.class);
				} else if (e instanceof Serie) {
					novo = gson.fromJson(json, Serie.class);
				}

				if (novo.getNomeOriginal() != null) {
					HashMap<String, Object> dados = gson.fromJson(json, HashMap.class);
					Date sqlDate = (Date) dados.get("dataLancamento");
					if (sqlDate == null)
						sqlDate = e.getDataLancamento();
					
					novo.setDataLancamento(sqlDate);
					novo.setMetacritic((int) dados.get("metascore"));
					if(e instanceof Serie)
						((Serie) novo).setTemporadas((int) dados.get("temporadas"));
					if(e instanceof Filme)
						((Filme) novo).setImdb(((double) dados.get("imdb")));
					builder.registerTypeAdapter(HashMap.class, new TMDBDeserializer());
					gson = builder.create();
					json = buscaJSON(criaURITMDB(novo));
					dados = gson.fromJson(json, HashMap.class);
					if (!dados.isEmpty()) {
						novo.setSinopse((String) dados.get("sinopse"));
						novo.setImagemFundo((String) dados.get("background"));
						if (e instanceof Filme) {
							((Filme) novo).setNomePortugues((String) dados.get("nomePt"));
						} else if (e instanceof Serie) {
							((Serie) novo).setNomePortugues((String) dados.get("nomePt"));
							
						}
					}
					return novo;
				}

			} catch (UnsupportedEncodingException e1) {

				e1.printStackTrace();
			}
		}

		return e;

	}

	private String criaURIOMDB(Entretenimento e) throws UnsupportedEncodingException {
		String uri = null;

		uri = "http://www.omdbapi.com/?t=" + URLEncoder.encode(e.getNomeOriginal(), "UTF-8");
		if (e instanceof Filme) {
			uri += "&type=movie&";
		} else if (e instanceof Serie) {
			uri += "&type=series&";
		}
		if (e.getDataLancamento() != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(e.getDataLancamento());
			uri += "y=" + c.get(Calendar.YEAR);
		}
		uri += "&plot=full&apikey=7fd1758d";

		return uri;
	}

	private String criaURITMDB(Entretenimento e) throws UnsupportedEncodingException {
		String uri = null;
		String tipo = null;
		if (e instanceof Filme) {
			tipo = "movie";
		} else if (e instanceof Serie) {
			tipo = "tv";
		}
		Calendar c = Calendar.getInstance();
		c.setTime(e.getDataLancamento());
		uri = "https://api.themoviedb.org/3/search/" + tipo
				+ "?api_key=a5e03bab2b046c01727a5cc5699556bd&language=pt-BR&query="
				+ URLEncoder.encode(e.getNomeOriginal(), "UTF-8") + "&page=1&year=" + c.get(Calendar.YEAR);

		return uri;
	}

	private String buscaJSON(String uri) {
		Client cliente = new Client();
		WebResource resource = cliente.resource(uri);
		String json = resource.get(String.class);
		return json;

	}
}
