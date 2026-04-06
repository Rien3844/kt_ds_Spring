package com.ktdsuniversity.edu.members.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.members.service.MembersService;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.request.UpdateVO;
import com.ktdsuniversity.edu.members.vo.response.DuplicateResultVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MembersController {

	@Autowired
	private MembersService membersService;
	
	@ResponseBody // 반환 결과(result)가 JSON으로 변환.
	@GetMapping("/regist/check/duplicate/{email}")
	public DuplicateResultVO doCheckDuplicateEmailAction(@PathVariable String email) {
		
		// email이 이미 사용중인지 확인한다.
		MembersVO membersVO = this.membersService.findMemberByEmail(email);
		
		
		// 확인된 결과를 브라우저에게 JSON으로 전송한다.
		// 이미 사용중 ==> {email : "test@gmail", duplicate :  true}
		// 사용중 X ==> {email : "test@gmail", duplicate :  false}
		DuplicateResultVO result = new DuplicateResultVO();
		result.setEmail(email);
		result.setDuplicate(membersVO != null);
		
		return result;
	}
	
	
	@GetMapping("/regist")
	public String viewRegistPage() {
		return "members/regist";
	}
	
	@PostMapping("/regist")
	public String doRegistAction(@Valid @ModelAttribute RegistVO registVO,
								 BindingResult bindingResult,
								 Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("inputData", registVO);
			return "members/regist";
		}
		
		boolean createResult = this.membersService.createNewMember(registVO);
		System.out.println("회원 가입 결과? " + createResult);
		return "redirect:/members/list";
	}
	
	/*
	 * /member/view/사용자아이디 ==> 회원 정보 조회 하기.
	 * /member/update/사용자아이디 ==> 회원 정보 수정 페이지 보기.
	 * /member/update/사용자아이디 ==> 회원 정보 수정 하기.
	 * /member/delete?id=사용자아이디 ==> 회원 정보 삭제 하기.
	 */
	@GetMapping("/members/list")
	public String viewViewPage(Model model) {
		SearchResultVO searchResult = this.membersService.findAllMember();
		
		List<MembersVO> list = searchResult.getResult();
		
		int searchCount = searchResult.getCount();
		
		model.addAttribute("searchResult", list);
		model.addAttribute("searchCount", searchCount);
		
		return "members/list";
	}
	
	@GetMapping("/members/view/{email}")
	public String viewMemberPage(@PathVariable String email, 
			Model model) {
		MembersVO searchReuslt = this.membersService.findMemberByEmail(email);
		model.addAttribute("member", searchReuslt);
		return "members/view";
	}
	
	@GetMapping("/member/update/{email}")
	public String viewUpdatePage(@PathVariable String email,
			Model model) {
		MembersVO searchReuslt = this.membersService.findMemberByEmail(email);
		model.addAttribute("member", searchReuslt);
		return "members/update";
	}
	
	@PostMapping("/members/update/{email}")
	public String doUpdateAction(@PathVariable String email,
			UpdateVO updateVO) {
		updateVO.setEmail(email);
		boolean updateResult = this.membersService.updateMemberByEmail(updateVO);
		System.out.println("수정 결과? " + updateResult);
		return "redirect:/members/view/" + email;
	}
	
	@GetMapping("/member/delete")
	public String doDeleteAction(@RequestParam String id) {
		boolean updateResult = this.membersService.deleteMemberByEmail(id);
		System.out.println("삭제 결과? " + updateResult);
		return "redirect:/members/list";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		return "members/login";
	}
	
	@PostMapping("/login")
	public String doLoginAction(@Valid @ModelAttribute LoginVO loginVO, BindingResult bindingResult, Model model, HttpServletRequest request) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("loginData", loginVO);
			return "members/login";
		}
		
		String userIp = request.getRemoteAddr();
		loginVO.setIp(userIp);
		
		MembersVO member = this.membersService.findMemberByEmailAndPassword(loginVO);
		
		// 서버의 세션을 삭제한다.
		// 내 정보를 더이상 기억하지 마라 ==> 로그아웃
		request.getSession().invalidate(); 
		
		// request.getSession(); <== HttpRequestHeader로 전달된 JSESSIONID의 객체를 반환.
		// request.getSession(true); <== 기존 JSESSIONID로 발급된 세션 객체는 버리고, 새로운 ID의 세션 객체를 생성 후 반환.
		HttpSession session = request.getSession(true); // 세션을 새롭게 만들어라.
		session.setAttribute("__Login_DATA__", member);
		
		return "redirect:/";
	}
}

	