package com.ktdsuniversity.edu.members.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.members.dao.MemberDao;
import com.ktdsuniversity.edu.members.vo.MemberVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public SearchResultVO findAllMember() {
		int count = this.memberDao.selectMemberCount();
		
		List<MemberVO> list = this.memberDao.selectMemberList();
		
		SearchResultVO result = new SearchResultVO();
		result.setResult(list);
		result.setCount(count);
		
		return result;
	}
	
	@Override
	public boolean createNewMember(RegistVO registVO) {
		int insertCount = this.memberDao.insertNewMember(registVO);
		System.out.println("생성된 유저 정보 수? : " + insertCount);
		return insertCount == 0;
	}
}
