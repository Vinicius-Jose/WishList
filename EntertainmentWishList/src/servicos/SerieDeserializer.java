package servicos;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import entity.Serie;

public class SerieDeserializer implements JsonDeserializer<Serie> {

	@Override
	public Serie deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		JsonObject jsonObject = element.getAsJsonObject();
		JsonElement name = jsonObject.get("Title");
		Serie s = new Serie();
		s.setNomeOriginal(name.getAsString());
		name = jsonObject.get("Poster");
		s.setPoster(name.getAsString());
		name = jsonObject.get("totalSeasons");
		s.setTemporadas(name.getAsInt());
		
		
		try {
			name = jsonObject.get("Metascore");
			int i  = Integer.parseInt(name.getAsString());
		}catch(Exception e) {
			s.setMetacritic(0);
		}
		return s;
	}

}
