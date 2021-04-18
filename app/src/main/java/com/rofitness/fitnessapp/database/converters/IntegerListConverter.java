package com.rofitness.fitnessapp.database.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegerListConverter {
    private static String SEPARATOR = ",";

    public static String fromIntegerListToString(List<Integer> list) {
        if (list == null) {
            return null;
        }
        return concateIntegerListToString(list);
    }

    public static List<Integer> stringToIntegerList(String str) {
        if (str == null) {
            return null;
        }
        List<String> asList = Arrays.asList(str.split(SEPARATOR));
        ArrayList arrayList = new ArrayList();
        for (String str2 : asList) {
            try {
                arrayList.add(Integer.valueOf(Integer.parseInt(str2)));
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    private static String concateIntegerListToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (Integer num : list) {
            sb.append(num);
            sb.append(SEPARATOR);
        }
        String sb2 = sb.toString();
        if (sb2.length() == 0) {
            return "";
        }
        return sb2.substring(0, sb2.length() - SEPARATOR.length());
    }
}
