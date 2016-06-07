package project5.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project5.jdbc.dao.EmployeeLoginDAO;
import project5.object.LoginInfo;
import project5.recaptcha.VerifyUtils;

public class EmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 10L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
			// System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
			// Verify CAPTCHA.
			boolean valid = VerifyUtils.verify(gRecaptchaResponse);
			// System.out.println(valid);

			if (!valid) {
				response.sendRedirect("recaptchafail.html");
				return;
			}
			

			LoginInfo employee = EmployeeLoginDAO.validate(request.getParameter("username"),
					request.getParameter("password"));

			if (employee != null) {

				HttpSession session = request.getSession();
				session.setAttribute("e_login_name", employee.getLname());

				response.sendRedirect("dashapp/main.html");
			} else {
				request.getRequestDispatcher("employee_login.html").forward(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
