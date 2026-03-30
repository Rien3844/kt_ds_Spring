package com.ktdsuniversity.edu.board.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.board.service.BoardService;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.request.WriteVO;
import com.ktdsuniversity.edu.board.vo.response.SearchResultVO;

@Controller
public class BoardController {
	
	// 빈(bean) 컨테이너에 들어있는 객체 중 타입이 일치하는 객체를 할당 받는다.
	@Autowired
	private BoardService boardSerivce;
	
	@GetMapping("/")
	public String viewListPage(Model model) {
		
		SearchResultVO searchResult = this.boardSerivce.findAllBoard();
		
		// 게시글의 목록을 조회.
		List<BoardVO> list = searchResult.getResult();
		// 게시글의 개수 조회.
		int searchCount = searchResult.getCount();
		
		model.addAttribute("searchResult", list);
		model.addAttribute("searchCount", searchCount);
		
		return "board/list";
	}
	
	// 게시글 등록 화면 보여주는 EndPoint
	@GetMapping("/write")
	public String viewWritePage() {
		return "board/write";
	}
	
	@PostMapping("/write")
	public String doWriteAction
	 (/*@ModelAttribute 생략가능*/WriteVO writeVO) {
		System.out.println(writeVO.getSubject());
		System.out.println(writeVO.getEmail());
		System.out.println(writeVO.getContent());
		
		// create, update, delete => 성공, 실패 여부 반환.
		boolean createResult = this.boardSerivce.createNewBoard(writeVO);
		
		System.out.println("게시글 생성 성공? " + createResult);
		
		// redirect: 브라우저에게 다음 End Point를 요청하도록 지시.
		// redirect:/ ==> 브라우저에게 "/" endPoint로 이동하도록 지시.
		return "redirect:/";
	}
	
	// 게시글 내용 조회.
	// endpoint ==> /view/게시글ID
	// ex) /view/BO-20260327-000001
	// 해야 하는 역할
	// 1. 게시글 내용을 조회해서 브라우저에게 노출.
	// 2. 조회수가 1증가.
	// url을 치거나 클릭했다 ==> 무조건 Get
	@GetMapping("/view/{articleId}")//articalId에 게시글 ID 할당
	public String viewDetailPage(Model model, 
			@PathVariable String articleId) {
		
		// articleId로 데이터베이스에서 게시글을 조회한다.
		// 조회 = Dao에서 진행되지만, Controller에서 바로 넘어가지 않고 Service를 거쳐간다 ==> 트랜잭션 처리를 위해.
		// 조회 시 조회수가 하나 증가해야 한다.
		BoardVO findResult = this.boardSerivce.findBoardByArticleId(articleId);
		
		model.addAttribute("article", findResult);
		
		return "board/view";
	}
	
	@GetMapping("/delete")
	public String doDeleteAction
	  (@RequestParam String id ) {
		
		boolean deleteResult = this.boardSerivce.deleteBoardByArticleId(id);
		System.out.println("게시글 삭제 성공 ? " + deleteResult);
		
		return "redirect:/";
	}
}