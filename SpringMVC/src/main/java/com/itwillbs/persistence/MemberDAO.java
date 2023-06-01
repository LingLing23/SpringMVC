package com.itwillbs.persistence;

import com.itwillbs.domain.MemberVO;

//persistence (영속성) -> DB관련 처리
public interface MemberDAO {
	// -> 필요한 동작들을 추상메서드를 사용하여 정의
	
	// DB 시간정보 조회
	public String getTime();
	
	
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ CRUD ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	// C - 회원정보 가입
	public void insertMember(MemberVO vo);
	
	// R - 로그인 (회원정보 조회)
	// 방법1 )  사실상 이걸 써야하는게 맞고 젤 많이 사용함
	public MemberVO loginMemeber(MemberVO vo);
	
	// R - 로그인 (오버로딩)
	// 방법2 )
	public MemberVO loginMemeber(String id, String pw);
	
	// U - 회원정보 수정
	public Integer updateMember(MemberVO uvo);
	
	// D - 회원정보 삭제
	public Integer deleteMember(MemberVO dvo); 
	
}
