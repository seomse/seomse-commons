package com.seomse.jdbc.example.objects;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.seomse.jdbc.objects.JdbcObjects;
import org.postgresql.util.PGobject;

/**
 * test 클래스
 * @author macle
 */
public class PostgresqlJsonInsertSample {
    public static void main(String[] args) {

        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        array.add("Adventure");
        array.add("Arcade");
        object.add("genre", array);
        PostgresqlTokenSample tokenSample = new PostgresqlTokenSample();
        try {

            tokenSample.tag = new PGobject();
            tokenSample.tag.setType("jsonb");
            tokenSample.tag.setValue(gson.toJson(object));
        }catch (Exception e){
            e.printStackTrace();
        }

        JdbcObjects.insert(tokenSample);

    }
}
