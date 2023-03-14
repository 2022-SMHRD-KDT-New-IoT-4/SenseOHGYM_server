package com.smhrd.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class TB_MemberDAO {

	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();

	// 회원등록 join
	public int join(TB_MemberDTO dto) {
		int row = 0;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			row = sqlsession.insert("com.smhrd.model.TB_MemberDAO.join", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.close();
		}
		return row;
	}

	// 로그인 Login
	public TB_MemberDTO Login(TB_MemberDTO dto) {
		TB_MemberDTO result = null;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			result = sqlsession.selectOne("com.smhrd.model.TB_MemberDAO.login", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.close();
		}

		return result;
	}

	// 회원목록 불러오기 selectAll(관리자)
	public List<TB_MemberDTO> selectAll(String gym_name) {

		List<TB_MemberDTO> result = null;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			result = sqlsession.selectList("com.smhrd.model.TB_MemberDAO.selectAll", gym_name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.close();
		}

		return result;
	}
}
