package com.ktdsuniversity.edu.members.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.members.service.MemberService;
import com.ktdsuniversity.edu.members.vo.MemberVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberSerivce;
	
	// 회원가입 멤버 보여주는 페이지
	@GetMapping("/member")
	public String viewMemberListPage(Model model) {
		
		SearchResultVO searchResult = this.memberSerivce.findAllMember();
		
		List<MemberVO> list = searchResult.getResult();
		
		int searchCount = searchResult.getCount();
		
		model.addAttribute("searchResult", list);
		model.addAttribute("searchCount", searchCount);
		
		return "member/list";
	}
	
	// 회원가입 등록
	@GetMapping("/regist")
	public String viewWritePage() {
		return "member/regist";
	}
	
	@PostMapping("/regist")
	public String doRegistAction(RegistVO registVO) {
		System.out.println(registVO.getEmail());
		System.out.println(registVO.getName());
		System.out.println(registVO.getPassword());
		
		boolean createResult = this.memberSerivce.createNewMember(registVO);
		
		System.out.println("회원가입 성공?" + createResult);
		
		return "redirect:/member";
		
	}
}
