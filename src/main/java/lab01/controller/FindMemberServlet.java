package lab01.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import lab01.model.MemberBean;
import lab01.service.MemberService;


@WebServlet("/lab01/findMember.do")
public class FindMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String key = request.getParameter("key");//注意大小寫
		int id = Integer.parseInt(key);
		
		WebApplicationContext webApplicationContext = 
				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberService ms = webApplicationContext.getBean(MemberService.class);
		MemberBean mb = ms.findById(id);
		
		request.setAttribute("mb", mb);
		
		RequestDispatcher rd = request.getRequestDispatcher("/lab01/updateMember.jsp");
		rd.forward(request, response);
		
	}
}
