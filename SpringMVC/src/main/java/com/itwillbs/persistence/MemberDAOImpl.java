package com.itwillbs.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;



//@Repository : 스프링(root-context.xml)에 해당하는 파일이 DAO의 동작 하는 객체 등록.
@Repository
public class MemberDAOImpl implements MemberDAO{
	// 1.2. DB 연결
	// -> SqlSessionFactory 객체 주입
	// 3. SQL 쿼리 작성
	// 4. pstmt 객체 생성, ??? 추가
	// 5. SQL 실행
	// 6. (select) 데이터처리
	
	
	@Inject
//	private SqlSessionFactory sqlFactory;
	private SqlSession sqlSession;
	
	
	// 추상메서드 오버라이딩
	@Override
	public String getTime() {
		String time = sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");	
		// 리턴타입이 <T> 제네릭타입, 매핑된 객체를 리턴
		// 제네릭타입을 사용하는 이유는 내가 그때그때 타입을 지정할 수 있기 때문이며
		// 앞에서 memberMapper.xml에서 리턴타입을 string으로 지정해줬음.
		
		return time;
		// 최종적으로 메서드의 리턴값을 담고 있는 time의 정보를 알아야하므로 time을 리턴함.
	}
	
}
