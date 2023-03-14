package com.smhrd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.smhrd.model.TB_MemberDAO;
import com.smhrd.model.TB_MemberDTO;

public class Member_JoinProgram implements Command {
	// 회원가입

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mb_name = request.getParameter("mb_name");
		String mb_card = request.getParameter("mb_card");
		String mb_birthdate = request.getParameter("mb_birthdate");
		String mb_gender = request.getParameter("mb_gender");
		String gym_name = request.getParameter("gym_name");
		String mb_type1 = request.getParameter("mb_type");
		char mb_type = mb_type1.charAt(0); // mb_type 형변환
		
//		String mb_joindate = request.getParameter("mb_joindate");
		
		Gson gson = new Gson();
		String adminJson = ""; // Json을 담을 변수
		
		TB_MemberDTO dto = new TB_MemberDTO(mb_name, mb_card, mb_birthdate, mb_gender, gym_name, mb_type,null);

		TB_MemberDAO dao = new TB_MemberDAO();

		int row = dao.join(dto);
		
		
		
		if(row > 0) { 
			// 회원등록 성공
			// 안드로이드에 해당 값을 보내줘야함
			System.out.println(dto.getMb_name()+" 회원등록완료 .");
			//adminJson = adminJson.toJson(row);
			// 제이슨형식으로 보내주기
			//response.setContentType("application/json; charset=UTF-8"); 
			response.setContentType("charset=UTF-8");
			// 안드로이드 전송
			response.getWriter().print(Integer.toString(row)); 
			
			return "회원등록완료";
			
		}else {
			//회원등록실패
			System.out.println("회원등록실패");
			response.setContentType("charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("회원등록에 필요한 모든 값을 입력하세요");
			return "회원등록 재시도 바람";
		}
	}

	
}
