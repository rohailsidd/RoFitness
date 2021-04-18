package com.rofitness.fitnessapp.database.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

public class RegularPlanMapConverter {
    private static final String COMMA_SEPARATOR = ",";
    private static final String TAG = "MapConverter";

    public static String getMapAsString(Map<String, List<Long>> map) {
        if (map.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, List<Long>> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), fromLongListToString(entry.getValue()));
        }
        String jSONObject = JsonUtils.mapToJson(hashMap).toString();
        return jSONObject.length() == 0 ? "" : jSONObject;
    }

    public static Map<String, List<Long>> getMapFromString(String str) {
        try {
            Map<String, String> jsonToMap = JsonUtils.jsonToMap(str);
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, String> entry : jsonToMap.entrySet()) {
                hashMap.put(entry.getKey(), fromStringToLongList(entry.getValue()));
            }
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String fromLongListToString(List<Long> list) {
        if (list == null) {
            return null;
        }
        return concatLongListToString(list);
    }

    private static List<Long> fromStringToLongList(String str) {
        if (str == null) {
            return null;
        }
        List<String> asList = Arrays.asList(str.split(COMMA_SEPARATOR));
        ArrayList arrayList = new ArrayList();
        for (String str2 : asList) {
            try {
                arrayList.add(Long.valueOf(Long.parseLong(str2)));
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    private static String concatLongListToString(List<Long> list) {
        StringBuilder sb = new StringBuilder();
        for (Long l : list) {
            sb.append(l);
            sb.append(COMMA_SEPARATOR);
        }
        String sb2 = sb.toString();
        if (sb2.length() == 0) {
            return "";
        }
        return sb2.substring(0, sb2.length() - 1);
    }
}
