package com.ktdsuniversity.edu.members.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.helpers.SHA256Util;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

@Service
public class MembersServiceImpl  implements MembersService{

	@Autowired
	private MembersDao membersDao;

	@Override
	public boolean createNewMember(RegistVO registVO) {
				// 실?시간 이메일 중복방지
				MembersVO membersVO = this.membersDao.selectMemberByEmail(registVO.getEmail());
				if(membersVO != null) {
					throw new IllegalArgumentException(registVO.getEmail() + "은 이미 사용중입니다.");
				}
				
				// 암호화를 위한 비밀키 생성
				String newSalt = SHA256Util.generateSalt();
				String usersPassword = registVO.getPassword();
				// 사용자가 입력한 비밀번호를 newSalt를 이용해 암호화.
				// 비밀번호와 newSalt의 값이 일치하면, 항상 같은 값의 암호화 결과가 생성된다.
				usersPassword = SHA256Util.getEncrypt(usersPassword, newSalt);
				
				// 비밀키 저장.
				registVO.setSalt(newSalt);
				// 암호화된 비밀번호 저장.
				registVO.setPassword(usersPassword);
				
				int insertCount = this.membersDao.insertNewMember(registVO);
				return insertCount == 1;
	}
	
	@Override
	public MembersVO findMemberByEmail(String email) {
		MembersVO searchResult = this.membersDao.selectMemberByEmail(email);
		return searchResult;
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
