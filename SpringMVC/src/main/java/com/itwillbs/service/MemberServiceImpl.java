package com.itwillbs.service;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;



@Service
public class MemberServiceImpl implements MemberService {
	// -> DAO와 Controller를 연결
	
	
	// DAO객체에 접근해야 함 -> 의존관계
	@Inject
	private MemberDAO mdao;
	// -> DAO랑 MemberService 객체가 합쳐짐.

	
	// 메서드 오버라이딩
	@Override
	public void memberJoin(MemberVO vo) {
		mdao.insertMember(vo);
	}


	@Override
	public MemberVO memberLogin(MemberVO vo) {
		MemberVO resultVO = mdao.loginMemeber(vo);
		
		return resultVO;
//		return mdao.loginMemeber(vo);
		// -> 이게 훨씬 좋은 코드이긴 함
	}
	
	
	
}
