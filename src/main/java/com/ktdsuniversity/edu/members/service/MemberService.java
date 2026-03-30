package com.ktdsuniversity.edu.members.service;

import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

public interface MemberService {

	SearchResultVO findAllMember();

	boolean createNewMember(RegistVO registVO);

}
