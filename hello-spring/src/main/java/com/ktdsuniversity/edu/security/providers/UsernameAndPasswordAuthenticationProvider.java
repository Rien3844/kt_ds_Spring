package com.ktdsuniversity.edu.security.providers;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.security.authenticate.service.SecurityPasswordEncoder;
import com.ktdsuniversity.edu.security.user.SecurityUser;

/**
 * Spring Security의 인증(DB에 저장된 email,password와 입력받은 e,p 비교)을 수행하는 공급자
 * 일치하다 = AuthenticationToken 발급 => SecurityContext에 저장.
 * 불일치 = BadCredentialsException 발생(아이디 또는 비밀번호가 일치하지 않습니다.)
 */
public class UsernameAndPasswordAuthenticationProvider implements AuthenticationProvider {

	// 사용자가 로그인 할 때 전송한 아이디(이메일)로 회원의 정보 조회.
	private UserDetailsService userDetailsService;
	
	// 사용자가 로그인 할 때 전송한 비밀번호와 회원의 비밀번호를 비교.
	private PasswordEncoder passwordEncoder;
	
	// 생성자
	public UsernameAndPasswordAuthenticationProvider(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * AuthenticationProvider가 가지고있는 class
	 * 
	 * Client에서 Spring Security 로그인 요청이 있을 때 마다 실행.
	 * 
	 * 사용자가 보내준 아이디(이메일)+비밀번호를 이용해 인증을 수행.
	 * UserDetailsService 인터페이스를 이용해 사용자의 정보 조회
	 * ==> PasswordEncoder 인터페이스를 이용해 사용자의 비밀번호를 검증
	 * ==> 일치하다 = UsernamePasswordAuthenticationToken 발급
	 * 
	 * @param authentication : 사용자가 로그인 요청한 정보 (이메일, 비밀번호)
	 * @return UsernamePasswordAuthenticationToken
	 */
	@Override
	public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		// 로그인에 사용된 사용자의 이메일
		String email = authentication.getName();
		
		// loadUserByUsername의 parameter username = 해당 구조에서는 email
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		// 계정잠김여부 확인
		if(!userDetails.isAccountNonLocked()) {
			// 비밀번호 횟수이상 틀려서 계정잠김 
			// ==> 어떤오류인지 알려주지않기위해 메시지는(아이디 또는 비밀번호가 일치하지 않습니다.)로 통일.
			throw new LockedException("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		// 사용자가 입력한 비밀번호를 가져와(authentication.getCredentials())
		// 문자열로 변환(.toString()) 후 저장(rawPassword)
		// ==> rawPassword = 사용자가 입력한 비밀번호 원문
		String rawPassword = authentication.getCredentials().toString();
		
		// this.userDetailsService.loadUserByUsername(email)를 SecurityUser로 변환하고
		// 그안의 회원정보를 꺼내옴(.getMembersVO())
		// membersVO가 MembersVO를 가진다.
		MembersVO membersVO = ((SecurityUser) userDetails).getMembersVO();
		
		SecurityPasswordEncoder passwordComparator = (SecurityPasswordEncoder) this.passwordEncoder;
		
		// matches(String rawPassword, String salt, String encodedPassword)
		// 반환값 encode(rawPassword, salt).equals(encodedPassword)
		// ==>
		// 원물 비밀번호(rawPassword)를 MembersVO에 있는 Salt로 암호화(membersVO.getSalt())하고
		// 그 값을 DB에 저장된 비밀번호와 비교(userDetails.getPassword())
		boolean isMatch = passwordComparator.matches(rawPassword,
													 membersVO.getSalt(),
													 userDetails.getPassword());
		// 비교(인증) 실패 - 값이 다름
		// ==> 어떤오류인지 알려주지않기위해 메시지는(아이디 또는 비밀번호가 일치하지 않습니다.)로 통일.
		if(!isMatch) {
			throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		// SecurityContext에 저장할 인증 토큰
		// principal = membersVO
		// credentials = userDetails.getPassword()
		// authorities = userDetails.getAuthorities()
		return new UsernamePasswordAuthenticationToken(membersVO,
				   userDetails.getPassword(),
				   userDetails.getAuthorities());
	}


	
	/**
	 * AuthenticationProvider가 가지고있는 class
	 * 
	 * 이 인증 공급자가 발급하는 토큰의 종류를 설정.
	 * 
	 * @Param authentication: authenticate() 메소드가 발급한 토큰의 클래스
	 * @return authenticate()가 발급한 토큰의 클래스가 적절한지 여부
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals( UsernamePasswordAuthenticationToken.class);
	}
}
