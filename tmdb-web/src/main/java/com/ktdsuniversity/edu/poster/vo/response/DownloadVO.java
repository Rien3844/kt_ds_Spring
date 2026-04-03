package com.ktdsuniversity.edu.poster.vo.response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

public class DownloadVO {
	private String displayName;
	private String extendName;
	private String posterLength;
	private String posterPath;

	// 사용자에게 전달해 줄 파일 객체
	private File poster;

	// 브라우저에게 전달하기 위한 파일 객체
	private Resource resource;

	// poster과 resource는 DB에서 가져오는게 아니기 때문에 Setter 필요 X

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		
		// Java 기반 애플리케이션에서 파일 다운로드 시 
		// 영어를 제외한 글자들이 사라지는 현상.
		// ==> 사라지지않게 다국어 지원.
		this.displayName = displayName;
		
		try {
			this.displayName = URLEncoder.encode(displayName, "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
	}

	public String getExtendName() {
		return this.extendName;
	}

	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}

	public String getPosterLength() {
		return this.posterLength;
	}

	public void setPosterLength(String posterLength) {
		this.posterLength = posterLength;
	}

	public String getPosterPath() {
		return this.posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
		// this.poster 생성
		this.poster = new File(this.posterPath);

		// this.resource 생성
		try {
			FileInputStream posterStream = new FileInputStream(this.poster);
			this.resource = new InputStreamResource(posterStream);
		} 
		catch (FileNotFoundException fnfe) {
			// TODO 전용 예외 발생시켜 던지기
		}
	}

	public File getPoster() {
		return this.poster;
	}

	public Resource getResource() {
		return this.resource;
	}
}
