package com.ktdsuniversity.edu.producer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.producer.dao.ProducerDao;
import com.ktdsuniversity.edu.producer.vo.ProducerVO;
import com.ktdsuniversity.edu.producer.vo.request.WriteVO;
import com.ktdsuniversity.edu.producer.vo.response.SearchResultVO;

@Service
public class ProducerServiceImpl implements ProducerService {

	private static final Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);

	@Autowired
	private ProducerDao producerDao;
	
	@Override
	public SearchResultVO findAllProducer() {
		
		SearchResultVO result = new SearchResultVO();
		
		int count = this.producerDao.selectProducerCount();
		result.setCount(count);
		
		if(count == 0) {
			return result;
		}
		
		List<ProducerVO> list = this.producerDao.selectProducerList();
		result.setResult(list);
		
		return result;
	}

	@Transactional
	@Override
	public boolean createNewProducer(WriteVO writeVO) {
		
		int insertCount = this.producerDao.insertNewProducer(writeVO);
		
		logger.debug("추가된 제작자의 수 {}", insertCount);
		
		return insertCount == 1;
	}

}
