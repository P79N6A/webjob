package webfood.service;

import java.util.List;

import webfood.model.Parameter;

public interface ParameterService
{

	List<Parameter> queryAllParameter();

	int insert(Parameter para);

	int deleteById(Integer id);

	void updateBug(String content);

	Parameter queryBug();

}
