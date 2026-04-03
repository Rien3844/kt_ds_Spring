package com.ktdsuniversity.edu.poster.vo;

public class PosterVO {
	
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
		if(this.posterGroupId != null) {
			this.posterGroupId = this.posterGroupId.replace("<", "&lt")
								   		           .replace(">", "&gt");
		}
		return this.posterGroupId;
	}
	public void setPosterGroupId(String posterGroupId) {
		this.posterGroupId = posterGroupId;
	}
	public String getObfuscateName() {
		if(this.obfuscateName != null) {
			this.obfuscateName = this.obfuscateName.replace("<", "&lt")
								   		           .replace(">", "&gt");
		}
		return this.obfuscateName;
	}
	public void setObfuscateName(String obfuscateName) {
		this.obfuscateName = obfuscateName;
	}
	public String getDisplayName() {
		if(this.displayName != null) {
			this.displayName = this.displayName.replace("<", "&lt")
								   		       .replace(">", "&gt");
		}
		return this.displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getExtendName() {
		if(this.extendName != null) {
			this.extendName = this.extendName.replace("<", "&lt")
								   		     .replace(">", "&gt");
		}
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
		if(this.posterPath != null) {
			this.posterPath = this.posterPath.replace("<", "&lt")
								   		     .replace(">", "&gt");
		}
		return this.posterPath;
	}
	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}
	
}
