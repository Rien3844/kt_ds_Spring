package com.ktdsuniversity.edu.poster.vo.request;

public class UploadVO {
	private int posterNum;
	private String posterGroupId;
	private String obfuscateName;
	private String displayName;
	private String extendName;
	private long posterLength;
	private String posterPath;
	
	public int getPosterNum() {
		return this.posterNum;
	}
	public void setPosterNum(int posterNum) {
		this.posterNum = posterNum;
	}
	public String getPosterGroupId() {
		return this.posterGroupId;
	}
	public void setPosterGroupId(String posterGroupId) {
		this.posterGroupId = posterGroupId;
	}
	public String getObfuscateName() {
		return this.obfuscateName;
	}
	public void setObfuscateName(String obfuscateName) {
		this.obfuscateName = obfuscateName;
	}
	public String getDisplayName() {
		return this.displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getExtendName() {
		return this.extendName;
	}
	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}
	public long getPosterLength() {
		return this.posterLength;
	}
	public void setPosterLength(long posterLength) {
		this.posterLength = posterLength;
	}
	public String getPosterPath() {
		return this.posterPath;
	}
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
}
