package com.ktdsuniversity.edu.members.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;


@Mapper
public interface MembersDao {

	int insertNewMember(RegistVO registVO);
	
	MembersVO selectMemberByEmail(String email);

	int selectMemberCount();

	List<MembersVO> selectMemberList();

}
