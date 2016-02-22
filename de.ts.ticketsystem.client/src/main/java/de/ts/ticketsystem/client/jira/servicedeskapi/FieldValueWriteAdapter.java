package de.ts.ticketsystem.client.jira.servicedeskapi;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import de.ts.ticketsystem.client.jira.servicedeskapi.objects.FieldValue;

public class FieldValueWriteAdapter extends TypeAdapter<FieldValue> {

	@Override
	public void write(JsonWriter out, FieldValue value) throws IOException {

	      if (value == null) {
	    	  out.nullValue();
	          return;
	        }
	      
	      StringBuilder stringBuilder = new StringBuilder();
	      stringBuilder.append("\""+value.getFieldId());
	      stringBuilder.append("\": \"");
	      stringBuilder.append(value.getValue()+"\"");
	      String shortFieldValueString = stringBuilder.toString();

	      out.jsonValue(shortFieldValueString);
		
	}

	@Override
	public FieldValue read(JsonReader in) throws IOException {

		Gson gson = new Gson();
		
		return gson.fromJson(in, FieldValue.class);
	}

}
