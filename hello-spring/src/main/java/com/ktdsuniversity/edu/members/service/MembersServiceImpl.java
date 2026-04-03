package com.ktdsuniversity.edu.members.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.helpers.SHA256Util;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.request.UpdateVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

@Service
public class MembersServiceImpl implements MembersService {

	@Autowired
	private MembersDao membersDao;
	
	@Override
	public boolean createNewMember(RegistVO registVO) {
		
		// 사용자가 사용가능한 이메일이래서 만들었는데, 잠깐사이에 다른 사람이 채갔다. ==> 사용자에게 DB Error를 보여줄 수 없으니, 해당 에러를 던져준다.
		MembersVO membersVO = this.membersDao.selectMemberByEmail(registVO.getEmail());
		if(membersVO != null) {
			throw new IllegalArgumentException(registVO.getEmail() + "은 이미 사용중입니다.");
		}
		
		// 암호화를 위한 비밀키 새엇ㅇ.
		String newSalt = SHA256Util.generateSalt();
		String usersPassword = registVO.getPassword();
		// 사용자가 입력한 비밀번호를 newSalt를 이용해 암호화.
		// 비밀번호와 newSalt의 값이 일치하면, 항상 같은 값의 암호화 결과가 생성된다.
		usersPassword = SHA256Util.getEncrypt(usersPassword, newSalt);
		
		// 비밀키 저장.
		registVO.setSalt(newSalt);
		// 암호화된 비밀번호 저장.
		registVO.setPassword(usersPassword);
		
		// Salt는 원래 기존 사용중인 DB에 같이 두는게 아니다. ==> 그 DB가 털리면 같이 털리니까. 원래는 물리적으로 분리하는게 맞음.
		
		int insertCount = this.membersDao.insertNewMember(registVO);
		return insertCount == 1;
	}

	@Override
	public MembersVO findMemberByEmail(String email) {
		MembersVO searchResult = this.membersDao.selectMemberByEmail(email);
		return searchResult;
	}

	@Override
	public boolean updateMemberByEmail(UpdateVO updateVO) {
		int updateCount = this.membersDao.updateMemberByEmail(updateVO);
		return updateCount == 1;
	}

	@Override
	public boolean deleteMemberByEmail(String email) {
		int deleteCount = this.membersDao.deleteMemberByEmail(email);
		return deleteCount == 1;
	}

	@Override
	public SearchResultVO findAllMember() {
		SearchResultVO result = new SearchResultVO();
		
		int count = this.membersDao.selectMemberCount();
		result.setCount(count);
		
		if(count == 0) {
			return result;
		}
		
		List<MembersVO> list = this.membersDao.selectMemberList();
		
		result.setResult(list);
		
		return result;
	}

}