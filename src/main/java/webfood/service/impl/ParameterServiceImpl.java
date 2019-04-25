package webfood.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webfood.mapper.ParameterMapper;
import webfood.model.Parameter;
import webfood.service.ParameterService;

@Service("parameterService")
public class ParameterServiceImpl implements ParameterService
{

	@Autowired
	private ParameterMapper paramererDao;

	@Override
	public List<Parameter> queryAllParameter()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Parameter para)
	{

		return paramererDao.insert(para);
	}

	@Override
	public int deleteById(Integer id)
	{

		return paramererDao.deleteByPrimaryKey(id);
	}

	@Override
	public void updateBug(String content)
	{

		paramererDao.updateBug(content);
	}

	@Override
	public Parameter queryBug()
	{

		return paramererDao.selectByPrimaryKey(1);
	}

}
