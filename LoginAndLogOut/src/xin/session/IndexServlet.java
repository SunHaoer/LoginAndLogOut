package xin.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");		// 防止中文乱码
		HttpSession session = request.getSession();				// 创建或获取保存用户信息的session
		User user = (User)session.getAttribute("user");			// 关联"user"和当前session
		if(user == null) {
			response.getWriter().println("还没登录<a href='/LoginAndLogOut/login.jsp'>登录</a>");
		} else {
			response.getWriter().println("已登录, 欢迎" + user.getUsername());
			response.getWriter().println("<a href='/LoginAndLogOut/LogoutServlet'>退出</a>");
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(60 * 30);
			cookie.setPath("/LoginAndLogOut");
			response.addCookie(cookie);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
