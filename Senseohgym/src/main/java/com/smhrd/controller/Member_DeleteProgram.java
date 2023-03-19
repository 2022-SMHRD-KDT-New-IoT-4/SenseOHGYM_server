package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smhrd.model.TB_MemberDAO;
import com.smhrd.model.TB_MemberDTO;

public class Member_DeleteProgram implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mb_name = request.getParameter("mb_name");
		String mb_card = request.getParameter("mb_card");
		String gym_name = request.getParameter("gym_name");

		TB_MemberDTO dto = new TB_MemberDTO();
		dto.setMb_name(mb_name);
		dto.setMb_card(mb_card);
		dto.setGym_name(gym_name);
		
		TB_MemberDAO dao = new TB_MemberDAO();
		int row = 0;
		row = dao.delete(dto);
		
		if(row > 0) {
			// 회원정보 삭제 성공
			response.getWriter().print(row);
			return "회원정보 삭제 성공";
		}else {
			// 회원정보 삭제 실패
			response.getWriter().print(row);
			return "회원정보 삭제 실패";
		}
		
	}

}
