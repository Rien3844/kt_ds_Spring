package com.ktdsuniversity.edu.movie.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.movie.vo.MovieVO;

public class SearchResultVO {
	
	private int movieCount;
	private List<MovieVO> movieList;
	
	public int getMovieCount() {
		return this.movieCount;
	}
	public void setMovieCount(int movieCount) {
		this.movieCount = movieCount;
	}
	public List<MovieVO> getMovieList() {
		return this.movieList;
	}
	public void setMovieList(List<MovieVO> movieList) {
		this.movieList = movieList;
	}
}
