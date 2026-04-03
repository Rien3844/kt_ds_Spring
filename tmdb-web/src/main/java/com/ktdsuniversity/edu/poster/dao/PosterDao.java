package com.ktdsuniversity.edu.poster.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.poster.vo.request.SearchPosterVO;
import com.ktdsuniversity.edu.poster.vo.request.UploadVO;
import com.ktdsuniversity.edu.poster.vo.response.DownloadVO;

@Mapper
public interface PosterDao {
	
	int insertAttachPoster(UploadVO uploadVO);

	DownloadVO selectPosterByGroupIdAndPosterNum(SearchPosterVO searchPosterVO);
}
