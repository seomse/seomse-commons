import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seomse.crypto.CharMap;
import com.seomse.crypto.HashConfusionString;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author macle
 */
public class CharMapJsonExample {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();

        CharMap charMap = new CharMap(CharMap.makeRandomMap());

        String s = gson.toJson(charMap.getMap(), Map.class);

        System.out.println(s);

        String hKey = HashConfusionString.get("MD5", "1", 32);
        System.out.println(hKey);
        System.out.println(hKey.length());

        String changeKey = charMap.change(hKey);
        System.out.println(changeKey);
        System.out.println(changeKey.length());



    }

}
