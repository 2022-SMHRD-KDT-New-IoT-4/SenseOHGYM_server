package com.smhrd.frontcontroller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.smhrd.controller.Command;
import com.smhrd.controller.Member_LoginProgram;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Command> map = null;
	
	public void init() throws ServletException{
		map = new HashMap<>();
		
		// TB_MEMBER
		map.put("Member_Login.do", new Member_LoginProgram());
		
	}
    
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		System.out.println("요청 uri: "+uri);
		String path = request.getContextPath();
		String finalUrl = uri.substring(path.length()+1);
		System.out.println("잘라낸 url: "+finalUrl);
		
		Command com = null;
		request.setCharacterEncoding("UTF-8");
		com = map.get(finalUrl);
		String result = com.execute(request, response);
		System.out.println("rdPath : "+result);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
		
	}
}
