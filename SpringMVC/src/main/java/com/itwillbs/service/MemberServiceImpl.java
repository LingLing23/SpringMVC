package com.itwillbs.service;
import java.util.List;

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

	
	
	
	// 1. 회원가입
	@Override
	public void memberJoin(MemberVO vo) {
		mdao.insertMember(vo);
	}


	
	// 2. 로그인
	@Override
	public MemberVO memberLogin(MemberVO vo) {
		MemberVO resultVO = mdao.loginMemeber(vo);
		return resultVO;
//		return mdao.loginMemeber(vo);
		// -> 이게 훨씬 좋은 코드이긴 함
	}


	
	// 3. 회원정보 조회
	@Override
	public MemberVO getMember(String id) {
		
		return mdao.getMember(id);
	}



	// 4. 회원정보 수정
	@Override
	public Integer memberModify(MemberVO uvo) {

		return mdao.updateMember(uvo);
	}



	// 5. 회원정보 삭제
	@Override
	public Integer memberDelete(MemberVO dvo) {

		return mdao.deleteMember(dvo);
	}



	// 6. 회원정보 목록
	@Override
	public List<MemberVO> getMemberList() {
		
		return mdao.getMemberList();
	}
	
	
	
	
	
	
	
	
	
}
