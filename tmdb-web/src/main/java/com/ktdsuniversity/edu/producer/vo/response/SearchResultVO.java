package com.ktdsuniversity.edu.producer.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.producer.vo.ProducerVO;

public class SearchResultVO {
	private List<ProducerVO> result;
	private int count;
	
	public List<ProducerVO> getResult() {
		return this.result;
	}
	public void setResult(List<ProducerVO> result) {
		this.result = result;
	}
	public int getCount() {
		return this.count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
