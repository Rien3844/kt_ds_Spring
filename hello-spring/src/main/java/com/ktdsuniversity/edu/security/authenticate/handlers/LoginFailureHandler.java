package com.ktdsuniversity.edu.security.authenticate.handlers;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFailureHandler implements AuthenticationFailureHandler{

	private MembersDao membersDao;
	
	public LoginFailureHandler(MembersDao membersDao) {
		this.membersDao = membersDao;
	}
	
	/**
	 * 로그인 실패 시 호출되는 메서드
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		// 로그인 화면에서 입력한 이메일을 요청 파라미터에서 가져옴
		String email = request.getParameter("email");
		
		// 예외가 BadCredentialsException인경우 실행. ==> 비밀번호 틀렸을 때
		// 로그인 실패 횟수, block 여부 업데이트
		if(exception instanceof BadCredentialsException) {
			this.membersDao.updateIncreaseLoginFailCount(email);
			this.membersDao.updateBlock(email);
		}
		
		// 로그인 페이지 지정
		String loginPagePath = "/WEB-INF/views/members/login.jsp";
		// 로그인 실패 시 보여줄 JSP로 넘기기 위해 dispatcher 준비.
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPagePath);
		
		LoginVO loginVO = new LoginVO();
		loginVO.setEmail(email);
		
		// Spring 에서의 model.addAttribute(k, v) ==> 같은역할
		request.setAttribute("inputData", loginVO);
		
		// 에러 메세지 보내기
		request.setAttribute("errorMessage", exception.getMessage());
		
		// 지정한 로그인 페이지로 request, response 전달(forward)
		// 브라우저 주소창은 변화 X 서버 내부 페이지만 교체
		dispatcher.forward(request, response);
	}
	
}
