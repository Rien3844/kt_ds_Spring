package com.ktdsuniversity.edu.producer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.producer.vo.ProducerVO;
import com.ktdsuniversity.edu.producer.vo.request.WriteVO;

@Mapper
public interface ProducerDao {

	int selectProducerCount();

	List<ProducerVO> selectProducerList();

	int insertNewProducer(WriteVO writeVO);
	
}
