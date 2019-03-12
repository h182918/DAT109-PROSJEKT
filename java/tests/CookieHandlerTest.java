package tests;

import db.DbHandler;
import login.Common;
import login.CookieHandler;
import org.junit.Test;

import javax.servlet.http.Cookie;

import static org.junit.Assert.*;
public class CookieHandlerTest {

    @Test
    public void makeNewCookie() {

        Cookie cookie = CookieHandler.newCookie();

        System.out.println(cookie.getValue());
        assertEquals(Common.USER,cookie.getName());
    }
}
