package com.ktdsuniversity.edu.movie.service;

import com.ktdsuniversity.edu.movie.enums.ReadType;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.WriteVO;
import com.ktdsuniversity.edu.movie.vo.response.SearchResultVO;

public interface MovieService {

	SearchResultVO findAllMovie();
	
	boolean createNewMovie(WriteVO writeVO);

	MovieVO findMovieById(String id, ReadType readType);
	
}
