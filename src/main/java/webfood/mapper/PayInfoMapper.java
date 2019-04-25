package webfood.mapper;

import java.util.List;

import webfood.model.PayInfo;

public interface PayInfoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(PayInfo record);

	int insertSelective(PayInfo record);

	PayInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(PayInfo record);

	int updateByPrimaryKey(PayInfo record);

	List<PayInfo> queryByBid(Integer id);
}