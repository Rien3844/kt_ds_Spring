package com.ktdsuniversity.edu.poster.service;

import com.ktdsuniversity.edu.poster.vo.request.SearchPosterVO;
import com.ktdsuniversity.edu.poster.vo.response.DownloadVO;

public interface PosterService {
	DownloadVO findAttachPoster(SearchPosterVO searchPosterVO);
}
