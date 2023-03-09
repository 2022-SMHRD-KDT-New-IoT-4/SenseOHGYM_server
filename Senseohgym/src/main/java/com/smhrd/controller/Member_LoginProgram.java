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
		
		
		String name = (String)request.getParameter("mb_name");
		String card = (String)request.getParameter("mb_card");
		System.out.println();
		
		Gson gson = new Gson();
		String memJson = "";

		System.out.println("name: "+name+" card: "+card);
		
		TB_MemberDTO dto = new TB_MemberDTO();
		dto.setMb_name(name);
		dto.setMb_card(card);
		
		TB_MemberDAO dao = new TB_MemberDAO();
		TB_MemberDTO result = dao.Login(dto);
		
		if(result != null) { 
			// 로그인 성공
			// 안드로이드에 해당 값을 보내줘야함
			System.out.println(dto.getMb_name()+" 회원이 존재합니다.");
			memJson = gson.toJson(result);
			System.out.println(memJson);
			return memJson;
			
		}else {
			System.out.println("존재하지 않는 회원입니다.");
			return "회원등록필요";
		}
		
		
	}

}