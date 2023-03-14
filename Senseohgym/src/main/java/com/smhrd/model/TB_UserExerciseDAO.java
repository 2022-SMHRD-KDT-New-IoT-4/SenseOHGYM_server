package com.smhrd.model;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class TB_UserExerciseDAO {
	
	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

	// 운동정보 보내기  TB_UserExerciseDTO
	public TB_UserExerciseDTO selcet(TB_UserExerciseDTO dto) {
		TB_UserExerciseDTO result = null;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			result = sqlsession.selectOne("com.smhrd.model.TB_UserExerciseDAO.select", dto);
			                                               
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			sqlsession.close();
		}

		return result;
	}
	
	
	
	
}
