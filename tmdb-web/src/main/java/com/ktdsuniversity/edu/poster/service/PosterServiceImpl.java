package com.ktdsuniversity.edu.poster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.poster.dao.PosterDao;
import com.ktdsuniversity.edu.poster.vo.request.SearchPosterVO;
import com.ktdsuniversity.edu.poster.vo.response.DownloadVO;

@Service
public class PosterServiceImpl implements PosterService{
	
	@Autowired
	private PosterDao posterDao;
	
	@Override
	public DownloadVO findAttachPoster(SearchPosterVO searchPosterVO) {
		DownloadVO result = this.posterDao.selectPosterByGroupIdAndPosterNum(searchPosterVO);
		return result;
	}
}
