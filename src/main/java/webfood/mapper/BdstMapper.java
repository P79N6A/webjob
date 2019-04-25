package webfood.mapper;

import java.util.List;

import webfood.model.Bdst;

public interface BdstMapper
{
	int deleteByPrimaryKey(Integer id);

	int insert(Bdst record);

	int insertSelective(Bdst record);

	Bdst selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Bdst record);

	int updateByPrimaryKey(Bdst record);

	List<Bdst> queryByPid(Integer id);
}