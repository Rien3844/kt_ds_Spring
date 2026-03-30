package com.ktdsuniversity.edu.members.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.members.vo.MemberVO;

/**
 * 멤버 검색 결과를 담고있는 클래스
 * 멤버 목록(email, name, password)
 * 멤버 수
 */
public class SearchResultVO {
	
	private List<MemberVO> result;
	private int count;
	
	public List<MemberVO> getResult() {
		return this.result;
	}
	public void setResult(List<MemberVO> result) {
		this.result = result;
	}
	public int getCount() {
		return this.count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
