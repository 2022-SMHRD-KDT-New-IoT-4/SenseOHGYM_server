package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.smhrd.model.TB_MemberDAO;
import com.smhrd.model.TB_MemberDTO;

public class Member_LoginProgram implements Command{
	// 회원 로그인
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 안드로이드에서 보낸 값 빼오기
		String name = (String)request.getParameter("mb_name");
		String card = (String)request.getParameter("mb_card");
		System.out.println();
		
		// Json 변환을 위한 gson 선언
		Gson gson = new Gson();
		String memJson = ""; // Json을 담아줄 변수

		System.out.println("name: "+name+" card: "+card);
		
		TB_MemberDTO dto = new TB_MemberDTO(name, card);

		
		TB_MemberDAO dao = new TB_MemberDAO();
		TB_MemberDTO result = dao.Login(dto);
		
		if(result != null) { 
			// 로그인 성공
			// 안드로이드에 해당 값을 보내줘야함
			System.out.println(dto.getMb_name()+" 회원이 존재합니다.");
			memJson = gson.toJson(result);
			System.out.println(memJson);
			// 제이슨 형식으로 보내겠다 ; 형식
			response.setContentType("application/json; charset=UTF-8");
			// 안드로이드에 결과값 보내주기
			response.getWriter().print(memJson);
			return "로그인 성공";
			
		}else {
			// 로그인 실패
			// 안드로이드에 회원등록필요 문자를 결과값으로 보내줌(멘트는 바꿔도 됨)
			System.out.println("존재하지 않는 회원입니다.");
			response.setContentType("charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("회원등록필요");
			return "회원등록필요";
		}
		
		
	}

}