package webfood.mapper;

import java.util.List;

import webfood.model.Process;

public interface ProcessMapper {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(Process record);

	Process selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Process record);

	List<Process> queryByBid(Integer id);

	List<Process> queryByBidNoPay(Integer id);
}