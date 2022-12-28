package lab01.controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/lab01/queryMember.do")
public class QueryAllMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext webApplicationContext = 
				WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		MemberService ms = webApplicationContext.getBean(MemberService.class);
		List <MemberBean>list = ms.findAll();
		request.setAttribute("allMembers", list);
		RequestDispatcher rd = request.getRequestDispatcher("/lab01/showMembers.jsp");
		rd.forward(request, response);	
	}

}
