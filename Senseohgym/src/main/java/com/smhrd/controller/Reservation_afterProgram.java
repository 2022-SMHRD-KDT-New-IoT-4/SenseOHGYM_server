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

// 예약가능 여부 확인
public class Reservation_afterProgram implements Command {
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String rs_machine = request.getParameter("exer_name"); // 사용기구명
		
		System.out.println("안드로이드에서 넘어온 값 확인");
		System.out.println(rs_machine);

		TB_ReservationDTO dto = new TB_ReservationDTO(rs_machine);
		
		TB_ReservationDAO dao = new TB_ReservationDAO();

		TB_ReservationDTO result = dao.after(dto);
		
		if(result != null) { 
			// 예약정보가 있음 예약불가
			// 안드로이드에 해당 값을 보내줘야함
			System.out.println(dto.getRs_machine()+" 예약정보가 있음.");
			// 안드로이드 전송
			response.getWriter().print(1); 
			return "예약정보가 있음";
			
		}else {
			//예약실패
			System.out.println("예약정보가 없음");
			response.getWriter().print(0);
			return "예약정보가 없음";
		}
	}
	
}
