package com.lincoln.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Object2Json {
	public static JSONArray list2Json (String key, List list) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("users", list);
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonObject);
		
		return jsonArray;
	}
}
