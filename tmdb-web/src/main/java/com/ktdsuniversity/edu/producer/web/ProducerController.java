package com.ktdsuniversity.edu.producer.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.producer.service.ProducerService;
import com.ktdsuniversity.edu.producer.vo.ProducerVO;
import com.ktdsuniversity.edu.producer.vo.request.WriteVO;
import com.ktdsuniversity.edu.producer.vo.response.SearchResultVO;

import jakarta.validation.Valid;

@Controller
public class ProducerController {

	private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

	@Autowired
	private ProducerService producerService;

	@GetMapping("/producer/list")
	public String producerListPage(Model model) {

		SearchResultVO searchResult = this.producerService.findAllProducer();

		List<ProducerVO> list = searchResult.getResult();

		int searchCount = searchResult.getCount();

		model.addAttribute("searchResult", list);
		model.addAttribute("searchCount", searchCount);

		return "producer/list";
	}

	@GetMapping("/producer/write")
	public String viewWritePage() {
		return "producer/write";
	}

	@PostMapping("/producer/write")
	public String doWriteAction(@Valid @ModelAttribute WriteVO writeVO, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("inputData", writeVO);
			return "producer/write";
		}

		logger.debug(writeVO.getName());
		
		boolean createResult = this.producerService.createNewProducer(writeVO);
		
		logger.debug("제작진 작성 성공 {}", createResult);
		
		return "redirect:/producer/list";
	}

}
