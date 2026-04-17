package com.ktdsuniversity.edu.security.authenticate.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.members.helpers.SHA256Util;


/**
 * DB의 비밀번호 = 로그인 요청 정보의 비밀번호인지 검사.
 * 
 * 필요 데이터
 *  1. DB의 회원 비밀번호(암호화됨)
 *  2. 로그인 요청 정보의 비밀번호(암호화 안되있음)
 *  ==> 비교를 위해 로그인 요청 정보 비밀번호를 암호화할 필요 있음.
 *  3. 암호화를 위한 salt
 */
public class SecurityPasswordEncoder implements PasswordEncoder {


	/**
	 * 로그인 요청 정보 중 비밀번호를 암호화
	 * SHA 암호화 코드에서 사용 불가.
	 * 
	 * @param: rawPassword: 암호화 되지 않은 평문 비밀번호
	 * @return: 암호화된 비밀번호
	 */
	@Override
	public @Nullable String encode(@Nullable CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 로그인 요청 정보 중 평문 비밀번호, DB의 암호화된 비밀번호 일치 검사
	 * 평문 비밀번호 => 암호화 => 일치 검사 
	 */
	@Override
	public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	// encode에서 salt를 사용할 예정.(SHA기반 암호화를 했기때문에?)
	// matches에서 rawPassword+salt = encodedPassword인지 비교할 예정
	// ==> Override된 메서드에서 사용할 수 없는 parameter들이 메서드에 들어가야 한다.
	// ==> 오버로딩해서 사용
	public String encode(String rawPassword, String salt) {
		return SHA256Util.getEncrypt(rawPassword, salt);
	}
	
	public boolean matches(String rawPassword, String salt, String encodedPassword) {
		return this.encode(rawPassword, salt).equals(encodedPassword);
	}
}
