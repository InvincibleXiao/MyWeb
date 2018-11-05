package com.dao;

import java.util.List;

import com.model.User;

public interface MemberDao {

	public int insert(User user);
	public List select();
	
	
}
