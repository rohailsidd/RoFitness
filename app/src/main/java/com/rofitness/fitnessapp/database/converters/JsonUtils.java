package com.rofitness.fitnessapp.database.converters;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
    public static JSONObject mapToJson(Map<?, ?> map) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (str != null) {
                try {
                    jSONObject.put(str, wrap(entry.getValue()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                throw new NullPointerException("key == null");
            }
        }
        return jSONObject;
    }

    public static JSONArray collectionToJson(Collection collection) {
        JSONArray jSONArray = new JSONArray();
        if (collection != null) {
            for (Object obj : collection) {
                jSONArray.put(wrap(obj));
            }
        }
        return jSONArray;
    }

    public static JSONArray arrayToJson(Object obj) throws JSONException {
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < length; i++) {
                jSONArray.put(wrap(Array.get(obj, i)));
            }
            return jSONArray;
        }
        throw new JSONException("Not a primitive data: " + obj.getClass());
    }

    private static Object wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject)) {
            return obj;
        }
        try {
            if (obj instanceof Collection) {
                return collectionToJson((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                return arrayToJson(obj);
            }
            if (obj instanceof Map) {
                return mapToJson((Map) obj);
            }
            if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short)) {
                return obj;
            }
            if (obj instanceof String) {
                return obj;
            }
            if (obj.getClass().getPackage().getName().startsWith("java.")) {
                return obj.toString();
            }
            return null;
        } catch (Exception unused) {
        }
        return obj;
    }

    public static Map<String, String> jsonToMap(String str) throws JSONException {
        HashMap hashMap = new HashMap();
        JSONObject jSONObject = new JSONObject(str);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.getString(next));
        }
        return hashMap;
    }

    private static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                obj = toList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            hashMap.put(next, obj);
        }
        return hashMap;
    }

    private static List<Object> toList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = toList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }
}
