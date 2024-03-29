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
import com.smhrd.controller.Member_DeleteProgram;
import com.smhrd.controller.Member_JoinProgram;
import com.smhrd.controller.Member_LoginProgram;
import com.smhrd.controller.Member_SelectAllProgram;
import com.smhrd.controller.Member_UpdateProgram;
import com.smhrd.controller.Reservation_afterProgram;
import com.smhrd.controller.Reservation_joinProgram;
import com.smhrd.controller.UserExercise_SelectAllProgram;
import com.smhrd.controller.UserExercise_TossProgram;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Command> map = null;
	
	public void init() throws ServletException{
		map = new HashMap<>();
		
		// TB_MEMBER
		map.put("Member_Login.do", new Member_LoginProgram());
		map.put("Member_Join.do", new Member_JoinProgram());
		map.put("Member_SelectAll.do", new Member_SelectAllProgram());
		map.put("Member_Update.do", new Member_UpdateProgram());
		map.put("Member_Delete.do", new Member_DeleteProgram());
		
		// TB_RESERVATION
		map.put("Reservation_Join.do", new Reservation_joinProgram());
		map.put("Reservation_after.do", new Reservation_afterProgram());
		
						
		// TB_USEREXERCISE
		map.put("UserExercise_Toss.do", new UserExercise_TossProgram());
		map.put("Admin_ExerciseCheck.do", new UserExercise_SelectAllProgram());
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
		System.out.println("결과 : "+result);
		
	}
}
