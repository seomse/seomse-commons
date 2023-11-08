import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seomse.crypto.CharMap;

import java.util.Map;

/**
 * @author macle
 */
public class CharMapJsonExample {

    public static void main(String[] args) {
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();

        CharMap charMap = new CharMap(CharMap.makeRandomMap());

        String s = gson.toJson(charMap.getMap(), Map.class);

        System.out.println(s);




    }

}
