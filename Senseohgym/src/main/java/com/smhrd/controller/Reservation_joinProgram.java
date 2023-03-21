package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.model.TB_MemberDAO;
import com.smhrd.model.TB_MemberDTO;
import com.smhrd.model.TB_ReservationDAO;
import com.smhrd.model.TB_ReservationDTO;

// 예약하기 (회원)
public class Reservation_joinProgram implements Command {
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mb_card = request.getParameter("mb_card"); //카드번호
		String rs_machine = request.getParameter("rs_machine"); // 사용기구명
		int use_time = Integer.parseInt(request.getParameter("use_time")); // 사용시간 
		
		System.out.println("안드로이드에서 넘어온 값 확인");
		System.out.println(mb_card);
		System.out.println(rs_machine);
		System.out.println(use_time);
		
		TB_ReservationDTO dto = new TB_ReservationDTO(mb_card, rs_machine, use_time);

		TB_ReservationDAO dao = new TB_ReservationDAO();

		int row = dao.reservation(dto);
		
		if(row > 0) { 
			// 예약하기 성공
			// 안드로이드에 해당 값을 보내줘야함
			System.out.println(dto.getRev_seq()+" 예약완료 .");
			// 안드로이드 전송
			response.getWriter().print(row); 
			return "예약 완료";
		}else {
			//예약실패
			System.out.println("예약실패");
			response.setContentType("charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("예약을 하기 위한 값들이 충분하지 않습니다.");
			return "예약 재시도 바람";
		}
	}
	
}
