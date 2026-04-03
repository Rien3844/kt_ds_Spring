package com.ktdsuniversity.edu.movie.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class WriteVO {
	
	private String id;//
	
	private List<MultipartFile> attachPoster;
	
	private String posterUrl;
	
	@NotEmpty(message = "영화 제목을 작성해주세요.")
	private String title;
	
	private String movieRating;
	private String openDate;
	private String openCountry;
	
	private int runningTime;
	
	private String introduce;
	
	@NotEmpty(message = "시놉시스를 작성해주세요.")
	private String synopsis;
	
	private String originalTitle;
	
	@NotEmpty
	@Size(min = 0, max = 5, message = "개봉 상태를 5글자 이하로 작성해주세요.")
	private String movieState;
	
	@NotEmpty
	@Size(min = 0, max = 6, message = "영화의 원어를 작성해주세요. 최대 6글자입니다.")
	private String language;

	private long budget;

	private long profit;
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<MultipartFile> getAttachPoster() {
		return this.attachPoster;
	}
	public void setAttachPoster(List<MultipartFile> attachPoster) {
		this.attachPoster = attachPoster;
	}
	public String getPosterUrl() {
		return this.posterUrl;
	}
	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMovieRating() {
		return this.movieRating;
	}
	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}
	public String getOpenDate() {
		return this.openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getOpenCountry() {
		return this.openCountry;
	}
	public void setOpenCountry(String openCountry) {
		this.openCountry = openCountry;
	}
	public int getRunningTime() {
		return this.runningTime;
	}
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
	public String getIntroduce() {
		return this.introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getSynopsis() {
		return this.synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getOriginalTitle() {
		return this.originalTitle;
	}
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	public String getMovieState() {
		return this.movieState;
	}
	public void setMovieState(String movieState) {
		this.movieState = movieState;
	}
	public String getLanguage() {
		return this.language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public long getBudget() {
		return this.budget;
	}
	public void setBudget(long budget) {
		this.budget = budget;
	}
	public long getProfit() {
		return this.profit;
	}
	public void setProfit(long profit) {
		this.profit = profit;
	}

}
