package com.ktdsuniversity.edu.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.movie.dao.MovieDao;
import com.ktdsuniversity.edu.movie.enums.ReadType;
import com.ktdsuniversity.edu.movie.vo.MovieVO;
import com.ktdsuniversity.edu.movie.vo.request.WriteVO;
import com.ktdsuniversity.edu.movie.vo.response.SearchResultVO;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieDao movieDao;
	
	@Override
	public SearchResultVO findAllMovie() {
		SearchResultVO result = new SearchResultVO();
		
		int count = this.movieDao.selectMovieCount();
		result.setMovieCount(count);
		
		if(count == 0) {
			return result;
		}
		
		List<MovieVO> list = this.movieDao.selectMovieList();
		
		result.setMovieList(list);
		
		return result;
	}

	@Override
	public boolean createNewMovie(WriteVO writeVO) {
		int insertCount = this.movieDao.insertNewMovie(writeVO);
		System.out.println("생성된 영화의 개수? : " + insertCount);
		return insertCount == 0;
	}

	@Override
	public MovieVO findMovieById(String id, ReadType readType) {
		
		MovieVO movie = this.movieDao.selectMovieById(id);
		
		return movie;
	}
}
