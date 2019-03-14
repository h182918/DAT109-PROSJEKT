package login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Random;

public class CookieHandler {

    private static String characters = "qwertyuiopåasdfghjkløæzxcvbnmQWERTYUIOPÅASDFGHJKLØÆZXCVBNM1234567890";

    public CookieHandler() {

    }

    public static Cookie newCookie() {
        char[] chars = characters.toCharArray();
        String generatedString = "";
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            generatedString += chars[random.nextInt(chars.length)];
        }

        Cookie cookie = new Cookie(Common.USER, generatedString);
        return cookie;
    }

    public static Cookie findCookie(HttpServletRequest request) {
        try {
            return Arrays.stream(request.getCookies())
                    .filter(a -> a.getName().equals(Common.USER))
                    .findAny()
                    .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

}
