package tests;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Test;

import login.Common;
import login.CookieHandler;

class CookieHandlerTest {

	 @Test
	    public void makeNewCookie() {

	        Cookie cookie = CookieHandler.newCookie();

	        System.out.println(cookie.getValue());
	        assertEquals(Common.USER,cookie.getName());
	    }

}
