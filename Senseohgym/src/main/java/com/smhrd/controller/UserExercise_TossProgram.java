package com.smhrd.controller;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.model.TB_UserExerciseDAO;
import com.smhrd.model.TB_UserExerciseDTO;

// 개인 운동정보 확인
public class UserExercise_TossProgram implements Command {
	
	List<TB_UserExerciseDTO> result;
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//카드번호
		String mb_card = request.getParameter("mb_card");
		// 운동시간확인 버튼과 기구사용횟수 버튼을 구별하기 위한 변수
		// 운동시간확인 버튼 누르면 btn_type의 값 0이 들어옴, 기구사용횟수 버튼의 btn_type = 1 들어옴
		String btn_type = request.getParameter("btn_type");
		
		System.out.println("안드로이드에서 넘어온 값 확인");
		System.out.println(mb_card);
		System.out.println(btn_type);
		
		Gson gson = new Gson();
		String UserExserciseJson = ""; // Json을 담을 변수
		TB_UserExerciseDTO dto = new TB_UserExerciseDTO(mb_card);
		TB_UserExerciseDAO dao = new TB_UserExerciseDAO();
		
		switch (btn_type) {
		case "0": {
			result = dao.select(dto);
			break;
		}
		case "1" : {
			result = dao.select1(dto);
			break;
		}
		default:
			result = null;
		}
		
		//제대로 값이 담겼는 지 확인
		//운동시간확인 버튼을 누르면 select함수 쿼리문의 값이 출력
		//기구사용횟수 버튼을 누르면 select1함수 쿼리문의 값이 출력
		System.out.println(result); 
		

		if(result != null) {
			System.out.println(dto.getExr_seq()+" 운동정보 보내기 완료 .");
			//gson형태로 담아주기
			UserExserciseJson = gson.toJson(result);
			System.out.println(UserExserciseJson);
			// 제이슨형식으로 보내주기
			response.setContentType("application/json; charset=UTF-8");
			// 안드로이드 전송
			response.getWriter().print(UserExserciseJson); 
			return "운동정보 전송 완료";
			
		}else {
			// 운동정보전송실패
			System.out.println("전송실패");
			response.setContentType("charset=UTF-8");
			response.getWriter().print("운동정보를 보내기 위한 값들이 충분하지 않습니다.");
			return "운동정보 전송 재시도 바람";
		}
	}
	
}
