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

public class Reservation_afterProgram implements Command {
	// 예약정보 받기
	
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		int rev_seq = Integer.parseInt(request.getParameter("rev_seq")); //예약순번
//		String mb_card = request.getParameter("mb_card"); //카드번호
		String rs_machine = request.getParameter("exer_name"); // 사용기구명
//		int use_time = Integer.parseInt(request.getParameter("use_time")); // 사용시간 
//		int reg_time = Integer.parseInt(request.getParameter("reg_time")); // 접수시간
		
		System.out.println("안드로이드에서 넘어온 값 확인");
		System.out.println(rs_machine);

		
		
		
		
		
		TB_ReservationDTO dto = new TB_ReservationDTO(rs_machine);

		TB_ReservationDAO dao = new TB_ReservationDAO();

		TB_ReservationDTO result = dao.after(dto);
		
		
		
		if(result != null) { 
			// 예약after 성공
			// 안드로이드에 해당 값을 보내줘야함
			System.out.println(dto.getRev_seq()+" 예약after 완료 .");
			//reservationJson = reservationJson.toJson(row);
			// 제이슨형식으로 보내주기
			//response.setContentType("application/json; charset=UTF-8"); 
			//response.setContentType("charset=UTF-8");
			// 안드로이드 전송
			response.getWriter().print(1); 
			return "예약after 완료";
			
		}else {
			//예약실패
			System.out.println("예약after실패");
			response.getWriter().print(0);
			return "예약after 재시도 바람";
		}
	}
	
}
