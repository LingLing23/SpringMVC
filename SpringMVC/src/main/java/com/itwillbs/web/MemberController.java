package com.itwillbs.web;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

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
	
	

	
	
	// 0. 동작구현 -> 메서드 설계
	// http://localhost:8088/member/MemberJoin.me (X) - 예전 방식
	// http://localhost:8088/member/join (O) - 현재 방식
	
	// 1. 회원가입
	// 1-1. 정보입력
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String memberJoinGET() {
		logger.debug("memberJoinGET() 호출");
		logger.debug("/member/MemberJoin.jsp 뷰페이지 연결(자동)");
		
		return "/member/MemberJoin";
	}

	// 1-2.정보처리
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
	
	
	
	
	
	// 2. 로그인
	// 2-1. 정보 입력(get)
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() {
		logger.debug("loginGET() 호출");
		logger.debug("연결된 뷰페이지로 이동 (/member/login.jsp)");
	}
	// 리턴이 void면 .jsp 주소로 입력
	
	// 2-2. 정보 처리(post)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO vo, HttpSession session) {
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
		if(resultVO != null) {
			// 성공
			session.setAttribute("id", resultVO.getUserid()); // id의 정보는 vo에 있음
			logger.debug("로그인 성공");
			return "redirect:/member/main";
		} else {
			// 실패
			logger.debug("로그인 실패");
			return "redirect:/member/login";
		}
	}
	
	
	
	
	
	// 3. 메인페이지
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainGET() {
		logger.debug("mainGET() 호출");
		
		logger.debug("/member/main.jsp 페이지 이동");
	}
	
	
	
	
	
	// 4. 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		logger.debug("logoutGET() 호출");
		
		// 세션정보 초기화
		// => main 가상주소 -> main.jsp -> logout 가상주소가 코드의 흐름
		// => jsp 페이지를 거치므로 거기서 session 객체 받아올 수 있으니까 파라미터로 받기!
		session.invalidate();
		
		return "redirect:/member/login";
//		return "redirect:/member/main";
		// main으로 가도 id정보가 없으면 login페이지로 가므로 이렇게해도 됨!
		
	}
	
	
	
	
	
	// 5. 회원정보 조회
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public void infoGET(HttpSession session, Model model) {
		logger.debug("infoGET() 호출");
	
		// 회원정보 가져오기
		// => 아이디정보(세션) DB에 있는 회원정보 모두 조회
		// => 서비스를 통해 DB호출 -> DAO 사용 가능
		String id = (String)session.getAttribute("id");
		MemberVO resultVO = mService.getMember(id);
		
		logger.debug("@@@@@ resultVO : " + resultVO);
		
		// 연결된 뷰페이지에 전달 -> Model 객체 사용
		model.addAttribute("resultVO", resultVO);
//		model.addAttribute(resultVO);
		// -> 이름없이 데이터 전달할 경우,
		// 뷰페이지에서 출력할때 클래스명의 첫글자를 소문자로 변경해서 출력하면 됨.
		
		// 페이지 이동
		logger.debug("@@@@@/member/info.jsp 페이지로 이동");
		
	}
	
	
	
	
	
	// 6. 회원정보 수정
	// 6-1. 기존의 정보 출력 + 수정정보 입력
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@SessionAttribute String id, Model model) {
		logger.debug("@@@@@ modifyGET() 호출");
		logger.debug("id : " + id);
		
		// 기존의 회원정보를 화면에 보여주기
		// (이름, 이메일 수정하기 - ID / PW 동일한 경우)
		MemberVO resultVO = mService.getMember(id);
		
		// 정보 저장 (뷰페이지 전달하기 위해) -> Model 객체 사용
		model.addAttribute("resultVO", resultVO);
		
		// modify.jsp 페이지에서 출력
		
	}
	
	// 6-2. 수정 정보 (데이터 처리)
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(MemberVO uvo) {
		logger.debug("@@@@@ modifyPOST() 호출");
		
		// 한글처리 (필터 사용)

		// 전달정보 저장 (수정된데이터)
		logger.debug("@@@@@ 파라미터 자동수집");
		logger.debug("@@@@@ uvo : " + uvo);
		
		// 서비스 -> 회원정보 수정가능한 기능
		int result = mService.memberModify(uvo);
		logger.debug("$$$$$ result : " + result);
		
		// 페이지 이동
		return "redirect:/member/main";
	}
	
	
	
	
	
	// 7. 회원정보 삭제
	// 7-1. 탈퇴정보 입력 및 전달
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void deleteGET() {
		logger.debug("@@@@@ deleteGET() 호출");
		logger.debug("@@@@@ /member/delete.jsp 이동");
		
	}
	
	// 7-2. 탈퇴 데이터처리
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePOST(@SessionAttribute String id,
							 @ModelAttribute("userpw") String userpw,
							 HttpSession session) {
		logger.debug("@@@@@ deletePOST() 호출");
		
		// 아이디 정보 (세션)
		logger.debug("@@@@@ id : " + id);
		// 전달받은 정보(userpw)저장
		logger.debug("@@@@@ pw : " + userpw);
		
		// 서비스 -> 정보 삭제처리
		MemberVO vo = new MemberVO();
		vo.setUserid(id);
		vo.setUserpw(userpw);
		int result = mService.memberDelete(vo);
		
		// 삭제 성공시 (기존 세션정보 초기화)
		if(result == 1) {
			session.invalidate();
		}
		
		return "redirect:/member/main";
	}
	
	
	
	
	
	// 8. 회원정보리스트
	// admin 계정만 볼 수 있음
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listGET(Model model) {
		logger.debug("@@@@@ listGET() 호출");
		
		// 서비스  ->  회원목록 가져오기
		List<MemberVO> memberList = mService.getMemberList();
		
		// Model 객체에 정보 저장
		model.addAttribute("memberList", memberList);
		
		// /member/list.jsp 뷰페이지 이동 (출력)
		
	}
	
	
	
	
	
	
	
	
} // Controller
