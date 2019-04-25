package webfood.service;

import java.util.List;

import webfood.model.User;

public interface UserService
{

	User findUserByUsername(String username);

	List<String> getMenuListByUserId(Integer id);

	// 插入用户
}
