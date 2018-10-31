package servicos;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import entity.Entretenimento;
import entity.Filme;
import entity.Game;
import entity.Serie;

public class ServicoOMDB {

	public Entretenimento servicoEntretenimento(Entretenimento e) {
		Entretenimento novo = null;
		if (!(e instanceof Game)) {
			Client cliente = new Client();
			String uri = null;
			try {
				uri = criaURI(e);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			WebResource resource = cliente.resource(uri);
			GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
			builder.registerTypeAdapter(Integer.class, new MetacriticDeserializer());
			builder.registerTypeAdapter(Date.class, new DateDeserializer());

			Gson gson = builder.create();

			String json = resource.get(String.class);

			if (e instanceof Filme) {
				novo = gson.fromJson(json, Filme.class);
			} else if (e instanceof Serie) {
				novo = gson.fromJson(json, Serie.class);
			}
			novo.setMetacritic(gson.fromJson(json, Integer.class));
			novo.setDataLancamento(gson.fromJson(json, Date.class));
			if (novo.getNomeOriginal() != null) {
				return novo;
			} else
				return e;
		}else {
			return e;
		}
	}


	private String criaURI(Entretenimento e) throws UnsupportedEncodingException {
		String uri = null;
		if (!(e instanceof Game)) {

			uri = "http://www.omdbapi.com/?t=" + URLEncoder.encode(e.getNomeOriginal(), "UTF-8");
			if (e instanceof Filme) {
				uri += "&type=movie&";
			} else if (e instanceof Serie) {
				uri += "&type=series&";
			}
			uri += "plot=full&apikey=7fd1758d";

		} 
		return uri;
	}
}

// outra api = https://moviegraph.io/#usage
