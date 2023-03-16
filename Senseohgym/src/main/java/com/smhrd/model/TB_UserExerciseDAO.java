package com.smhrd.model;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class TB_UserExerciseDAO {
	
	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

	// 운동정보 보내기  TB_UserExerciseDTO
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
	
	// 운동정보 보내기  TB_UserExerciseDTO
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
	
	
	
	
}
