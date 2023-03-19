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
import com.smhrd.model.TB_ReservationDAO;
import com.smhrd.model.TB_ReservationDTO;

@WebServlet("/RledCheck")
public class Reservation_RledCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());

		Gson gson = new Gson();

		// 기구 이름 전송
		String machine = request.getParameter("machine");
		System.out.println("machine : " + machine);

		TB_ReservationDTO dto = new TB_ReservationDTO();
		dto.setRs_machine(machine);
		System.out.println(dto.getRs_machine());

		TB_ReservationDAO dao = new TB_ReservationDAO();
		TB_ReservationDTO result = dao.machineCheck(dto);

		if (result != null) {
			// 예약 정보가 있음(LED ON)
			String str = "{\"예약유무\":1}";
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser.parse(str);
			String resultJson = gson.toJson(jsonObject);
			// String resultJson = gson.toJson(str);
			System.out.println(resultJson);

			writer.write(resultJson);
			writer.flush();

			System.out.println("예약인원 있음");
		} else {
			// 예약 정보가 없음(LED OFF)
			String str = "{\"예약유무\":0}";
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = (JsonObject) parser.parse(str);
			String resultJson = gson.toJson(jsonObject);
			// String resultJson = gson.toJson(str);
			System.out.println(resultJson);

			writer.write(resultJson);
			writer.flush();

			System.out.println("예약인원 없음");
		}
		
	}

}
