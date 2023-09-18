import com.seomse.crypto.LoginCrypto;

/**
 * @author macle
 */
public class LoginDataMake {
    public static void main(String[] args) {

        String [] infos = LoginCrypto.encryption("id", "password");
        System.out.println(infos[0]);
        System.out.println(infos[1]);

        infos = LoginCrypto.decryption(infos[0], infos[1]);

        System.out.println(infos[0]);
        System.out.println(infos[1]);

    }
}
