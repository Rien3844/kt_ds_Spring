package com.ktdsuniversity.edu.members.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.helpers.SHA256Util;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.request.UpdateVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

import jakarta.validation.Valid;

@Service
public class MembersServiceImpl implements MembersService {

	@Autowired
	private MembersDao membersDao;

	@Override
	public boolean createNewMember(RegistVO registVO) {

		// 사용자가 사용가능한 이메일이래서 만들었는데, 잠깐사이에 다른 사람이 채갔다. ==> 사용자에게 DB Error를 보여줄 수 없으니, 해당
		// 에러를 던져준다.
		MembersVO membersVO = this.membersDao.selectMemberByEmail(registVO.getEmail());
		if (membersVO != null) {
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

		if (count == 0) {
			return result;
		}

		List<MembersVO> list = this.membersDao.selectMemberList();

		result.setResult(list);

		return result;
	}

	@Override
	public MembersVO findMemberByEmailAndPassword(@Valid LoginVO loginVO) {

		// 1. email을 이용해 회원 정보 조회하기(selectMemberByEmail)
		MembersVO memberVO = this.membersDao.selectMemberByEmail(loginVO.getEmail());
		
		if(memberVO == null) {
		// 2. 조회된 결과가 없다면 "이메일 또는 비밀번호가 잘못되었습니다." 예외 던지기
		// ==> IllegalArgumentException
			throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.");
		}
		else {
			
			if(memberVO.getBlockYn().equals("Y")) {
				// 로그인 Block 된 시간으로부터 120분이 지나면 다시 로그인 가능한 상태로 변경한다.
				// 이 경우엔 예외를 던지지 않도록 한다.
//				LocalDate nowDate = LocalDate.now();
//				LocalTime nowTime = LocalTime.now();

//				if(memberVO.getLatestLoginFailDate().isAfter(nowDate))
				String latestLoginFailDate = memberVO.getLatestLoginFailDate();
				
				DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime lastestBlockDateTime = LocalDateTime.parse(latestLoginFailDate, dateTimePattern);
				
				if(lastestBlockDateTime.isAfter(LocalDateTime.now().minusMinutes(120))) {
					throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.");
				}
			}
			
			// 3. 조회된 결과가 있다면 사용자가 전송한 비밀번호와 조회된 회원의 salt를 이용해 SHA 암호화 하기.
			String Salt = memberVO.getSalt();
			String userPassword = loginVO.getPassword();
			
			userPassword = SHA256Util.getEncrypt(userPassword, Salt);
			
			loginVO.setSalt(Salt);
			loginVO.setPassword(userPassword);
				// 4. 3에서 암호화한 비밀번호와 1에서 조회한 비밀번호가 일치하는지 확인하기.
				
			if(!userPassword.equals(memberVO.getPassword())) {
				
				// 로그인 실패 시 loginFailCount를 1 증가시킨다.
				this.membersDao.updateIncreaseLoginFailCount(loginVO.getEmail());
				
				// 최근 로그인 실패 횟수가 5이상이면 Y로 변경
				this.membersDao.updateBlock(loginVO.getEmail());
				
				// 5. 비밀번호가 일치하지 않는다면 "이메일 또는 비밀번호가 잘못되었습니다." 예외 던지기
				// ==> IllegalArgumentsException
				throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.");
			}
			
			// 로그인 성공처리
			// 1. login_fail_count를 0으로 초기화.
			// 2. latest_login_ip를 현재 아이피로 변경.
			// 3. login_date를 현재 시간으로 변경.
			// 4. block_yn을 'N'으로 변경.
			membersDao.updateSuccessLogin(loginVO);
			
			// 6. 비밀번호가 일치하면 1에서 조회한 결과를 반환.
			return memberVO;
		}
	}

}