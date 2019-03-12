package login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpCookie;
import java.nio.charset.Charset;
import java.util.Random;

public class CookieHandler {

    public CookieHandler() {
    }

    public Cookie newCookie() {
        byte[] array = new byte[255]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        Cookie cookie = new Cookie("expoUser", generatedString);
        return cookie;
    }


}
