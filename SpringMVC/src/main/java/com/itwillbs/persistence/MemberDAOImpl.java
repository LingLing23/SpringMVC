package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.itwillbs.domain.MemberVO;



//@Repository : 스프링(root-context.xml)에 해당하는 파일이 DAO의 동작 하는 객체 등록.
@Repository
public class MemberDAOImpl implements MemberDAO{
	// 1.2. DB 연결
	// -> SqlSessionFactory 객체 주입
	// 3. SQL 쿼리 작성
	// 4. pstmt 객체 생성, ??? 추가
	// 5. SQL 실행
	// 6. (select) 데이터처리이
	
	
	@Inject
//	private SqlSessionFactory sqlFactory; -> 필요없음. MemberDAO를 구현하는데 DB정보를 DAO가 가지고 있음
	private SqlSession sqlSession;
	private static final String NAMESPACE = "com.itwillbs.mapper.MemberMapper";
	// -> 원래는 상수라서 final키워드만 있으면 되는데 암묵적으로 static키워드도 같이 붙임.
	
	
	// "mylog" + ctrl + space
	// -> 단축키를 통한 로거 생성
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);

	
	// 시간정보 조회 메서드 오버라이딩
	@Override
	public String getTime() {
		String time = sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");	
		// 리턴타입이 <T> 제네릭타입, 매핑된 객체를 리턴
		// 제네릭타입을 사용하는 이유는 내가 그때그때 타입을 지정할 수 있기 때문이며
		// 앞에서 memberMapper.xml에서 리턴타입을 string으로 지정해줬음.
		
		return time;
		// 최종적으로 메서드의 리턴값을 담고 있는 time의 정보를 알아야하므로 time을 리턴함.
	}

	
	// 회원가입 메서드 오버라이딩
	@Override
	public void insertMember(MemberVO vo) {
		// 원래라면 DB연결, sql작성, pstmt객체, sql실행 등등..해야했음
		// 그러나 DB연결 할 필요 없음 -> MemberDAO를 inject(주입)했기 때문
		sqlSession.insert(NAMESPACE + ".insertMember", vo);
		logger.debug("회원가입 완료!");
	}

	
	// 방법1 )
	// 로그인 메서드 오버라이딩
	@Override
	public MemberVO loginMemeber(MemberVO vo) {
		logger.debug("테스트 -> DAO 호출 : 로그인 동작 수행");
		
		// SQL구문 작성
		// SQL 실행
		logger.debug("DAO -> mapper 사용 -> SQL 쿼리 실행");
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE + ".login", vo);	 
		// id, pw를 vo에 담아서 mapper에 전달,  DB에서 데이터 처리.
		
		logger.debug("SQL 실행 결과 리턴 -> 테스트");
//		logger.debug(resultVO.toString());
		// -> toString() 메서드는 null값을 받을 수 없으므로 에러 발생함.
		logger.debug(resultVO + "");
		
		return resultVO;
	}

	
	// 방법2 )
	// 오버로딩된 로그인 메서드 오버라이딩
	@Override
	public MemberVO loginMemeber(String id, String pw) {
		
		// 아이디, 비밀번호 전달받아서 사용
		// -> 1개 이상의 정보를 mapper에 각각 전달 불가
		// -> VO 객체,  즉 도메인 객체에 저장하면 처리 가능
		// => mapper에 전달할 수 있는 공간(line 93의 vo)이 하나밖에 없으므로,
		//    전달정보 (파라미터정보)는 객체단위로 전달하기
//		MemberVO vo = new MemberVO();
//		vo.setUserid(userid);
//		vo.setUserpw(userpw);
		
		// 만약에 전달된 정보가 하나의 객체에 저장이 불가능한 경우 !
		Map<String, Object> params = new HashMap<String, Object>();
		// -> Map은 인터페이스이므로 객체 생성 불가, 이미 만들어져있는 HashMap 사용하기
		
		// params.put("mapper 매핑된 이름", 전달되는 값);
		params.put("user_id", id);
		params.put("user_pw", pw);
		
		
		// SQL 호출, 실행
		sqlSession.selectOne(NAMESPACE + ".login", params);
		
		return null;
		// 원래라면 값 리턴해야 결과 볼 수 있음.
	}


	// 회원정보 수정 메서드 오버라이딩
	@Override
	public Integer updateMember(MemberVO uvo) {
		
		logger.debug("테스트 -> DAO 호출 : 회원정보 수정");
		
		// 수정할 정보를 가져옴 (uvo)
		logger.debug("DAO -> mapper 호출 -> SQL 실행");
		Integer result = sqlSession.update(NAMESPACE + ".update",uvo);
		// -> update() 메서드는 변경된 행의 수를 리턴하므로 Integer타입의 result 변수에 담아줌
		
		logger.debug("SQL 실행 결과를 리턴");
		
		return result;
	}


	// 회원정보 삭제 메서드 오버라이딩
	@Override
	public Integer deleteMember(MemberVO dvo) {
		
		logger.debug("회원정보 탈퇴 수행");
		return sqlSession.delete(NAMESPACE + ".delete",dvo);
		// -> delete 구문이 성공하면 삭제된 행의 수가 1이므로 1이 리턴됨.
		
	}
	
	
	
	
	
	
	
}
