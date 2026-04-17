package com.ktdsuniversity.edu.members.service;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

public interface MembersService {

	boolean createNewMember(RegistVO registVO);
	
	MembersVO findMemberByEmail(String email);

	SearchResultVO findAllMember();

}
