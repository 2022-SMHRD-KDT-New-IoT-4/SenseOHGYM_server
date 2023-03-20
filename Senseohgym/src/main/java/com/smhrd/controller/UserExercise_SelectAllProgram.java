package com.smhrd.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.model.AdminEexerciseDTO;
import com.smhrd.model.TB_UserExerciseDAO;

public class UserExercise_SelectAllProgram implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Gson gson = new Gson();
		
		String gym_name = request.getParameter("gym_name");
		
		TB_UserExerciseDAO dao = new TB_UserExerciseDAO();
		List<AdminEexerciseDTO> result = dao.selectAll(gym_name);
		
		if(result != null) {
			String resultJson = gson.toJson(result);
			System.out.println(resultJson);
			// 제이슨 형식으로 보내겠다 ; 형식
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(resultJson);
			
			return "운동정보 전송완료";
		}else {
			return "운동정보 없음";
		}
		
	}

}
