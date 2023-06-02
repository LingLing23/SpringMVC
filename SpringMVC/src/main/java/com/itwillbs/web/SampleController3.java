package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;



@Controller
public class SampleController3 {

	
	// 로거 생성
	private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);
	
	
	
	// doC 메서드 정의
	// 파라미터 데이터 전달
	// http://localhost:8088/doC
	// http://localhost:8088/doC?msg=hello
	@RequestMapping(value = "/doC", method = RequestMethod.GET)
	public String doC(@ModelAttribute("msg") String tmp) {
		logger.debug("doC 호출");
		
		logger.debug("msg : " + tmp);
		
		return "doC";
	}
	
	
	
	// 파라미터 데이터 2개 전달
	// http://localhost:8088/doC1?msg=hello&age=20
	@RequestMapping(value = "/doC1", method = RequestMethod.GET)
	public String doC1(@ModelAttribute("msg") String tmp
			, @ModelAttribute("age") int age) {
		logger.debug("doC1 호출");
		
		logger.debug("msg : " + tmp);
		logger.debug("age : " + (age+100));	// age가 int타입인지 확인하기 위해 숫자를 더해봄
		
		return "doC";
		// 연결된 view 페이지
	}
	
	
	
	// MemberVO 객체를 이용한 파라미터 데이터 전달 (훨씬 간단)
	// http://localhost:8088/doC2?userid=admin&userpw=1234
	// http://localhost:8088/doC2?userid=admin&userpw=1234&tel=010-1234-1234
	@RequestMapping(value = "/doC2", method = RequestMethod.GET)
	public String doC2(MemberVO vo, @ModelAttribute("tel") String tel) {
		logger.debug("doC2 호출");
		
		logger.debug("userid : " + vo.getUserid());
		logger.debug("userpw : " + vo.getUserpw());
		logger.debug(vo + "");
		logger.debug("tel : " + tel);
		
		
		return "doC";
	}
	
	
	
	// 파라미터가 아닌 정보 뷰페이지로 전달
	// http://localhost:8088/doC3
	@RequestMapping(value = "/doC3", method = RequestMethod.GET)
	public String doC3(Model model) {
		logger.debug("doC3 호출");
		
		// 임시 DB데이터
		MemberVO vo = new MemberVO();
		vo.setUserid("admin");
		vo.setUserpw("1234");
		
		// 영역에 저장 - 불가능
		// -> Model 객체를 사용하면 가능
		// Model : 스프링에서 제공, 컨트롤러와 뷰 사이에 정보를 옮겨주는 컨테이너(박스) 개념.
		model.addAttribute("vo", vo);
		
		return "doC";
	}
	
}
