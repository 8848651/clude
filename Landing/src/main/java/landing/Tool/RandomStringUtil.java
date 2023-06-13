package landing.Tool;

import java.util.Random;

public class RandomStringUtil {

    public static String RandomString() {
        Random random = new Random();
        int length=random.nextInt(5)+1;
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}