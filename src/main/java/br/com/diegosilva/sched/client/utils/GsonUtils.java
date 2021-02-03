package br.com.diegosilva.sched.client.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class GsonUtils {

    public static Map<String, String> getMapFromJson(String jsonString) {
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        return new Gson().fromJson(jsonString, type);
    }

    public static <T> T fromJson(String json, Class<T> tclass) {
        return new GsonBuilder()
                .setDateFormat("dd/MM/yyyy HH:mm:ss")
                .create()
                .fromJson(json, tclass);
    }

    public static <T> T fromJsonWithDateTimezone(String json, Class<T> tclass) {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create()
                .fromJson(json, tclass);
    }

    public static String toJson(Object obj) {
        if (obj == null) return null;
        return new GsonBuilder()
                .setDateFormat("dd/MM/yyyy HH:mm:ss")
                .setPrettyPrinting().create().toJson(obj);
    }

    public static JsonObject toJsonObject(String json) {
        return new JsonParser().parse(json).getAsJsonObject();
    }

}
