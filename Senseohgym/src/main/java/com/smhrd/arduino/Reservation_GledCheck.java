package com.smhrd.arduino;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smhrd.model.TB_MemberDAO;
import com.smhrd.model.TB_ReservationDAO;
import com.smhrd.model.TB_ReservationDTO;
import com.smhrd.model.TB_UserExerciseDAO;
import com.smhrd.model.TB_UserExerciseDTO;

// 제품에 카드를 태그했을 때 기구 사용 가능 여부 판단 및 보내준 운동정보 저장
@WebServlet("/GledCheck")
public class Reservation_GledCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());

		Gson gson = new Gson();

		// 카드 아이디
		String cardId = request.getParameter("cardId");
		// 기기 명
		String machine = request.getParameter("machine");
		// 사용 시간이 끝났는지 확인
		String done = request.getParameter("done");
		// 실제 사용시간
		int totalMin = Integer.parseInt(request.getParameter("totalMin"));
		System.out.println("cardId : " + cardId);
		System.out.println("machine : " + machine);
		// 운동시간을 담아두는 변수
		int useTime = 0;

		TB_ReservationDTO dto = new TB_ReservationDTO();
		dto.setRs_machine(machine);
		System.out.println(dto.getRs_machine());

		TB_ReservationDAO dao = new TB_ReservationDAO();
		TB_ReservationDTO result = dao.machineCheck(dto);

		// 사용하려는 기기에 예약한 사용자가 있는지 확인
		if (result != null) {
			// 예약정보가 존재
			// 기기에 예약한 사용자가 있다면 예약자가 현재 사용하려는 본인인지 확인
			if (result.getMb_card().equals(cardId)) {
				// 예약한 사람이 사용하려는 사람과 일치
				// {"사용가능여부":1,"사용시간":20}
				String str = "{\"사용가능여부\":1, \"사용시간\":" + result.getUse_time() + "}";
				useTime = result.getUse_time();
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = (JsonObject) parser.parse(str);
				String resultJson = gson.toJson(jsonObject);
				System.out.println(resultJson);

				writer.write(resultJson);
				writer.flush();
				// 예약자가 운동기구를 사용하게 되면 예약정보를 삭제해야 함
				dto.setMb_card(cardId);
				dto.setRs_machine(machine);
				int row = dao.delete(dto);
				
				if(row > 0) {
					// 예약정보 삭제 성공
					System.out.println("예약정보 삭제 성공!");
				}else {
					// 예약정보 삭제 실패
					System.out.println("예약정보 삭제 실패!");
				}

			} else {
				// 예약 정보가 없음(사용불가)
				String str = "{\"사용가능여부\":0, \"사용시간\":0}";
				JsonParser parser = new JsonParser();
				JsonObject jsonObject = (JsonObject) parser.parse(str);
				String resultJson = gson.toJson(jsonObject);
				System.out.println(resultJson);

				writer.write(resultJson);
				writer.flush();
				System.out.println("예약정보없음");

			}

		} else {
			// 예약 정보가 없음(사용가능)
			String str = "{\"사용가능여부\":2, \"사용시간\":20}";
			useTime = 3;
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser.parse(str);
			String resultJson = gson.toJson(jsonObject);
			// String resultJson = gson.toJson(str);
			System.out.println(resultJson);

			writer.write(resultJson);
			writer.flush();

			System.out.println("예약인원 없음");
		}
		
		if(done.equals("1")) {
			// 운동정보 저장
			// 카드번호,사용시간,사용기구명,헬스장명
			TB_UserExerciseDTO data = new TB_UserExerciseDTO(cardId);
			data.setRs_usetime(totalMin);
			data.setRev_machine(machine);
			// 헬스장 이름 member테이블에서 가져오기
			TB_MemberDAO memDao = new TB_MemberDAO();
			String gym_name = memDao.selectGym(cardId);
			data.setGymname(gym_name);
			
			System.out.println("헬스장 이름 : "+data.getGymname());

			TB_UserExerciseDAO insertDao = new TB_UserExerciseDAO();
			int row = insertDao.insert(data);
			
			if(row > 0) {
				// 운동정보 저장 성공
				System.out.println("운동정보 저장 성공!");
			}else {
				// 운동정보 저장 실패
				System.out.println("운동정보 저장 실패!");
			}
		}
		
	}

}
