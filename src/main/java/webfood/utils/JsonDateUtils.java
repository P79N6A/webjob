package webfood.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JsonDateUtils extends JsonSerializer<Date>
{

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		jgen.writeString(sdf.format(value));
	}

}
