package com.ktdsuniversity.edu.producer.service;

import com.ktdsuniversity.edu.producer.vo.request.WriteVO;
import com.ktdsuniversity.edu.producer.vo.response.SearchResultVO;


public interface ProducerService {

	SearchResultVO findAllProducer();

	boolean createNewProducer(WriteVO writeVO);
	
}
