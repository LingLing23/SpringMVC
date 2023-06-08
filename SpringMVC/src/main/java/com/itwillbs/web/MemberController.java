package com.itwillbs.web;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;


// 1. 컨트롤러별 공통 주소(URI) 설계
// 2. 각 기능별 주소(URI) 설계
// -> 로그인 주소, 로그아웃 주소 등 미리 정해놓는 것
// 3. 각 URI별 호출 방식 설정 (GET / POST)
// -> 사용자의 정보 입력, 조회 (GET)
// -> 데이터처리, DB접근필요 (POST)
// => 절대적인 규칙은 아니나 일반적으로 이러함.
// 4. 결과처리 어떻게할건지, 페이지 이동 어디로 할건지 설계
// 5. 예외처리


@Controller
@RequestMapping(value = "/member/*")	
// 이제부터 member 뒤에 나오는 모든 주소들은 이 컨트롤러가 처리
public class MemberController {

	// 로거 생성
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// 컨트롤러 ---- 서비스 ---- DAO
	// -> 컨트롤러가 DAO가려면 서비스가 필요함
	// -> 객체주입 (의존)
	// => 객체주입을 통해 컨트롤러가 DB까지 닿음
	@Inject
	private MemberService mService;
	
	
	
	// 동작구현 -> 메서드 설계
	// http://localhost:8088/member/MemberJoin.me (X) - 예전 방식
	// http://localhost:8088/member/join (O) - 현재 방식
	// 1. 회원가입 처리 - 정보입력
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String memberJoinGET() {
		logger.debug("memberJoinGET() 호출");
		logger.debug("/member/MemberJoin.jsp 뷰페이지 연결(자동)");
		
		return "/member/MemberJoin";
	}
	
	
	
	// 2. 회원가입 처리 - 정보처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String memberJoinPOST(HttpServletRequest request, 
			MemberVO vo) throws Exception{
			// 매개변수선언 (vo) 관련정보 (파라미터) 자동 수집
		logger.debug("memberJoinPOST() 호출");
		
		// 한글처리
		// -> 스프링에서는 한글처리 사용 못하므로 필터 처리해서 사용해야함
//		request.setCharacterEncoding("UTF-8");
		
		// 전달정보 저장 (회원가입 정보 - 파라미터)
		// 컨트롤러에서는 서비스를 통해 DAO호출 -> 회원가입메서드 호출
		// => 서비스가 DAO를 호출
//		MemberVO vo = new MemberVO();
//		vo.setUserid(request.getParameter("userid"));
		
		logger.debug(vo + "");
		
		// 서비스 - 회원가입 메서드
		// => DAO - 회원가입메서드 호출
		mService.memberJoin(vo);
		
		// 회원가입 모두 완료 후에는 페이지 이동하기
		// -> 로그인 페이지
		return "redirect:/member/login";
		
	}
	
	
	
	// 3. 로그인
	// 3-1. 정보 입력(get)
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() {
		logger.debug("loginGET() 호출");
		logger.debug("연결된 뷰페이지로 이동 (/member/login.jsp)");
	}
	// 리턴이 void면 .jsp 주소로 입력
	
	// 3-2. 정보 처리(post)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO vo) {
		// 전달정보 (로그인 ID ,PW) 저장
		logger.debug(vo + "");
		// -> 프레임워크에서는 중간중간 데이터가 잘 들어왓는지 체크하는걸 습관들여야 함.
		
		
		// DB에 직접 가지 않고 서비스를 통해 DAO - 로그인체크
		MemberVO resultVO = mService.memberLogin(vo);
		
		logger.debug(resultVO + "");
		
		// 로그인 여부에 따라 페이지 이동
		// 1) 로그인성공 : 메인페이지이동 (redirect)
		//                 로그인 아이디 세션에 저장
		// 2) 로그인실패 : 다시 로그인페이지로 이동
		
		return "";
	}
	
	
	
	
	
	
} // Controller
