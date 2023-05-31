package com.itwillbs.persistence;



//persistence (영속성) -> DB관련 처리
public interface MemberDAO {
	// -> 필요한 동작들을 추상메서드를 사용하여 정의
	
	// DB 시간정보 조회
	public String getTime();
	
}
