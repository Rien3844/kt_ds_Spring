package com.ktdsuniversity.edu.poster.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ktdsuniversity.edu.poster.vo.request.SearchPosterVO;
import com.ktdsuniversity.edu.poster.vo.response.DownloadVO;
import com.ktdsuniversity.edu.poster.service.PosterService;

@Controller
public class PosterController {
	
	private Map<String, String> mimeTypeMap;
	
	public PosterController() {
		this.mimeTypeMap = new HashMap<>();
		
		// Images
		this.mimeTypeMap.put("jpg", "image/jpeg");
		this.mimeTypeMap.put("jpeg", "image/jpeg");
		this.mimeTypeMap.put("png", "image/png");
		this.mimeTypeMap.put("webp", "image/webp");
		this.mimeTypeMap.put("gif", "image/gif");
		this.mimeTypeMap.put("svg", "image/svg");
	}
	
	@Autowired
	private PosterService posterService;
	
	@GetMapping("/file/{posterGroupId}/{posterNum}")
	public ResponseEntity<Resource> doDownloadAction(
			@PathVariable String posterGroupId,
			@PathVariable int posterNum){
		
		SearchPosterVO searchPosterVO = new SearchPosterVO();
		searchPosterVO.setPosterGroupId(posterGroupId);
		searchPosterVO.setPosterNum(posterNum);
		
		
		DownloadVO downloadVO = this.posterService.findAttachPoster(searchPosterVO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadVO.getDisplayName());
		headers.set(HttpHeaders.CONTENT_LENGTH, downloadVO.getPosterLength());
		headers.set(HttpHeaders.CONTENT_TYPE, 
				this.mimeTypeMap.getOrDefault(downloadVO.getExtendName().toLowerCase() , 
						"application/octet-stream"));
				return ResponseEntity.ok()
				             .headers(headers)
				             .body(downloadVO.getResource());
	}
}
