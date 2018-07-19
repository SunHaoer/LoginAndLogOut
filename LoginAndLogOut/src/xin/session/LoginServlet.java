package xin.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkCode = request.getParameter("check_code");
		String savedCode = (String)request.getSession().getAttribute("check_code");
		PrintWriter pw = response.getWriter();
		if( ("admin").equals(username) && ("123456").equals(password) && (checkCode.equals(savedCode)) ) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/LoginAndLogOut/IndexServlet");
		} else if(checkCode.equals(savedCode)) {
			pw.write("登录失败");
			response.sendRedirect("/LoginAndLogOut/login.jsp");
		} else {
			pw.write("验证码错误");
			response.sendRedirect("/LoginAndLogOut/login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
