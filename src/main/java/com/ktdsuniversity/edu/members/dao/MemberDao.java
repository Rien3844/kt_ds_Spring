package com.ktdsuniversity.edu.members.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.members.vo.MemberVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;

@Mapper
public interface MemberDao {

	int selectMemberCount();

	List<MemberVO> selectMemberList();

	int insertNewMember(RegistVO registVO);
	
}
