package webfood.mapper;

import webfood.model.Parameter;

public interface ParameterMapper
{
	int deleteByPrimaryKey(Integer id);

	int insert(Parameter record);

	int insertSelective(Parameter record);

	Parameter selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Parameter record);

	int updateByPrimaryKey(Parameter record);

	void updateBug(String content);
}