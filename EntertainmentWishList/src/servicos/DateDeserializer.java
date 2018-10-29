package servicos;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class DateDeserializer implements JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject jsonObject = element.getAsJsonObject();
		JsonElement name = jsonObject.get("Released");
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy",new Locale("en"));
		Date sql = null;
		try {
			java.util.Date data = null;
			data = sdf.parse(name.getAsString());
			 sql = new Date(data.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sql;
	}

}
