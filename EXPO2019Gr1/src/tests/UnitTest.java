package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import login.Common;
import login.LoginUtil;

class UnitTest {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;
	
	@Before
	public void setUp() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		session = new MockHttpSession();
		request.setSession(session);
	}

	@Test
	public void testLoginUtil() {
		session.setAttribute("admin", Common.ADMIN);
		boolean test = LoginUtil.adminIsLoggedIn(request);
		assertTrue(test);
	}


}
