package com.itwillbs.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class SampleController4 {

	// 로거 생성
	private static final Logger logger = LoggerFactory.getLogger(SampleController4.class);

	
	// http://localhost:8088/doD
	// http://localhost:8088/doD?data=itwill
	@RequestMapping(value = "/doD", method = RequestMethod.GET)
	public String doD(RedirectAttributes rttr /* Model model */ /* @ModelAttribute("data") String data */) {
		logger.debug("doD() 메서드 호출");
//		logger.debug("data : " + data);
//		model.addAttribute("data", "ITWILL");
		rttr.addFlashAttribute("data", "ITWILL2");
		
		// 1. Model 객체
		// -> 전달 방식 상관없이 사용 가능 / URI(주소줄)에 데이터값 표시됨 / 새로고침 데이터유지
		// 2. RedirectAttributes 객체
		// -> redirect방식만 사용 가능 / URI(주소줄)에 데이터값 표시안됨 / 새로고침 데이터유지안됨
		
		// @ModelAttribute
		// -> 내부적으로 Model객체 생성 후 그 안에 저장해서 사용.
		
				
//		return "/doE";
		return "redirect:/doE";		// redirect -> 가상주소 변화O,  화면 변화O
//		return "forward:/doE";		// forward  -> 가상주소 변화X,  화면 변화O
	}
	
	
	
	// http://localhost:8088/doE
	@RequestMapping(value = "/doE", method = RequestMethod.GET)
	public String doE(@ModelAttribute("data") String data) {
		logger.debug("doE() 메서드 호출");
		logger.debug("data : " +  data);
		
		return "";
	}
	
}
