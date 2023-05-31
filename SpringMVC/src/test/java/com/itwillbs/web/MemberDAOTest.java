package com.itwillbs.web;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.persistence.MemberDAO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MemberDAOTest {
	// 생성해놓은 기능 호출
	
	// 로거 - 콘솔창에 메세지 출력
	// -> 5버전에서는 어노테이션 하나로 사용 가능.
	// -> system.out.println() 출력문은 실제로 시스템을 사용하는 것이므로 굳이..?
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	// -> 객체를 직접 생성하지 않고 만들어진 것을 가져와 사용하는 '싱글톤패턴'이 적용됨.
	
	// DAO 객체가 필요 (의존관계) => 주입
	@Inject
	private MemberDAO mdao;
	// -> MemberDAO라는 객체를 root-context.xml에가서 찾아오는 것.

//	@Inject
//	private sqlSession sqlSession;
	
//	@Test
	public void 디비시간정보_조회() throws Exception{
		System.out.println(mdao.getTime());
		logger.info(mdao.getTime());
	}
	
	@Test
	public void 로그레벨테스트() throws Exception{		
		logger.warn("warn 레벨 실행");
		logger.info("info 레벨 실행");
		logger.debug("debug 레벨 실행");
	}
	
	
	
	
}
