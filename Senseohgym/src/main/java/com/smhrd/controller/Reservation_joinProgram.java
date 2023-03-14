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

public class Reservation_joinProgram implements Command {
	// 예약정보 받기
	
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		int rev_seq = Integer.parseInt(request.getParameter("rev_seq")); //예약순번
		String mb_card = request.getParameter("mb_card"); //카드번호
		String rs_machine = request.getParameter("exername"); // 사용기구명
		int use_time = Integer.parseInt(request.getParameter("etUseTime")); // 사용시간 
//		int reg_time = Integer.parseInt(request.getParameter("reg_time")); // 접수시간
		
		System.out.println("안드로이드에서 넘어온 값 확인");
		System.out.println(mb_card);
		System.out.println(rs_machine);
		System.out.println(use_time);
		
		
		
		Gson gson = new Gson();
		String reservationJson = ""; // Json을 담을 변수
		
		TB_ReservationDTO dto = new TB_ReservationDTO(mb_card, rs_machine, use_time);

		TB_ReservationDAO dao = new TB_ReservationDAO();

		int row = dao.reservation(dto);
		
		
		
		if(row > 0) { 
			// 예약하기 성공
			// 안드로이드에 해당 값을 보내줘야함
			System.out.println(dto.getRev_seq()+" 예약완료 .");
			//reservationJson = reservationJson.toJson(row);
			// 제이슨형식으로 보내주기
			//response.setContentType("application/json; charset=UTF-8"); 
			response.setContentType("charset=UTF-8");
			// 안드로이드 전송
			response.getWriter().print(Integer.toString(row)); 
			
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
