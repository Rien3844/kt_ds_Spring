package com.ktdsuniversity.edu.movie.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.movie.enums.ReadType;
import com.ktdsuniversity.edu.movie.service.MovieService;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.WriteVO;
import com.ktdsuniversity.edu.movie.vo.response.SearchResultVO;

import jakarta.validation.Valid;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/list")
	public String viewListPage(Model model) {
		
		SearchResultVO searchResult = this.movieService.findAllMovie();
		
		List<MovieVO> list = searchResult.getMovieList();
		
		int searchCount = searchResult.getMovieCount();
		
		model.addAttribute("searchResult", list);
		model.addAttribute("searchCount", searchCount);
		
		return "movie/list";
	}
	
	@GetMapping("/view/{id}")
	public String viewDetailPage(Model model, @PathVariable String id) {
		MovieVO findResult = this.movieService.findMovieById(id, ReadType.VIEW);
		
		model.addAttribute("article", findResult);
		
		return "movie/view";
	}
	
	@GetMapping("/write")
	public String viewWritePage() {
		return "movie/write";
	}
	
	@PostMapping("/write")
	public String doWriteAction(@Valid @ModelAttribute WriteVO writeVO,
								BindingResult bindingResult,
								Model model) {
		
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			model.addAttribute("inputData", writeVO);
			return "movie/write";
		}
		
//		String posterUrl = writeVO.getPosterUrl();
//		posterUrl = posterUrl.replace("<", "&lt;")
//							 .replace(">", "&gt;");
//		writeVO.setPosterUrl(posterUrl);
		
		System.out.println(writeVO.getPosterUrl());
		System.out.println(writeVO.getTitle());
		System.out.println(writeVO.getMovieRating());
		System.out.println(writeVO.getOpenDate());
		System.out.println(writeVO.getOpenCountry());
		System.out.println(writeVO.getRunningTime());
		System.out.println(writeVO.getIntroduce());
		System.out.println(writeVO.getSynopsis());
		System.out.println(writeVO.getOriginalTitle());
		System.out.println(writeVO.getMovieState());
		System.out.println(writeVO.getLanguage());
		System.out.println(writeVO.getBudget());
		System.out.println(writeVO.getProfit());
		
		boolean createResult = this.movieService.createNewMovie(writeVO);
		
		System.out.println("영화 생성 성공? " + createResult);
		
		return "redirect:/list";
	}
}
