package com.ktdsuniversity.edu.files.vo;

public class FilesVO {
	private int fileNum;           // NUMBER(2, 0)
	private String fileGroupId;    // VARCHAR2(18)
	private String obfuscateName;  //  VARCHAR2(50)
	private String displayName;    //  VARCHAR2(250)
	private String extendName;     //  VARCHAR2(8)
	private long fileLength;       // NUMBER(36, 0)
	private String filePath;       // VARCHAR2(500)
	
	public int getFileNum() {
		return this.fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}
	public String getFileGroupId() {
		return this.fileGroupId;
	}
	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
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
	public long getFileLength() {
		return this.fileLength;
	}
	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}
	public String getFilePath() {
		return this.filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
//게시글이 지워지면 파일도 지우고, DB에서도 지운다.