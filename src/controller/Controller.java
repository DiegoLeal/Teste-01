package controller;

import org.json.JSONObject;

public interface Controller<T> {

	JSONObject generateJsonObject (T object);
	
	T generateObjectFromJson ( JSONObject jsonObject);
	
	JSONObject create (JSONObject json);
	
	JSONObject show (Long id);
}