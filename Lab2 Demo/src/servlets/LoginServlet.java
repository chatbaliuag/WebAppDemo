package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LoginBean;
import dao.LoginDao;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private LoginDao loginDao;
	
	public void init() {
		loginDao = new LoginDao();
	}
  	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUSERNAME(username);
		loginBean.setPASSWORD(password);
		
		if(loginDao.validate(loginBean)) {
			response.sendRedirect("Success.jsp");
		} else {
			response.sendRedirect("Error.jsp");
		}
	}

}
