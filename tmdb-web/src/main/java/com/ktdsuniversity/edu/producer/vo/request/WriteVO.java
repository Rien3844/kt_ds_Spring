package com.ktdsuniversity.edu.producer.vo.request;

import jakarta.validation.constraints.NotEmpty;

public class WriteVO {

	private String id;

	@NotEmpty(message = "이름을 반드시 입력해주세요.")
	private String name;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
