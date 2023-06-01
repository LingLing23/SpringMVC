package com.itwillbs.web;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.itwillbs.domain.MemberVO;
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
	
	
	
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡINJECTㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	// DAO 객체가 필요 (의존관계) => 주입
	@Inject
	private MemberDAO mdao;
	// -> MemberDAO라는 객체를 root-context.xml에가서 찾아오는 것.

//	@Inject
//	private sqlSession sqlSession;
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡINJECTㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	
	
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡTESTㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//	@Test
	public void 디비시간정보_조회() throws Exception{
		System.out.println(mdao.getTime());
		logger.info(mdao.getTime());
	}
	
	
//	@Test
	public void 로그레벨테스트() throws Exception{		
		logger.warn("warn 레벨 실행");
		logger.info("info 레벨 실행");
		logger.debug("debug 레벨 실행");
	}
	
	
//	@Test
	public void 회원가입테스트() throws Exception{
		
		logger.debug("뷰에서 정보를 입력 받음 -> 생성");
		MemberVO vo = new MemberVO();
		vo.setUserid("ㅁㄴㅇㄹㅇ");
		vo.setUserpw("5555");
		vo.setUsername("메론");
		vo.setUseremail("melon@google.com");
		logger.debug("DAO - 회원가입 메서드 호출");
		
		// DAO 객체 주입
		// -> MemberDAO 객체 inject 완료!
		mdao.insertMember(vo);
	}
	
	
//	@Test
	public void 로그인테스트() throws Exception{
		logger.debug("로그인 테스트 시작");
		
		// 로그인 계정
		MemberVO vo = new MemberVO();
		vo.setUserid("ad");
		vo.setUserpw("1234");
				
		// DAO - 로그인 체크하는 메서드 호출
		// DB는 주입한 상태
		MemberVO resultVO = mdao.loginMemeber(vo);
		// -> MemberDAO에 있는 추상메서드가 아닌 MemberDAOImpl에 있는 오버라이딩 된 메서드를 호출함.
		// -> 부모메서드는 은닉,  오버라이딩 된 자식메서드가 호출됨.
		// -> 변수에 저장함. 로그인 결과가 여러개이므로 리턴값을 변수에 저장해둠.
		
		if(resultVO == null) {
			logger.debug("로그인 실패!");
			// -> JUnit은 성공하지만 로그인은 실패!
		}else {
			logger.debug("로그인 성공!");
		}
		
		logger.debug("로그인 테스트 끝");
	}
	
	
//	@Test
	public void 회원정보수정() throws Exception{
		logger.debug("회원정보수정 테스트 시작🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶");
		// -> 특수기호를 뒤에 찍어주면 로그가 보다 더 눈에 잘 들어옴
		
		// 수정할 회원정보
		MemberVO uvo = new MemberVO();
		uvo.setUserid("admin");
		uvo.setUserpw("1234");
		uvo.setUsername("관리자아아아아아아");
		uvo.setUseremail("ADMIN@ADMIN.COM");
		
		// DAO의 회원정보체크 메서드 호출
		// -> 결과를 변수에 저장
		Integer result = mdao.updateMember(uvo);
		
		// 결과에 따른 데이터처리
		if(result == 1) {
			logger.debug("정상 수정 완료");
		}else {
			// result == 0
			logger.debug("정상 수정 실패 (아이디 or 비밀번호 오류)");
		}
		
		logger.debug("회원정보수정 테스트 끝🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶🎶");
	}
	
	
//	@Test
	public void 회원정보삭제테스트() throws Exception{
		logger.debug("회원 삭제 테스트 시작");
		
		// 삭제할 회원정보
		MemberVO dvo = new MemberVO();
		dvo.setUserid("itwill");
		dvo.setUserpw("12342");
		
		// DAO에서 회원정보삭제 메서드 호출
		Integer result = mdao.deleteMember(dvo);
		
		// 결과에 따른 데이터처리
		if(result == 1) {
			logger.debug("회원 삭제 완료 !");
		}else {
			logger.debug("회원 삭제 실패 !");
		}
		
		logger.debug("회원 삭제 테스트 끝");
	}
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡTESTㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	
	
	
}
