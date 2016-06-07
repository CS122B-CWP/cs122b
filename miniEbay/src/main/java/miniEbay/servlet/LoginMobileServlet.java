package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import miniEbay.jdbc.dao.LoginDAO;
import miniEbay.object.LoginInfo;

public class LoginMobileServlet extends HttpServlet {
	private static final long serialVersionUID = 16L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			LoginInfo user = LoginDAO.validate(request.getParameter("username"), request.getParameter("password"));
			// System.out.println(login_name);
			// System.out.println(request.getHeader("referer"));
			/*
			 * if (request.getSession().getAttribute("origin_url") == null) {
			 * request.getSession().setAttribute("origin_url",
			 * request.getHeader("referer")); }
			 */

			JSONObject login_result = new JSONObject();

			if (user != null) {
				login_result.put("login_result", true);
				login_result.put("login_name", user.getLname());
			} else {
				login_result.put("login_result", false);
			}

			response.getWriter().write(login_result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
