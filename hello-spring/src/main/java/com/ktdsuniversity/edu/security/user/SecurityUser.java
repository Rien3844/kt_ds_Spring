package com.ktdsuniversity.edu.security.user;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.members.vo.MembersVO;

/**
 * Spring Security가 사용자 식별에 사용.
 */
public class SecurityUser implements UserDetails{

	private static final long serialVersionUID = 7907191462472441568L;

	/**
	 * UserDetails 인터페이스로 사용자의 세부 내용을 알 수 없기 때문에 사용자 정보를 가진 membersVO를 멤버변수로 추가.
	 * getPassword, getUsername, isAccountNonLocked
	 */
	private MembersVO membersVO;
	
	// 생성자
	public SecurityUser(MembersVO membersVO) {
		this.membersVO = membersVO;
	}
	
	public MembersVO getMembersVO() {
		return this.membersVO;
	}
	
	
	/**
	 * 사용자의 권한 목록을 관리 ==> 권한별 서비스에서 사용
	 * ROLES 테이블에서 조회
	 * 
	 * GrantedAuthority <= 사용자에게 허용된 권한
	 * Collection <= List / Set
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Spring Security는 2가지를 체크함.
		// 1. ROLE ==> 권한
		// 2. ACTION ==> CRUD 등등...
		// 두 가지가 어떤것인지 구분하는 방법은
		// ROLE의 경우 Prefix가 ROLE_ ==> 따로 변경하지 않는 한 기본값
		// ACTION의 경우 실행하는 ACTION의 이름(CREATE, READ, UPDATE, DELETE, ...)으로 작성.
		return this.membersVO.getRoles()
							 .stream()
							 .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
							 .toList();
	}

	/**
	 * 로그인 한 회원의 비밀번호
	 */
	@Override
	public @Nullable String getPassword() {
		return this.membersVO.getPassword();
	}

	/**
	 * 사용자의 식별가능한 아이디(현재 구조에서는 email)
	 */
	@Override
	public String getUsername() {
		return this.membersVO.getEmail();
	}
	
	/**
	 * 사용자의 계정 잠금 여부(true 반환)
	 */
	@Override
	public boolean isAccountNonLocked() {
		return this.membersVO.getBlockYn().equals("N");
	}

}
