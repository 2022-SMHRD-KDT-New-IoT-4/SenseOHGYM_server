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

	// 회원정보 수정 update(관리자)
	public int update(TB_MemberDTO dto) {
		int row = 0;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			row = sqlsession.update("com.smhrd.model.TB_MemberDAO.update", dto);
			sqlsession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.close();
		}
		return row;
	}

	// 회원정보 삭제 delete(관리자)
	public int delete(TB_MemberDTO dto) {
		int row = 0;
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			row = sqlsession.update("com.smhrd.model.TB_MemberDAO.delete", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.close();
		}
		return row;
	}

	// 카드번호로 회원의 헬스장 이름 select(아두이노가 보낸 운동정보를 저장하기 위해)
	public String selectGym(String mb_card) {
		String result = "";
		SqlSession sqlsession = sqlSessionFactory.openSession(true);
		try {
			result = sqlsession.selectOne("com.smhrd.model.TB_MemberDAO.selectGym", mb_card);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlsession.close();
		}
		return result;
	}
}
