package webfood.mapper;

import java.util.List;

import webfood.model.Compact;

public interface CompactMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Compact record);

	int insertSelective(Compact record);

	Compact selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Compact record);

	int updateByPrimaryKey(Compact record);

	List<Compact> queryAllCp();

	Compact queryLast();
}