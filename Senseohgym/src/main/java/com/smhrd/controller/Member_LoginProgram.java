package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.smhrd.model.TB_MemberDAO;
import com.smhrd.model.TB_MemberDTO;

// 회원 로그인
public class Member_LoginProgram implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String gym_name = (String)request.getParameter("gym_name");
		String mb_name = (String)request.getParameter("mb_name");
		String mb_card = (String)request.getParameter("mb_card");
		
		Gson gson = new Gson();
		String memJson = "";

		System.out.println("gym_name: "+gym_name+" name: "+mb_name+" card: "+mb_card);
		
		TB_MemberDTO dto = new TB_MemberDTO();
		dto.setGym_name(gym_name);
		dto.setMb_name(mb_name);
		dto.setMb_card(mb_card);
		
		TB_MemberDAO dao = new TB_MemberDAO();
		TB_MemberDTO result = dao.Login(dto);
		
		if(result != null) { 
			// 로그인 성공
			// 안드로이드에 해당 값을 보내줘야함
			System.out.println(dto.getMb_name()+" 회원이 존재합니다.");
			memJson = gson.toJson(result);
			System.out.println(memJson);
			response.setContentType("application/json; charset=UTF-8");
			// 제이슨 형식으로 보내겠다 ; 형식
			response.getWriter().print(memJson);
			return "로그인 성공";
			
		}else {
			System.out.println("존재하지 않는 회원입니다.");
			response.setContentType("charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("회원등록필요");
			return "회원등록필요";
		}
		
	}

}