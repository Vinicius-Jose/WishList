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

public class MetacriticDeserializer implements JsonDeserializer<Integer> {

	@Override
	public Integer deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		JsonObject jsonObject = element.getAsJsonObject();
		JsonElement name = jsonObject.get("Metascore");
		int i;
		
		try {
			i  = Integer.parseInt(name.getAsString());
		}catch(Exception e) {
			i = 0;
		}
		return i;
	}

}
