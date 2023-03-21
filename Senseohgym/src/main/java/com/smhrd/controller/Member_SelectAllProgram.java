package com.smhrd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.model.TB_MemberDAO;
import com.smhrd.model.TB_MemberDTO;

// 회원 전체 목록 확인(관리자)
public class Member_SelectAllProgram implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Gson gson = new Gson();
		
		String gym_name = (String)request.getParameter("gym_name");
		System.out.println("gym_name : " + gym_name);
		
		TB_MemberDAO dao = new TB_MemberDAO();
		
		List<TB_MemberDTO> result = dao.selectAll(gym_name);
		
		if(result != null) {
			String resultJson = gson.toJson(result);
			System.out.println(resultJson);
			
			response.setContentType("application/json; charset=UTF-8");
			// 제이슨 형식으로 보내겠다 ; 형식
			response.getWriter().print(resultJson);
			return "회원정보확인";
		}else {
			return "";
		}
		
	}

}
