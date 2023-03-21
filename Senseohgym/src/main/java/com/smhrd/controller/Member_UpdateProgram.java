package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.model.TB_MemberDAO;
import com.smhrd.model.TB_MemberDTO;

// 회원정보 업데이트(관리자)
public class Member_UpdateProgram implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mb_name = request.getParameter("mb_name");
		String mb_card = request.getParameter("mb_card");
		String mb_birthdate = request.getParameter("mb_birthdate");
		String mb_gender = request.getParameter("mb_gender");
		String gym_name = request.getParameter("gym_name");
		System.out.println("수정중>>"+mb_name);
		
		TB_MemberDTO dto = new TB_MemberDTO();
		dto.setMb_name(mb_name);
		dto.setMb_card(mb_card);
		dto.setMb_birthdate(mb_birthdate);
		dto.setMb_gender(mb_gender);
		dto.setGym_name(gym_name);
		
		TB_MemberDAO dao = new TB_MemberDAO();
		int row = 0;
		row = dao.update(dto);
		System.out.println("회원정보수정 결과 : "+row);
		
		if(row > 0){
			// 회원정보 수정 성공
			response.getWriter().print(row);
			
			return "회원정보 수정 성공";
		}else {
			// 회원정보 수정 실패
			response.getWriter().print(row);
			return "회원정보 수정 실패";
		}
		
		
	}

}
