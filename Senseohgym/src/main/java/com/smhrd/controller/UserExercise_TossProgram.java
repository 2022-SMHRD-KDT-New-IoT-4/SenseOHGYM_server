package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.model.TB_UserExerciseDAO;
import com.smhrd.model.TB_UserExerciseDTO;

public class UserExercise_TossProgram implements Command {
	// 운동정보 보내기
	
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String mb_card = request.getParameter("mb_card"); //카드번호
		
		System.out.println("안드로이드에서 넘어온 값 확인");
		System.out.println(mb_card);

		
		Gson gson = new Gson();
		String UserExserciseJson = ""; // Json을 담을 변수
		TB_UserExerciseDTO dto = new TB_UserExerciseDTO(mb_card);
		TB_UserExerciseDAO dao = new TB_UserExerciseDAO();
		TB_UserExerciseDTO result = dao.selcet(dto);
		System.out.println(result);
		
		
		
		if(result != null) { 
			// 운동정보보내기 성공
			// 안드로이드에 해당 값을 보내줘야함
			System.out.println(dto.getExr_seq()+" 운동정보 보내기 완료 .");
			UserExserciseJson = gson.toJson(result);
			System.out.println(UserExserciseJson);
			//reservationJson = reservationJson.toJson(row);
			// 제이슨형식으로 보내주기
			//response.setContentType("application/json; charset=UTF-8"); 
			response.setContentType("charset=UTF-8");
			// 안드로이드 전송
			response.getWriter().print(UserExserciseJson); 
			
			return "운동정보 전송 완료";
			
		}else {
			// 운동정보전송실패
			System.out.println("전송실패");
			response.setContentType("charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("운동정보를 보내기 위한 값들이 충분하지 않습니다.");
			return "운동정보 전송 재시도 바람";
		}
	}
	
}
