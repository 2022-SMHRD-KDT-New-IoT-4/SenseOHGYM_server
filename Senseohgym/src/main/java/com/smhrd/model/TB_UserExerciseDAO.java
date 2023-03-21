package com.smhrd.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class TB_UserExerciseDAO {

	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

	// 운동정보 보내기(회원, 일주일간 운동 시간)
	public List<TB_UserExerciseDTO> select(TB_UserExerciseDTO dto) {

		List<TB_UserExerciseDTO> result = null;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			result = sqlsession.selectList("com.smhrd.model.TB_UserExerciseDAO.select", dto);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			sqlsession.close();
		}

		return result;
	}

	// 운동정보 보내기(회원, 일주일간 운동기구 사용횟수)
	public List<TB_UserExerciseDTO> select1(TB_UserExerciseDTO dto) {
		List<TB_UserExerciseDTO> result1 = null;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			result1 = sqlsession.selectList("com.smhrd.model.TB_UserExerciseDAO.select1", dto);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			sqlsession.close();
		}

		return result1;
	}

	// 운동정보 보내기(관리자, 일주인간 운동기구 사용횟수)
	public List<AdminEexerciseDTO> selectAll(String gym_name) {
		List<AdminEexerciseDTO> result = null;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			result = sqlsession.selectList("com.smhrd.model.TB_UserExerciseDAO.selectAll", gym_name);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			sqlsession.close();
		}

		return result;
	}

	// 아두이노에서 보낸 운동정보 저장
	public int insert(TB_UserExerciseDTO data) {
		int row = 0;

		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			row = sqlsession.insert("com.smhrd.model.TB_UserExerciseDAO.insert", data);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			sqlsession.close();
		}
		return 0;
	}

}
