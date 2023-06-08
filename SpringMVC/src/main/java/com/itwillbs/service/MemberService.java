package com.itwillbs.service;
import com.itwillbs.domain.MemberVO;



public interface MemberService {

	// 구현동작 설계
	public void memberJoin(MemberVO vo);
	
	// 로그인
	public MemberVO memberLogin(MemberVO vo);
	// -> 해당 vo에는 id, pw만 저장되어 있음
}
