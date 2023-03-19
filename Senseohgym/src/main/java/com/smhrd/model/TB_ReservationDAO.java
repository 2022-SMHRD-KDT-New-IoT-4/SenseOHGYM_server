package com.smhrd.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class TB_ReservationDAO {

	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

	// 예약하기 reservation
	public int reservation(TB_ReservationDTO dto) {
		int row = 0;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			row = sqlsession.insert("com.smhrd.model.TB_ReservationDAO.join", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.close();
		}
		return row;
	}

	// 예약after, 예약여부 확인
	public TB_ReservationDTO after(TB_ReservationDTO dto) {
		TB_ReservationDTO result = null;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			result = sqlsession.selectOne("com.smhrd.model.TB_ReservationDAO.after", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.close();
		}
		return result;
	}

	// 기구 이름으로 검색(예약자가 있는지 검색)
	public TB_ReservationDTO machineCheck(TB_ReservationDTO dto) {

		TB_ReservationDTO result = null;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			result = sqlsession.selectOne("com.smhrd.model.TB_ReservationDAO.machineCheck", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.close();
		}

		return result;

	}

	// 예약자가 운동을 시작하면 해당 예약정보 삭제
	public int delete(TB_ReservationDTO dto) {
		int row = 0;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			row = sqlsession.delete("com.smhrd.model.TB_ReservationDAO.delete", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.close();
		}
		return 0;
	}

}
