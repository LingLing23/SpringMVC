package com.itwillbs.service;
import java.util.List;

import com.itwillbs.domain.MemberVO;



public interface MemberService {

	// 구현동작 설계
	
	// 1.  회원가입
	public void memberJoin(MemberVO vo);
	
	// 2. 로그인
	public MemberVO memberLogin(MemberVO vo);
	// -> 해당 vo에는 id, pw만 저장되어 있음
	
	// 3. 회원정보 조회
	public MemberVO getMember(String id);
	
	// 4. 회원정보 수정
	public Integer memberModify(MemberVO uvo);
	
	// 5. 회원정보 삭제
	public Integer memberDelete(MemberVO dvo);
	
	// 6. 회원정보 목록
	public List<MemberVO> getMemberList(); 
	
}
