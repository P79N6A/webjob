package webfood.mapper;

import java.util.List;

import webfood.model.Build;

public interface BuildMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Build record);

	int insertSelective(Build record);

	Build selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Build record);

	int updateByPrimaryKey(Build record);

	List<Build> queryAll();

	List<Build> queryAllByUnit(String buildUnit);
}