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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.members.service.MembersService;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.response.DuplicateResultVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

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
}
