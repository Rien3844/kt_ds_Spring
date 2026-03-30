package com.ktdsuniversity.edu.board.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.board.dao.BoardDao;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.request.WriteVO;
import com.ktdsuniversity.edu.board.vo.response.SearchResultVO;

@Service
public class BoardServiceImpl implements BoardService {

	// 빈(bean) 컨테이너에 들어있는 객체 중 타입이 일치하는 객체를 할당 받는다.
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public SearchResultVO findAllBoard() {
		SearchResultVO result = new SearchResultVO();
		
		// 게시글 개수 조회.
		int count = this.boardDao.selectBoardCount();
		result.setCount(count);
		
		if(count == 0) {
			return result;
		}
		
		// 게시글 목록 조회.
		List<BoardVO> list = this.boardDao.selectBoardList();

		result.setResult(list);
		
		return result;
	}
	
	@Override
	public boolean createNewBoard(WriteVO writeVO) {
		// dao => insert 요청
		// mybatis 는 insert, update, delete를 수행했을 때
		// 영향을 받은 row의 수를 반환시킨다.
		// ex) insert ==> insert 된 row의 개수 반환.
		//     update ==> update 된 row의 개수 반환.
		//     delete ==> delete 된 row의 개수 반환.
		int insertCount = this.boardDao.insertNewBoard(writeVO);
		System.out.println("생성된 게시글의 개수? : " + insertCount);
		return insertCount > 0;
	}
	
	@Override
	public BoardVO findBoardByArticleId(String articleId) {
		// 조회수 증가가 조회보다 늦게 만들어지면, 조회 이후 증가하므로, 1 낮은 값이 조회됨. 
		// ==> 증가를 먼저하고 조회를 하도록 코드 구조 변경. 
		// 2. 조회수 증가.
		int updateCount = this.boardDao.updateViewCntIncreaseById(articleId);
		System.out.println("조회수가 증가된 게시글의 수 : " + updateCount);
		
		// 1. 게시글 조회.
		BoardVO board = this.boardDao.selectBoardById(articleId);
		
		
		if(updateCount == 0) {
			//존재하지 않는 게시물을 조회하려 했다.
			return null;
//			throw new RuntimeException("존재하지 않는 게시글입니다.");
		}
		
		// 조회한 게시글을 반환.
		return board;
	}
	
	@Override
	public boolean deleteBoardByArticleId(String id) {
		int deleteCount = this.boardDao.deleteBoardById(id);
		
		return deleteCount == 1;
		
	}

	
	
}
