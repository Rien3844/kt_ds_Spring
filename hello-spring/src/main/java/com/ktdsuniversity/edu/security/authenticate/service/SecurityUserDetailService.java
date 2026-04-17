package com.ktdsuniversity.edu.security.authenticate.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.security.user.SecurityUser;

/**
 * 로그인 인증 수행시 로그인 요청 정보중 아이디로 회원의 정보를 조회.
 */
public class SecurityUserDetailService implements UserDetailsService {

	private MembersDao membersDao;
	
	public SecurityUserDetailService(MembersDao membersDao) {
		this.membersDao = membersDao;
	}
	
	/**
	 * 아이디로 DB에서 회원의 정보 조회.
	 * @param username : 현재 구조에서 email
	 * @return DB에서 조회한 회원의 정보(SecurityUser)
	 * @throws UsernameNotFoundException : DB에 회원의 정보가 없을 때.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loadedUser에 쿼리를 통해 DB에서 로그인 요청 정보의 email과 동일한 email을 가진 유저의 정보를 가져옴.
		MembersVO loadedUser = this.membersDao.selectMemberByEmail(username);
		
		// 없는 유저
		// ==> 어떤오류인지 알려주지않기위해 메시지는(아이디 또는 비밀번호가 일치하지 않습니다.)로 통일.
		if(loadedUser == null) {
			throw new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		// 쿼리를 통해 해당 유저의 Roles(권한정보)를 가져와 userRole에 저장
		List<String> userRole = this.membersDao.selectMemberRolesByEmail(username);
		
		// loadedUser에 권한을 DB에서 가져온 권한으로 set 해준다.
		loadedUser.setRoles(userRole);
		
		// Spring Security가 식별할 수 있도록 SecurityUser로 loadedUser를 감싸서 반환.
		return new SecurityUser(loadedUser);
	}

}
