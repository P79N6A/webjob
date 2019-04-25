package webfood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webfood.mapper.OpLogMapper;
import webfood.model.OpLog;
import webfood.service.LogService;

@Service()
public class LogServiceImpl implements LogService {

	@Autowired
	private OpLogMapper logMapper;

	@Override
	public int Save(OpLog log) {

		return logMapper.insert(log);
	}

}
