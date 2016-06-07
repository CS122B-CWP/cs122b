package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniEbay.jdbc.dao.InsertStarDAO;

public class InsertStarServlet extends HttpServlet {
	private static final long serialVersionUID = 12L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String photoUrl = request.getParameter("url");
			String first_name = request.getParameter("fname");
			String last_name = request.getParameter("lname");
			String dob = request.getParameter("dob");
			if (first_name == "")
				first_name = null;
			if (dob == "")
				dob = null;
			if (photoUrl == "")
				photoUrl = null;
			int new_star_id = InsertStarDAO.insert(dob, first_name, last_name, photoUrl);
			if (new_star_id != -1) {
				request.getSession().setAttribute("new_star_id", String.valueOf(new_star_id));
				response.sendRedirect("insert_star_success.jsp");
			} else
				response.sendRedirect("insert_star_fail.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("insert_star.html").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
